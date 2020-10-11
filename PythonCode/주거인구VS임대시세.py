import pandas as pd
import sys
import numpy as np
import glob
import os
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import font_manager,rc

font_path="C:\\WINDOWS\\Fonts\\batang.ttc"
font_name=font_manager.FontProperties(fname=font_path).get_name()
matplotlib.rc('font',family=font_name)

input="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시생활_주거인구VS임대시세.csv"

df=pd.read_csv(input,encoding='cp949',engine='python')

x=df['2020년 2분기 생활_주거']
y=df['20202분기임대시세 1층']
gu=df['지역']

#거주인구와 생활인구
fig, ax=plt.subplots()
ax.scatter(x,y)

for i, txt in enumerate(gu):
    ax.annotate(txt,(x[i],y[i]))

plt.xlabel('2020년 2분기 생활_주거')
#plt.ylabel('20202분기임대시세 1층')
plt.show()