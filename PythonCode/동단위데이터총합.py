import pandas as pd

for i in range(2018,2021):
    #기본 지역코드
    input1='../ProcessedDataSet/'+str(i)+' 서울특별시 지역코드.csv'
    #성별 통계
    input2='../ProcessedDataSet/동단위 세부 데이터/주민등록인구 성별 통계/'+str(i)+' 2분기 주민등록인구 성별 통계.csv'
    #가구원수당 세대수 통계
    input3='../ProcessedDataSet/동단위 세부 데이터/세대 통계/'+str(i)+' 2분기 가구원수당 세대수 통계.csv'
    #연령별 통계
    input4='../ProcessedDataSet/동단위 세부 데이터/주민등록인구 연령별 통계/'+str(i)+' 2분기 주민등록인구 연령별 통계.csv'
    #주거, 직장인구 통계
    input5='../ProcessedDataSet/동단위 세부 데이터/주거, 직장인구 통계/'+str(i)+'년 2분기 주거, 직장인구 통계.csv'
    #편의점 수 통계
    input6='../ProcessedDataSet/동단위 세부 데이터/편의점 통계/'+str(i)+' 편의점수 통계.csv'
    #폐업률 통계
    input7='../ProcessedDataSet/동단위 세부 데이터/편의점 폐업률 통계/'+str(i)+'년 2분기 폐업률 통계.csv'
    #지하철역 통계
    input8='../ProcessedDataSet/동단위 세부 데이터/지하철역 통계/지하철역 수 통계.csv'
    #임대시세 통계
    input9='../ProcessedDataSet/동단위 세부 데이터/임대시세 통계/'+str(i)+'년 2분기 임대시세 통계.csv'


    df1=pd.read_csv(input1, encoding="cp949",engine='python')
    df2=pd.read_csv(input2, encoding="cp949",engine='python')
    df3=pd.read_csv(input3, encoding="cp949",engine='python')
    df4=pd.read_csv(input4, encoding="cp949",engine='python')
    df5=pd.read_csv(input5, encoding="cp949",engine='python')
    df6=pd.read_csv(input6, encoding="cp949",engine='python')
    df7=pd.read_csv(input7, encoding="cp949",engine='python')
    df8=pd.read_csv(input8, encoding="cp949",engine='python')
    df9=pd.read_csv(input9, encoding="cp949",engine='python')

    dftotal=pd.merge(df1,df2,on='CD',how='outer')
    dftotal=pd.merge(dftotal,df3,on=['자치구','행정동'],how='outer')
    dftotal=pd.merge(dftotal,df4,on=['자치구','행정동'],how='outer')
    dftotal=pd.merge(dftotal,df5,on='CD',how='outer')
    dftotal=pd.merge(dftotal,df6,on='CD',how='outer')
    dftotal=pd.merge(dftotal,df7,on='CD',how='outer')
    dftotal=pd.merge(dftotal,df8,on='CD',how='outer')
    dftotal=pd.merge(dftotal,df9,on='CD',how='outer')

    output="../ProcessedDataSet/동단위 분기총합데이터/"+str(i)+"년 2분기 동단위 총합.csv"

    dftotal.to_csv(output, encoding='cp949',index=False)