import pandas as pd

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 임대시세.csv"

df=pd.read_csv(input)

df=df[(df['GUBUN'].str.contains('gu'))]

df=df.iloc[:,[0,3,4,5]]

df.rename(columns={'NM':'행정동코드'}, inplace=True)
df.rename(columns={'20182분기1층':'1층 임대시세'}, inplace=True)
df.rename(columns={'20192분기1층':'1층 임대시세'}, inplace=True)
df.rename(columns={'20202분기1층':'1층 임대시세'}, inplace=True)

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\임대시세 통계\\"

df2018=df.iloc[:,[0,1]]
df2018.to_csv(outdir+"2018년 2분기 임대시세 통계.csv",encoding='cp949',index=False)

df2019=df.iloc[:,[0,2]]
df2019.to_csv(outdir+"2019년 2분기 임대시세 통계.csv",encoding='cp949',index=False)

df2020=df.iloc[:,[0,3]]
df2020.to_csv(outdir+"2020년 2분기 임대시세 통계.csv",encoding='cp949',index=False)