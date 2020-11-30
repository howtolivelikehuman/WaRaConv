import pandas as pd
import glob
import os
import sys
from pathlib import Path

allFiles=glob.glob(os.path.join("..\\DataSet\\서울시 동별 등록외국인","*서울시 동별 등록외국인 수.xlsx"))

#파일별 반복
for file in allFiles:
    dfForeigner=pd.read_excel(file,sheet_name='Sheet1')
    date=dfForeigner['기간'].iloc[2].replace('/4','') #파일명 용 날짜 추출
    
    #남성 행 걸러내기
    dfForeignerGuM=dfForeigner.loc[(dfForeigner['행정동'].str.contains('소계'))&\
    dfForeigner['성별'].str.contains('남자')]
    #여성 행 걸러내기
    dfForeignerGuF=dfForeigner.loc[(dfForeigner['행정동'].str.contains('소계'))&\
    dfForeigner['성별'].str.contains('여자')]

    dfProcessedGuM=[]
    dfProcessedGuF=[]

    for i in dfForeignerGuM.iloc:#남성인구 딕셔너리
        indexTemp=0
        data={'지역':i['자치구'], '남성':i['계']}
        dfProcessedGuM.append(pd.DataFrame(data,\
            columns=['지역','남성'], index=[indexTemp]))
        indexTemp+=1
    dfProcessedGuMCSV=pd.concat(dfProcessedGuM,axis=0,ignore_index=True)

    for i in dfForeignerGuF.iloc:#여성인구 딕셔너리
        indexTemp=0
        data={'지역':i['자치구'], '여성':i['계']}
        dfProcessedGuF.append(pd.DataFrame(data,\
        columns=['지역','여성'], index=[indexTemp]))
        indexTemp+=1
    dfProcessedGuFCSV=pd.concat(dfProcessedGuF,axis=0,ignore_index=True)

    #두 데이터프레임 병합
    dFProcessedGu=pd.merge(dfProcessedGuMCSV,dfProcessedGuFCSV,on='지역',how='outer')

    outdir=Path("C:\\Users\\admin\\Desktop\\WaRaConv\\ProcessedDataSet\\")
    #출력
    dFProcessedGu.to_csv(Path(outdir,date+'분기 서울시 구별 외국인수.csv'),encoding='cp949',index=False)
    



