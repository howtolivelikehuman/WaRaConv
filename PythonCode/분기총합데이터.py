import pandas as pd

for i in range(2018,2021):
    input1="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\가구원수당 세대수\\"+str(i)+". 2분기 가구원수당 세대수.csv"
    input2="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주민등록인구 연령별 통계\\"+str(i)+".2분기 주민등록인구 연령별 통계.csv"
    input3="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주거, 직장 인구\\"+str(i)+"년 2분기 주거,직장인구.csv"
    input4="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\주민등록인구 성별 통계\\"+str(i)+" 2분기 주민등록인구 성별 통계.csv"
    input5="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울특별시 대규모점포 인허가 수.csv"
    input6="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\서울시 편의점수\\"+str(i)+" 서울시 편의점수.csv"
    input7="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\편의점 매출\\"+str(i)+"년 2분기 편의점 매출 통계.csv"
    input8="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\폐업률 통계\\"+str(i)+"년 2분기 폐업률 통계.csv"
    input9="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\지하철역 수 통계.csv"
    input10="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\임대시세 통계\\"+str(i)+"년 2분기 임대시세 통계.csv"

    df1=pd.read_csv(input1, encoding="cp949",engine='python')
    df2=pd.read_csv(input2, encoding="cp949",engine='python')
    df3=pd.read_csv(input3, encoding="cp949",engine='python')
    df4=pd.read_csv(input4, encoding="cp949",engine='python')
    df5=pd.read_csv(input5, encoding="cp949",engine='python')
    df6=pd.read_csv(input6, encoding="cp949",engine='python')
    df7=pd.read_csv(input7, encoding="cp949",engine='python')
    df8=pd.read_csv(input8, encoding="cp949",engine='python')
    df9=pd.read_csv(input9, encoding="cp949",engine='python')
    df10=pd.read_csv(input10, encoding="cp949",engine='python')

    df3=df3.iloc[:,[0,1,5,6]]

    df5=df5.iloc[:,[0,(i-2017)]]
    df5.rename(columns={'지역구':'지역'}, inplace=True)
    df5.rename(columns={str(i):'대규모점포 인허가 수'}, inplace=True)

    dftotal=pd.merge(df4,df1,on='지역', how='outer')
    dftotal=pd.merge(dftotal,df2,on='지역', how='outer')
    dftotal=pd.merge(dftotal,df3,on=['지역','행정동코드'], how='outer')
    dftotal=pd.merge(dftotal,df5,on='지역', how='outer')
    dftotal=pd.merge(dftotal,df6,on=['지역','행정동코드'], how='outer')
    dftotal=pd.merge(dftotal,df7,on='지역', how='outer')
    dftotal=pd.merge(dftotal,df8,on='행정동코드', how='outer')
    dftotal=pd.merge(dftotal,df9,on='행정동코드', how='outer')
    dftotal=pd.merge(dftotal,df10,on='행정동코드', how='outer')

    output="C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\분기총합데이터\\"+str(i)+" 2분기 총합.csv"

    dftotal.to_csv(output, encoding='cp949',index=False)