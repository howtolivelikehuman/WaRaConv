import pandas as pd
import sys
import numpy as np
import glob
import os

inputLivingP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\주민등록 인구 및 세대현황\\2020"
allFilesL=glob.glob(os.path.join(inputLivingP,"*서울특별시_주민등록인구및세대현황.csv"))
dataFrameTotalLp=[]

#구 단위 성별 인구
dFsex=[]
for file in allFilesL:
    dataFrameTemp=pd.read_csv(file, encoding='cp949',engine='python')
    dataFrameTempToTalLP=dataFrameTemp.iloc[1:,[0,4,5]]
    for i in dataFrameTempToTalLP.index:
        eachData=dataFrameTempToTalLP.loc[i]
        infor_list=eachData['행정구역'].split(" ")
        data={'지역':infor_list[1],'행정동코드':infor_list[2][1:6],'남자 인구수':\
            eachData['2020년08월_남자 인구수'], '여자 인구수':eachData['2020년08월_여자 인구수'] }
        dataFrameTotalLp.append(pd.DataFrame(data,\
            columns=['지역','행정동코드','남자 인구수','여자 인구수'], index=[i]))

dataFrameTotalLpCSV=pd.concat(dataFrameTotalLp,axis=0,ignore_index=True)
output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\\주민등록인구 성별 통계\\2020 2분기 주민등록인구 성별 통계.csv"
dataFrameTotalLpCSV.to_csv(output, index=False,encoding='cp949')