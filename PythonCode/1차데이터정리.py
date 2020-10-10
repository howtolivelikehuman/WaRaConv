import pandas as pd
import sys
import numpy as np
import glob
import os
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import font_manager,rc
from mpl_toolkits.mplot3d import Axes3D

inputLivingP="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 생활인구8월.csv"
inputResidentP="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 8월 구별 주민등록인구.csv"

LivingP=pd.read_csv(inputLivingP,encoding='cp949',engine='python')
ResidentP=pd.read_csv(inputResidentP,encoding='cp949',engine='python')

LivingP['행정동코드']=LivingP['행정동코드'].astype(str)
ResidentP['행정동코드']=ResidentP['행정동코드'].astype(str)

dataFrameLP=[]

for i in ResidentP.index:
    gu=ResidentP.loc[i]['행정동코드']
    dataFrameLivingPGu=LivingP.loc[(LivingP['행정동코드'].str.contains(gu))]
    TotalPeople=dataFrameLivingPGu['8월일평균총생활인구수'].sum()/24

    data={'행정동코드':gu,'8월평균총생활인구수':round(TotalPeople)}
    dataFrameLP.append(pd.DataFrame(data,columns=['행정동코드','8월평균총생활인구수'], index=[i]))

dataFrameLPCSV=pd.concat(dataFrameLP,axis=0,ignore_index=True)

dFLPRP=pd.merge(ResidentP,dataFrameLPCSV,on='행정동코드',how='outer')

output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 생활인구_주민등록인구.csv"
#dFLPRP.to_csv(output,encoding='cp949',index=False)

print(dFLPRP)


input="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시생활_주거인구VS임대시세.csv"

df=pd.read_csv(input,encoding='cp949',engine='python')

dfNew=pd.merge(dFLPRP,df,on="지역")

print(dfNew)

font_path="C:\\WINDOWS\\Fonts\\batang.ttc"
font_name=font_manager.FontProperties(fname=font_path).get_name()
matplotlib.rc('font',family=font_name)

x=dfNew['2020년08월_총인구수']
y=dfNew['8월평균총생활인구수']
z=dfNew['20202분기임대시세']
gu=dFLPRP['지역']

'''
#거주인구와 생활인구
fig, ax=plt.subplots()
ax.scatter(x,y,z)

for i, txt in enumerate(gu):
    ax.annotate(txt,(x[i],y[i],z[i]))

plt.xlabel('8월 주민등록인구')
plt.ylabel('8월평균총생활인구수')
plt.zlabel('20202분기임대시세')

plt.show()
'''

#3차원

fig=plt.figure()
ax=fig.gca(projection='3d')

for i in x.index:
    xp=x


ax.set_xlabel('8월 주민등록인구')
ax.set_ylabel('8월평균총생활인구수')
ax.set_zlabel('20202분기임대시세')

plt.show()