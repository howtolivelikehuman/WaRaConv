import pandas as pd
import glob
import os
import sys
from pathlib import Path

allFiles=glob.glob(os.path.join("C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\가구원수당 세대수","*2분기 가구원수당 세대수.xlsx"))

for file in allFiles:
    #파일명 추출
    filename=file
    name=filename.split("\\")[7]
    name=name[:-5]
    name=name.replace(".","")

    dfFamily=pd.read_excel(file)

    #일부 세대 추출
    dfFamily=dfFamily.iloc[1:,[1,2,4,5,6,7,8]]

    outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\동단위 세부 데이터\\세대 통계\\"
    dfFamily.to_csv(outdir+name+" 통계.csv",encoding='cp949',index=False)