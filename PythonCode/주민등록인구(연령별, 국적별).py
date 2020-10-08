import pandas as pd
import glob
import os
import sys
import numpy as np

allFiles=glob.glob(os.path.join("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 주민등록인구 (연령별, 국적별) 통계","*.xlsx"))

pos=list(range(5,26))
pos.insert(0,1)

for file in allFiles:
    dfPeople=pd.read_excel(file)
    #연도, 분기 추출
    date=dfPeople['기간'].iloc[1].replace("/4","")

    dfPeople=dfPeople[(dfPeople['행정동'].str.contains("소계"))&dfPeople['구분'].str.contains("계")]
    dfPeople.rename(columns={'자치구':'지역'}, inplace=True)
    
    dfPeople=dfPeople.iloc[:,pos]

    outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주민등록인구 연령별 통계\\"

    dfPeople.to_csv(outdir+date+"분기 주민등록인구 연령별 통계.csv",encoding='cp949',index=False)
