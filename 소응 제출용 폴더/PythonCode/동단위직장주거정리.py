import pandas as pd
import glob
import os
import sys

input="..\\DataSet\\서울시 인구수 (직장,주거)\\서울시 인구수(명).csv"

dfPeople=pd.read_csv(input)

dfPeople=dfPeople.iloc[1:,:]

dfPeople.rename(columns={'2018년2분기주거인구':'주거인구'}, inplace=True)
dfPeople.rename(columns={'2018년2분기직장인구':'직장인구'}, inplace=True)
dfPeople.rename(columns={'2019년2분기주거인구':'주거인구'}, inplace=True)
dfPeople.rename(columns={'2019년2분기직장인구':'직장인구'}, inplace=True)
dfPeople.rename(columns={'2020년2분기주거인구':'주거인구'}, inplace=True)
dfPeople.rename(columns={'2020년2분기직장인구':'직장인구'}, inplace=True)

#연도별 분류
outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\동단위 세부 데이터\\주거, 직장인구 통계\\"

dfPeople2018=dfPeople.iloc[:,[1,4,5]]
dfPeople2018.to_csv(outdir+"2018년 2분기 주거, 직장인구 통계.csv",encoding='cp949',index=False)

dfPeople2019=dfPeople.iloc[:,[1,6,7]]
dfPeople2019.to_csv(outdir+"2019년 2분기 주거, 직장인구 통계.csv",encoding='cp949',index=False)

dfPeople2020=dfPeople.iloc[:,[1,8,9]]
dfPeople2020.to_csv(outdir+"2020년 2분기 주거, 직장인구 통계.csv",encoding='cp949',index=False)
