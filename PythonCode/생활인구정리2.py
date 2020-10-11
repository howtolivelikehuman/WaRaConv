import pandas as pd
import sys
import numpy as np
import glob
import os

inputLivingP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 생활인구\\LOCAL_PEOPLE_DONG_201906.csv"

dataFrame1=pd.read_csv(inputLivingP,encoding='utf-8',engine='python')

dataFrame1=dataFrame1.iloc[:,0:3]

#중간 과정 임시 데이터 출력
dataFrame1.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 생활인구\\temp.csv",encoding="cp949")

#헤더 재선정
dataFrame1=pd.read_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 생활인구\\temp.csv"\
 ,encoding='cp949',engine='python',names=['기준일ID','시간대구분','행정동코드','총생활인구수'])

dataFrame1=dataFrame1.iloc[1:,:]

dataFrameTotalDong=[]

for i in range(424): #424
    code=dataFrame1.iloc[i].loc['행정동코드']
    dataFrameDong=dataFrame1.loc[(dataFrame1['행정동코드'].str.contains(code))]
    num=dataFrameDong['총생활인구수'].astype(float).sum()/(30*24) #월별 날짜*24시간으로 나눈다.

    data={'행정동코드':code,'구평균총생활인구수':num }
    dataFrameTotalDong.append(pd.DataFrame(data,\
        columns=['행정동코드','구평균총생활인구수'],index=[i]))

dataFrameTotalDongCSV=pd.concat(dataFrameTotalDong,axis=0,ignore_index=True)

#임시 파일 출력
#outputLivingP="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 생활인구\\LOCAL_PEOPLE_DONG_202006_temp.csv"
#dataFrameTotalDongCSV.to_csv(outputLivingP,index=False,encoding="cp949")

#dataFrameTotalDongCSV=pd.read_csv(outputLivingP,encoding='cp949',engine='python')
dataFrameTotalDongCSV['행정동코드']=dataFrameTotalDongCSV['행정동코드'].astype(str)

dFtemp=pd.read_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 8월 구별 주민등록인구.csv",
encoding='cp949',engine='python')

dFtemp=dFtemp.iloc[:,[0,1]]
dFTotal=[]

for guData in dFtemp.iloc:
    guCode=guData['행정동코드'].astype(str)
    guName=guData['지역']
    dFDong=dataFrameTotalDongCSV.loc[(dataFrameTotalDongCSV['행정동코드'].str.contains(guCode))]
    People=dFDong['구평균총생활인구수'].astype(float).sum()

    data={'지역':guName,'행정동코드':guCode,'구평균총생활인구수':People}
    dFTotal.append(pd.DataFrame(data,\
        columns=['지역','행정동코드','구평균총생활인구수'],index=[0]))

dFTotalCSV=pd.concat(dFTotal,axis=0,ignore_index=True)
dFTotalCSV.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\생활인구\\2019 2분기 구별 생활인구.csv",
index=False,encoding="cp949")