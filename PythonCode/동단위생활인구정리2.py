import pandas as pd
import sys
import numpy as np
import glob
import os

allFiles=glob.glob(os.path.join("../ProcessedDataSet/동단위 세부 데이터/생활인구 통계","*.csv"))
inputDir="../ProcessedDataSet/동단위 세부 데이터/생활인구 통계/"

for year in ['2018', '2019', '2020']:
    for quat in [1,2]:
        firstMonth="0"+str((quat-1)*3+1)
        secondMonth="0"+str((quat-1)*3+2)
        thirdMonth="0"+str((quat-1)*3+3)

        df1=pd.read_csv(inputDir+year+firstMonth+" 생활인구 통계.csv",encoding='cp949',engine='python')
        df2=pd.read_csv(inputDir+year+secondMonth+" 생활인구 통계.csv",encoding='cp949',engine='python')
        df3=pd.read_csv(inputDir+year+thirdMonth+" 생활인구 통계.csv",encoding='cp949',engine='python')
        
        #병합
        dftotal=pd.merge(df1,df2,on='CD',how='outer')
        dftotal=pd.merge(dftotal,df3,on='CD',how='outer')

        #분기 평균 계산
        dftotal['CD']=dftotal['CD'].astype(str)
        dftotal['분기평균총생활인구수']=(dftotal['총생활인구수_x']+dftotal['총생활인구수_y']+dftotal['총생활인구수'])/3
        
        #데이터 추출
        dftotal=dftotal.iloc[:,[0,4]]
        outdir="../ProcessedDataSet/동단위 세부 데이터/생활인구 통계2/"
        dftotal.to_csv(outdir+year+" "+str(quat)+"분기 생활인구 통계.csv",encoding='cp949',index=False)

