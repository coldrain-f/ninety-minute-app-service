# Back-end

# 축구팀 매칭 플랫폼 [90분]
<a href = "https://www.ninety.site/">![image](https://user-images.githubusercontent.com/105046055/182977241-f6c3d9f6-981f-4766-8b0e-179fe6776f22.png)
<a href = "https://www.ninety.site/">>Link<br>
<br><br><br>

  
## ERD
<a href = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d5665f3d-6223-40bf-95c8-4068ccf1eb55/B%E1%84%8C%E1%85%A9%E1%84%8F%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A9%E1%86%AB8%E1%84%8C%E1%85%A9_%E1%84%8B%E1%85%AA%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%A5%E1%84%91%E1%85%B3%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%86%B7.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220623%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220623T071454Z&X-Amz-Expires=86400&X-Amz-Signature=6d5a84841575561818e62c68eea810164ce895955ef69fccef7abf3921fb76a1&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22B%25E1%2584%258C%25E1%2585%25A9%25E1%2584%258F%25E1%2585%25B3%25E1%2586%25AF%25E1%2584%2585%25E1%2585%25A9%25E1%2586%25AB8%25E1%2584%258C%25E1%2585%25A9_%25E1%2584%258B%25E1%2585%25AA%25E1%2584%258B%25E1%2585%25B5%25E1%2584%258B%25E1%2585%25A5%25E1%2584%2591%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25A6%25E1%2584%258B%25E1%2585%25B5%25E1%2586%25B7.pdf%22&x-id=GetObject">Link<br><br><br>

  
## 구현 기능
> 1. 회원가입/로그인
- Spring Security, JWT 인증 방식으로 로그인 구현(refresh token 사용)<br><br>

> 2. 팀 등록/가입
- 새로운 팀을 등록하거나 기존에 있는 팀에 가입
- 팀 등록은 사용자당 1개만 가능
- 팀 가입은 제한 없읍<br><br>
  
> 3. 대결 등록/신청 
- 팀을 등록한 사용자의 경우 대결 등록을 통해 대결 신청을 받을 수 있음
- 대결 등록을 한 상대의 정보를 보고 대결 신청 가능<br><br>
  
> 4. 경기 히스토리(팀, 개인)
- 경기 결과 및 경기에 참여한 인원, 교체 선수, MVP, 분위기 메이커 등등 선정하여 등록 가능
- 등록된 경기 히스토리의 경우 언제든 조회 가능<br><br>
  
> 5. 팀, 개인 정보 상세조회
- 팀 정보 상세 조회시 팀의 전적, 멤버, 경기 히스토리등 해당 팀과 관련된 여러 추가적인 정보 확인 가능
- 개인 정보 상세 조회시 포지션별 점수, 전적, 참여했던 경기 히스토리등 추가적인 정보 확인 가능<br><br>
  
> 6. 랭킹(팀, 개인)
- 팀의 경우 승점과 승률을 기준으로 상위 10개팀을 순위별로 조회
- 개인의 경우 설정한 포지션별로 포지션 점수 상위 10명을 순위별로 조회
- 로그인한 사용자의 개인의 순위와 자신이 참여하고 있는 팀들 순위를 따로 조회 가능<br><br><br>


## 기술 스택
<img src="https://img.shields.io/badge/java-004B8D?style=flat&logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=SpringSecurity&logoColor=white"/>

<img src="https://img.shields.io/badge/git-F05032?style=flat&logo=git&logoColor=white"/> <img src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white"/>

<img src="https://img.shields.io/badge/AmazonS3-569A31?style=flat&logo=AmazonS3&logoColor=white"/> <img src="https://img.shields.io/badge/AmazonEC2-FF9900?style=flat&logo=AmazonEC2&logoColor=white"/>
<br><br><br>

  
## 팀원
### Backend(Spring)<br>
- 인상운(팀장) - 팀api<br>
- 문범수 - 회원api, 랭킹api<br>
- 최병민 - 대결api, 경기히스토리api<br><br><br>


## Link
<a href = "https://morning-shamrock-bba.notion.site/8-SA-9220e4c5777b49e6b2d2a007b83b49e8">notion<br>
<a href = "https://github.com/me-coldrain/FE">frontend github<br>
