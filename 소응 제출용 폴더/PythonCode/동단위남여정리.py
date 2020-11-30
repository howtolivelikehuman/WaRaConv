import pandas as pd
import sys
import numpy as np
import glob
import os

inputLivingP="..\\DataSet\\주민등록 인구 및 세대현황\\2019"
allFilesL=glob.glob(os.path.join(inputLivingP,"*구_주민등록인구및세대현황.csv"))
dfTotal=[]


#동 단위 성별 인구
dFsex=[]
for file in allFilesL:
    df=pd.read_csv(file, encoding='cp949',engine='python')
    df=df.iloc[:,[0,4,5]]

    for i in df.index:
        if i==0:
            eachData=df.loc[i]
            infor_list=eachData['행정구역'].split(" ")
            data={'CD':infor_list[2][1:6],'남자 인구수':eachData['2019년06월_남자 인구수'],
            '여자 인구수':eachData['2019년06월_여자 인구수'] }
            dfTotal.append(pd.DataFrame(data,\
                columns=['CD','남자 인구수','여자 인구수'], index=[i]))
        else:
            eachData=df.loc[i]
            infor_list=eachData['행정구역'].split(" ")
            data={'CD':infor_list[2][-11:-3],'남자 인구수': eachData['2019년06월_남자 인구수'],
            '여자 인구수':eachData['2019년06월_여자 인구수'] }
            dfTotal.append(pd.DataFrame(data,\
                columns=['CD','남자 인구수','여자 인구수'], index=[i]))

dfTotalCSV=pd.concat(dfTotal,axis=0,ignore_index=True)
output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\동단위 세부 데이터\\주민등록인구 성별 통계\\2019 2분기 주민등록인구 성별 통계.csv"
dfTotalCSV.to_csv(output, index=False,encoding='cp949')
