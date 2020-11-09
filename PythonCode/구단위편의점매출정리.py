import pandas as pd

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 매출분석(편의점).CSV"

df=pd.read_csv(input, encoding='cp949',engine='python')
df=df.iloc[1:]

df.rename(columns={'행정구역':'지역'}, inplace=True)
df.rename(columns={'2018년2분기합계':'편의점 매출'}, inplace=True)
df.rename(columns={'2019년2분기합계':'편의점 매출'}, inplace=True)
df.rename(columns={'2020년2분기합계':'편의점 매출'}, inplace=True)

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\편의점 매출\\"

df2018=df.iloc[:,[0,3]]
df2018.to_csv(outdir+"2018년 2분기 편의점 매출 통계.csv",encoding='cp949',index=False)

df2019=df.iloc[:,[0,6]]
df2019.to_csv(outdir+"2019년 2분기 편의점 매출 통계.csv",encoding='cp949',index=False)

df2020=df.iloc[:,[0,9]]
df2020.to_csv(outdir+"2020년 2분기 편의점 매출 통계.csv",encoding='cp949',index=False)
