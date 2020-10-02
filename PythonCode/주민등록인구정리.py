import pandas as pd
import sys
import numpy as np
import glob
import os

inputLivingP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\주민등록 인구 및 세대현황"
allFilesL=glob.glob(os.path.join(inputLivingP,"서울특별시_주민등록인구및세대현황.csv"))
dataFrameTotalLp=[]

#구만 선별
dataFrameLP=[]
for file in allFilesL:
    dataFrameTemp=pd.read_csv(file, encoding='cp949',engine='python')
    dataFrameTempToTalLP=dataFrameTemp.iloc[1:,[0,1]]
    for i in dataFrameTempToTalLP.index:
        eachData=dataFrameTempToTalLP.loc[i]
        infor_list=eachData['행정구역'].split(" ")
        data={'지역':infor_list[1],'행정동코드':infor_list[2][1:6],'2020년08월_총인구수':\
            eachData['2020년08월_총인구수'].replace(',','')}
        dataFrameTotalLp.append(pd.DataFrame(data,\
            columns=['지역','행정동코드','2020년08월_총인구수'], index=[i]))

dataFrameTotalLpCSV=pd.concat(dataFrameTotalLp,axis=0,ignore_index=True)
dataFrameTotalLpCSV.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 8월 구별 주민등록인구.csv"\
    ,index=False,encoding="cp949")

print(dataFrameTotalLpCSV['행정동코드'])
