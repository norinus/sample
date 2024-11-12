# 스프링 부트  JPA, QueryDSL, Liquibase. MapStruct , 타임리프 기반 예제 프로젝트

이 프로젝트는 스프링 부트  JPA, QueryDSL, Liquibase. MapStruct, 타임리프 기반 프로젝트입니다. 

## 프로젝트 개요

1. **JPA** - 데이터베이스 엑서스를 JPA 리포지토리를 이용 CRUD 작성

2. **QueryDSL** - 데이터베이스 엑서스를 QueryDSL 의존성을 이용 CRUD 작성

3. **Liquibase** - Database 버전 관리 모듈 

4. **MapStruct** - 엔티티 DTO간 변환 작업을 위해 사용

5. **타임리프** - 템플릿엔진은 타임리프 사용
   
   
  
# MySQL 데이터베이스 및 사용자 설정 가이드

이 가이드는 MySQL 데이터베이스와 사용자 계정을 특정 권한으로 생성하는 방법에 대한 단계별 설명을 제공합니다.

## 사전 준비 사항
- MySQL이 시스템에 설치되어 있고 MySQL 서버가 실행 중인지 확인하세요.
- 데이터베이스와 사용자 계정을 생성하기 위해 루트 사용자 비밀번호가 필요합니다.

## 데이터베이스 및 사용자 생성 단계

### 1단계: MySQL에 로그인
루트 사용자로 MySQL에 로그인하려면 터미널에서 다음 명령어를 실행하세요:

```bash
mysql -u root -p
```

루트 사용자 비밀번호를 입력하라는 메시지가 나타납니다.

### 2단계: 데이터베이스 생성
로그인한 후 새로운 데이터베이스를 생성합니다. 여기서는 `klaatus`라는 이름의 데이터베이스를 예제로 사용하겠습니다.

```sql
CREATE DATABASE lab;
```

### 3단계: 사용자 생성 및 권한 부여
이제 새로운 MySQL 사용자를 생성하고, 방금 생성한 데이터베이스에 대한 권한을 부여합니다. `klaatus`와 `1234`를 원하는 사용자 이름과 비밀번호로 대체하여 사용하세요.

```sql
CREATE USER 'klaatus'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON lab.* TO 'klaatus'@'localhost';
FLUSH PRIVILEGES;
```

이 명령어는 `klaatus`라는 사용자를 생성하고 `lab` 데이터베이스에 대한 모든 권한을 부여합니다.

### 4단계: MySQL에서 로그아웃
모든 작업이 완료되면 다음 명령어로 MySQL에서 로그아웃할 수 있습니다:

```sql
EXIT;
```

### 추가 참고 사항
- 사용자 및 데이터베이스 이름은 고유해야 하므로 필요에 따라 수정하세요.
- 생성한 사용자로 데이터베이스에 접속하려면 다음 명령어를 사용하세요:

```bash
mysql -u klaatus -p
```

비밀번호 입력 시 `1234`를 입력하여 로그인합니다.

## 문제 해결
- 문제가 발생할 경우 MySQL이 실행 중인지, 사용자가 데이터베이스와 사용자 생성을 위한 권한을 가지고 있는지 확인하세요.
- 사용자가 모든 호스트에서 접근할 수 있도록 하려면 `localhost`를 `%`로 변경할 수 있지만, 보안 위험이 있을 수 있습니다.

---

이 단계들을 완료하면 MySQL 데이터베이스와 사용자가 성공적으로 설정됩니다!
