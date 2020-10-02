import pandas as pd
import sys
import numpy as np
import glob
import os
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import font_manager,rc

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
    TotalPeople=dataFrameLivingPGu['8월일평균총생활인구수'].sum()

    data={'행정동코드':gu,'8월일평균총생활인구수':TotalPeople}
    dataFrameLP.append(pd.DataFrame(data,columns=['행정동코드','8월일평균총생활인구수'], index=[i]))

dataFrameLPCSV=pd.concat(dataFrameLP,axis=0,ignore_index=True)

dFLPRP=pd.merge(ResidentP,dataFrameLPCSV,on='행정동코드',how='outer')

output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시생활인구_주민등록인구.csv"
dFLPRP.to_csv(output,encoding='cp949',index=False)

font_path="C:\\WINDOWS\\Fonts\\batang.ttc"
font_name=font_manager.FontProperties(fname=font_path).get_name()
matplotlib.rc('font',family=font_name)

x=dFLPRP['2020년08월_총인구수']
y=dFLPRP['8월일평균총생활인구수']
gu=dFLPRP['지역']

fig, ax=plt.subplots()
ax.scatter(x,y)

for i, txt in enumerate(gu):
    ax.annotate(txt,(x[i],y[i]))

plt.xlabel('2020년08월_총인구수')
plt.ylabel('8월일평균총생활인구수')
plt.show()