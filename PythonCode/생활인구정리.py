import pandas as pd
import sys
import numpy as np
import glob
import os

inputLivingP="C:\\Users\\admin\\Desktop\\서울시 생활인구\\서울시 생활인구.csv"
inputResidentP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\주민등록 인구 및 세대현황"

outputLivingP="C:\\Users\\admin\\Desktop\\서울시 생활인구\\서울시 생활인구temp.csv"

dataFrame1=pd.read_csv(inputLivingP,encoding='utf-8',engine='python')

dataFrame1=dataFrame1.iloc[:,0:3]

#중간 과정 임시 데이터 출력
dataFrame1.to_csv(outputLivingP,encoding="cp949")

inputLivingP="C:\\Users\\admin\\Desktop\\서울시 생활인구\\서울시 생활인구temp.csv"
inputResidentP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\주민등록 인구 및 세대현황"

#헤더 재선정
dataFrame1=pd.read_csv(inputLivingP,encoding='cp949',engine='python',\
    names=['기준일ID','시간대구분','행정동코드','총생활인구수'])

dataFrame1=dataFrame1.iloc[1:,:]

dataFrameTotalDong=[]

for i in range(424): #424
    code=dataFrame1.iloc[i].loc['행정동코드']
    dataFrameDong=dataFrame1.loc[(dataFrame1['행정동코드'].str.contains(code))]
    num=dataFrameDong['총생활인구수'].astype(float).sum()/31

    data={'행정동코드':code,'8월일평균총생활인구수':num }
    dataFrameTotalDong.append(pd.DataFrame(data,\
        columns=['행정동코드','8월일평균총생활인구수'],index=[i]))

dataFrameTotalDongCSV=pd.concat(dataFrameTotalDong,axis=0,ignore_index=True)
dataFrameTotalDongCSV.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 생활인구8월.csv"\
    ,index=False,encoding="cp949")