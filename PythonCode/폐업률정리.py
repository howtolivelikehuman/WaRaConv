import pandas as pd

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 개폐업률(편의점).csv"

df=pd.read_csv(input, encoding='utf-8',engine='python',\
    names=['NM','CD','GUBUN',
    '2018년2분기개업수','2018년2분기폐업수','2018년2분기개업률','2018년2분기폐업률',
    '2019년2분기개업수','2019년2분기폐업수','2019년2분기개업률','2019년2분기폐업률',
    '2020년2분기개업수','2020년2분기폐업수','2020년2분기개업률','2020년2분기폐업률'],
    header=None)

df=df.iloc[1:]

df.rename(columns={'CD':'행정동코드'}, inplace=True)
df.rename(columns={'2018년2분기폐업률':'폐업률'}, inplace=True)
df.rename(columns={'2019년2분기폐업률':'폐업률'}, inplace=True)
df.rename(columns={'2020년2분기폐업률':'폐업률'}, inplace=True)

#구단위
df=df[(df['GUBUN'].str.contains('gu'))]

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\폐업률 통계\\"

df2018=df.iloc[:,[1,6]]
df2018.to_csv(outdir+"2018년 2분기 폐업률 통계.csv",encoding='cp949',index=False)

df2019=df.iloc[:,[1,10]]
df2019.to_csv(outdir+"2019년 2분기 폐업률 통계.csv",encoding='cp949',index=False)

df2020=df.iloc[:,[1,14]]
df2020.to_csv(outdir+"2020년 2분기 폐업률 통계.csv",encoding='cp949',index=False)