import pandas as pd

input="..\\DataSet\\서울시 점포수(편의점).csv"

dfConv=pd.read_csv(input,encoding='utf-8',engine='python',\
    names=['NM','CD','GUBUN','2018전체점포','2018프랜차이즈','2018일반','2019전체점포'\
        ,'2019프랜차이즈','2019일반','2020전체점포','2020프랜차이즈','2020일반'],\
            header=None)

dfConv=dfConv.iloc[2:]
dfConv['CD']=dfConv['CD'].astype(str)

dfConv2018=dfConv.iloc[:,[1,3]]
dfConv2018.rename(columns={'2018전체점포':'편의점 수'}, inplace=True)
dfConv2019=dfConv.iloc[:,[1,6]]
dfConv2019.rename(columns={'2019전체점포':'편의점 수'}, inplace=True)
dfConv2020=dfConv.iloc[:,[1,9]]
dfConv2020.rename(columns={'2020전체점포':'편의점 수'}, inplace=True)

outdir="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\동단위 세부 데이터\\편의점 통계\\"

dfConv2018.to_csv(outdir+"2018 편의점수 통계.csv",encoding="cp949",index=False)
dfConv2019.to_csv(outdir+"2019 편의점수 통계.csv",encoding="cp949",index=False)
dfConv2020.to_csv(outdir+"2020 편의점수 통계.csv",encoding="cp949",index=False)