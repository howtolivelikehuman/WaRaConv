import pandas as pd
import sys
import numpy as np
import glob
import os





'''
input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 임대시세.csv"
output="C:\\Users\\admin\\Desktop\\서울시전체임대시세.csv"

dataFrame=pd.read_csv(input,encoding="utf-8",engine='python',\
    names=['NM','CD','GUBUN','20182분기1층','20192분기1층','20202분기1층','20182분기1층외'\
        ,'20192분기1층외','20202분기1층외','20182분기전체','20192분기전체','20202분기전체'])

dataFrame=dataFrame.iloc[1:,:]

dataFrame_filter=dataFrame.loc[(dataFrame['GUBUN'].str.contains('gu')),:]

dataFrame_filter=dataFrame_filter.iloc[:,[1,10]]
df1=dataFrame_filter.sort_values(by='CD')
df1['20192분기전체']=df1['20192분기전체'].astype(float)
df1.rename(columns={'CD':'지역'}, inplace=True)

#df1.to_csv(output,index=False,encoding="cp949")

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 매출현황(편의점).CSV"
output="C:\\Users\\admin\\Desktop\\서울시편의점매출.csv"

dataFrame=pd.read_csv(input,encoding="cp949",engine='python')

dataFrame=dataFrame.iloc[1:,[0,2,3,4,5]]

dataFrame_filter=dataFrame.loc[(dataFrame['지역'].str.contains('구')),:]

for col in dataFrame_filter.columns:
    dataFrame_filter[col]=dataFrame_filter[col].str.replace(',','')

dataFrame_sort=dataFrame_filter.sort_values(by='지역')

dataFrame_sort['2019월평균매출평균']=\
(dataFrame_sort['2019년 상반기'].astype(float)+dataFrame_sort['2019년 하반기'].astype(float))/2

dataFrame_sort['2019건단가평균']=\
(dataFrame_sort['Unnamed: 3'].astype(float)+dataFrame_sort['Unnamed: 5'].astype(float))/2


df2=dataFrame_sort.iloc[:,[0,5]]

#df2.to_csv(output,index=False,encoding="cp949")

result=pd.merge(df1,df2,on="지역",how='outer')

output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시편의점매출&임대시세.csv"
#result.to_csv(output,index=False,encoding="cp949")

x=result['20192분기전체']
y=result['2019월평균매출평균']
plt.plot(x,y,'o')
plt.xlabel('20192분기전체임대시세')
plt.ylabel('2019월평균매출')
plt.show()
'''

