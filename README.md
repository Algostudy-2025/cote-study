# 📚 cote-study

**기간**: 2025년 6월 19일 ~  
**목표**: 기업 코딩 테스트 대비를 위한 알고리즘 문제 풀이 및 코드 리뷰를 통해 함께 성장하기

---

## 📌 Rules

### 🔹 전체 구성

| 주차        | 운영 내용               | 플랫폼         |
| --------- | ------------------- | ----------- |
| Week 0    | 연습 주차 (환경 적응 & 실습)  | 프로그래머스      |
| Week 1\~N | 정규 운영 (기업 코테 실전 대비) | 프로그래머스 + 백준 |

---

### 🔹 Week 0: 연습 주차

* 프로그래머스 플랫폼 사용법 및 함수형 채점 방식에 익숙해지는 기간
* 아래 문제 중 2문제 이상 풀이 (예시)

  * [Lv1 - 완주하지 못한 선수](https://school.programmers.co.kr/learn/courses/30/lessons/42576)
  * [Lv1 - K번째 수](https://school.programmers.co.kr/learn/courses/30/lessons/42748)
* 주석으로 풀이 방식, 시간복잡도 설명 필요

---

### 🔹 Week 1\~: 정규 주차 운영

* 매주 **프로그래머스 1\~2문제 + 백준 1문제** 풀이

  * 프로그래머스: 고득점 Kit, Lv2\~3 중심
  * 백준: 기업 기출 위주
* 문제는 `weekN/overview.md`에 공지

  * 발표자 1명씩 돌아가며 풀이 설명
  * 다양한 풀이 공유 및 토론

---



### 🔹 문제 풀이
- **매주 수요일 오후 12시까지**, 지난주에 선정된 총 2~3문제 풀이 후 PR 제출
- 풀이 시 **주석으로 간단한 설명을 반드시** 작성

### 🔹 코드 리뷰
- **매주 수요일 밤 8시 30분**, 오프라인 리뷰 진행
- 발표자 1명 설명 → 질의응답 및 다양한 풀이 공유

### 🔹 AI 사용 가이드
- 생성형 AI의 도움 없이 **직접 사고하고 풀이하는 것**을 권장

---

### 🔹 Convention Rule

#### ✅ Commit Message
```
[Wn] 자유기술  
ex) [W2] 홍길동 문제 풀이  
```

#### ✅ Pull Request
```
Wn / 이름  
ex) W3 / 홍길동  
```

---

## 🗂️ Directory Structure

```bash
📦 Algostudy_2025_CodingTest
 ┣ 📂 week1
 ┃ ┣ 📂 honggildong
 ┃ ┃ ┣ 📄 P_42586.java // 프로그래머스 문제 (P = Programmers)
 ┃ ┃ ┗ 📄 B_1234.java // 백준 문제 (B = Baekjoon)
 ┣ 📂 week2
 ┃ ┣ 📂 honggildong
 ┃ ┃ ┗ 📄 P_12909.py
 ┣ 📄 overview.md // 주차별 선정 문제 목록 및 참고 링크 정리
 ┗ 📄 README.md
```

---

## 🧭 Git & GitHub Guide

### 1. ✅ 저장소 Fork 및 Clone
```
# 내 GitHub 계정으로 포크
# 이후 로컬에 복제
git clone https://github.com/스터디장계정/Algostudy_2025_CodingTest.git
cd Algostudy_2025_CodingTest
```

### 2. ✅ 원본 저장소 연결 (upstream 등록)
```
git remote add upstream https://github.com/스터디장계정/Algostudy_2025_CodingTest.git
```

### 3. ✅ 주차별 브랜치 생성 및 작업
```
git checkout -b username/week1

# 폴더 생성 및 코드 작성

git add .
git commit -m "[W1] 홍길동"
git push origin username/week1
```

### 4. ✅ Pull Request 생성
- GitHub에서 원본 저장소로 PR 생성
- 제목: `W1 / 홍길동`
- 내용: 문제 링크, 풀이 요약 등 포함

### 5. ✅ 최신 상태로 유지 (매주 Merge 이후)
```
# 메인 브랜치로 이동
git checkout main

# 원본 저장소에서 변경 내용 가져오기
git pull upstream main

# 다시 개인 브랜치로 이동 후 작업
git checkout username/week2
```

---

### ✅ Pull Request 작성 예시 템플릿
```
## ✏️ 푼 문제
- [프로그래머스 - 기능개발](https://school.programmers.co.kr/learn/courses/30/lessons/42586)
- [BOJ 1234 - 문제 제목](https://www.acmicpc.net/problem/1234)

## 💡 풀이 요약
- 큐를 이용하여 배포 시점 관리
- 시간 복잡도: O(N)

## ❗ 어려웠던 점
- 경계 조건에서 버그 발생 → 디버깅에 시간 소요

## ✅ 기타
- 다음 문제도 큐 기반으로 접근 가능할 듯
```
