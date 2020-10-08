import pandas as pd
import glob
import os
import sys
from pathlib import Path

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\2020. 2분기 가구원수당 세대수.xlsx"
allFiles=glob.glob(os.path.join("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet","*2분기 가구원수당 세대수.xlsx"))

for file in allFiles:
    #파일명 추출
    filename=file
    name=filename.split("\\")[6]
    name=name[:-5]

    dfFamily=pd.read_excel(file)
    #데이터 구 추출
    dfFamilyGu=dfFamily[(dfFamily['행정동'].str.startswith('계'))|\
        (dfFamily['행정동'].str.startswith('소계'))]

    #일부 세대 추출
    dfFamilyGu=dfFamilyGu.iloc[:,[1,4,5,6,7,8]]

    #열 정보 변경
    dfFamilyGu.rename(columns={'자치구':'지역'}, inplace=True)

    outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\"

    dfFamilyGu.to_csv(outdir+name+".csv",encoding='cp949',index=False)