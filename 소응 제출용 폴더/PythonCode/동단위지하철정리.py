import pandas as pd

input="..\\DataSet\\지하철역\\서울시 지하철역 수.csv"

df=pd.read_csv(input)

df.rename(columns={'NUM':'지하철역 수'}, inplace=True)

df=df.iloc[1:,[1,3]]

outdir="../ProcessedDataSet/동단위 세부 데이터/지하철역 통계/"
df.to_csv(outdir+"지하철역 수 통계.csv",encoding='cp949',index=False)