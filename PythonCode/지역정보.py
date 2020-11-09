import pandas as pd
import sys
import numpy as np
import glob
import os

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 소득_가구수.csv"

df=pd.read_csv(input,encoding='utf-8',engine='python',\
    names=['NM','CD','GUBUN','20182분기소득분위','20182분위가구수',
        '20192분기소득분위','20192분기가구수','20202분기소분분위','20202분기가구수'],\
            header=None)

df=df.iloc[1:,:]
df['CD']=df['CD'].astype(int)
df['CD']=df['CD'].astype(str)

df=df.iloc[1:,0:3]
df=df.assign(자치구="",행정동="")

df=df.copy()
Gu=''

for i in range(len(df)):
    if df.iloc[i].loc['GUBUN']=='gu':
        Gu=df.iloc[i].loc['NM']
        df.iloc[i].loc['자치구']=Gu
        df.iloc[i].loc['행정동']='계'
    else:
        df.iloc[i].loc['자치구']=Gu
        df.iloc[i].loc['행정동']=df.iloc[i].loc['NM']

print(df['CD'])

df.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울특별시 지역코드.csv",encoding="cp949",index=False)










