# open_source_web
1. 목표
   : 대학교 교양 과제들은 pdf 파일로 자주 나오는데, 내용을 요약해주고, 중요 단어들을 빈칸으로 바꿔주는 web 을 Spring Boot 를 통해서 만들기.

2. 구현할 기능
   :
 - pdf 파일을 읽어서 해당 내용을 repository ( PdfFileRepository ) 에 저장. ( 상대경로를 통해 pdf_storage
 - textrank 알고리즘을 사용해서 내용 요약.
 - textrank 알고리즘을 통해서 본문에서 사용도가 높은, 빈번도가 높은 단어를 중요 단어로 선택해 important Keyword 로 저장.
 - 그 중 빈번도는 높지만, 중요하지 않은 단어, 혹은 불용어 같은 것들을 nonImportant Keyword 로 저장.
 - nonImportant Keyword 를 제외한 important Keyword 를 빈칸( __ 블랭크로 처리 )으로 바꾸기. 이 때 요약하지 않은 본문과 요약한 본문 모두 확인할 수 있어야 함.
 - 
