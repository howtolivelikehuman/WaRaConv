import pandas as pd

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\지하철역\\서울시 지하철역 수.csv"

df=pd.read_csv(input)

df=df[(df['GUBUN'].str.contains('gu'))]

df.rename(columns={'CD':'행정동코드'}, inplace=True)
df.rename(columns={'NUM':'지하철역 수'}, inplace=True)

df=df.iloc[:,[1,3]]

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\"

df.to_csv(outdir+"지하철역 수 통계.csv",encoding='cp949',index=False)