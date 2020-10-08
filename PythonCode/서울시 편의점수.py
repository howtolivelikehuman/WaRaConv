import pandas as pd

input="C:\\Users\\admin\\Desktop\\WaRaConv\\DataSet\\서울시 점포수(편의점).csv"

dfConv=pd.read_csv(input,encoding='utf-8',engine='python',\
    names=['NM','CD','GUBUN','2018전체점포','2018프랜차이즈','2018일반','2019전체점포'\
        ,'2019프랜차이즈','2019일반','2020전체점포','2020프랜차이즈','2020일반'],\
            header=None)

dfConv=dfConv.iloc[1:]
dfConv=dfConv[(dfConv['GUBUN'].str.contains('gu'))]
dfConv.rename(columns={'NM':'지역','CD':'행정동코드'}, inplace=True)

dfConv2018=dfConv.iloc[:,[0,1,3]]
dfConv2018.rename(columns={'2018전체점포':'편의점 수'}, inplace=True)
dfConv2019=dfConv.iloc[:,[0,1,6]]
dfConv2019.rename(columns={'2019전체점포':'편의점 수'}, inplace=True)
dfConv2020=dfConv.iloc[:,[0,1,9]]
dfConv2020.rename(columns={'2020전체점포':'편의점 수'}, inplace=True)

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 편의점수\\"

dfConv2018.to_csv(outdir+"2018 서울시 편의점수.csv",encoding="cp949",index=False)
dfConv2019.to_csv(outdir+"2019 서울시 편의점수.csv",encoding="cp949",index=False)
dfConv2020.to_csv(outdir+"2020 서울시 편의점수.csv",encoding="cp949",index=False)