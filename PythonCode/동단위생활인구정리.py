import pandas as pd
import sys
import numpy as np
import glob
import os

allFiles=glob.glob(os.path.join("../DataSet/서울시 생활인구","*201806.csv"))

for file in allFiles:
    #파일명 중 날짜 추출
    filename=file
    name=filename.split("_")[3]
    name=name[:-4]

    #파일 읽어 전처리
    df=pd.read_csv(file,encoding='utf-8',engine='python',
    names=['기준일ID', '시간대구분','CD','총생활인구수'], header=None)
    df=df.iloc[1:]
    df['CD']=df['CD'].astype(str)

    #해당 월의 일 수*24시간 구하기
    Length=len(df.index)/424

    dfTotal=[]
    for i in range(424): #424
        code=df.iloc[i].loc['CD']
        dfDong=df.loc[(df['CD'].str.contains(code))]
        num=dfDong['총생활인구수'].astype(float).sum()/Length #월별 날짜*24시간으로 나눈다.

        data={'CD':code,'총생활인구수':num }
        dfTotal.append(pd.DataFrame(data,columns=['CD','총생활인구수'],index=[i]))

    dfTotal=pd.concat(dfTotal,axis=0,ignore_index=True)

    outdir="../ProcessedDataSet/동단위 세부 데이터/생활인구 통계/"

    dfTotal.to_csv(outdir+name+" 생활인구 통계.csv",encoding='cp949',index=False)