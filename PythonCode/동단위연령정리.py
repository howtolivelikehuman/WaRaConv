import pandas as pd
import glob
import os
import sys
import numpy as np

allFiles=glob.glob(os.path.join("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 주민등록인구 (연령별, 국적별) 통계","*.xlsx"))

pos=list(range(5,26))
pos.insert(0,1)
pos.insert(1,2)

for file in allFiles:
    dfPeople=pd.read_excel(file)
    #연도, 분기 추출
    date=dfPeople['기간'].iloc[1].replace("/4","")
    date=date.replace("."," ")

    dfPeople=dfPeople[dfPeople['구분'].str.contains("계")]
    
    dfPeople=dfPeople.iloc[1:,pos]

    dfAge=[]
    for i in dfPeople.index:
        eachData=dfPeople.loc[i]
        data={'자치구':eachData['자치구'],'행정동':eachData['행정동'],
        '0~9세':(eachData['0~4세']+eachData['5~9세']),
        '10~19세':(eachData['10~14세']+eachData['15~19세']),
        '20~29세':(eachData['20~24세']+eachData['25~29세']),
        '30~39세':(eachData['30~34세']+eachData['35~39세']),
        '40~49세':(eachData['40~44세']+eachData['45~49세']),
        '50~59세':(eachData['50~54세']+eachData['55~59세']),
        '60~69세':(eachData['60~64세']+eachData['65~69세']),
        '70~79세':(eachData['70~74세']+eachData['75~79세']),
        '80~89세':(eachData['80~84세']+eachData['85~89세']),
        '90~99세':(eachData['90~94세']+eachData['95~99세']),
        }
        dfAge.append(pd.DataFrame(data,
        columns=['자치구','행정동','0~9세','10~19세','20~29세','30~39세','40~49세','50~59세','60~69세',
        '70~79세','80~89세','90~99세'], index=[i]))
    
    dfAge=pd.concat(dfAge,axis=0,ignore_index=True)

    outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\동단위 세부 데이터\\주민등록인구 연령별 통계\\"

    dfAge.to_csv(outdir+date+"분기 주민등록인구 연령별 통계.csv",encoding='cp949',index=False)

