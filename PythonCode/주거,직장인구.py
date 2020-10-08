import pandas as pd
import glob
import os
import sys

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 인구수.csv"

dfPeople=pd.read_csv(input)

dfPeople=dfPeople[dfPeople['GUBUN'].str.contains('gu')]
dfPeople.rename(columns={'NM':'지역'}, inplace=True)
dfPeople.rename(columns={'CD':'행정동코드'}, inplace=True)
#연도별 분류

dfPeople2018=dfPeople.iloc[:,[0,1,5,6]]
dfPeople2018.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주거, 직장 인구\\2018년 2분기 주거,직장인구.csv",encoding='cp949',index=False)

dfPeople2019=dfPeople.iloc[:,[0,1,9,10]]
dfPeople2019.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주거, 직장 인구\\2019년 2분기 주거,직장인구.csv",encoding='cp949',index=False)

dfPeople2020=dfPeople.iloc[:,[0,1,13,14]]
dfPeople2020.to_csv("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주거, 직장 인구\\2020년 2분기 주거,직장인구.csv",encoding='cp949',index=False)
