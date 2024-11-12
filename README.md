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


# 게시판 애플리케이션

이 프로젝트는 Spring Boot 기반의 게시판 애플리케이션으로, MySQL 데이터베이스와 Liquibase를 사용하여 데이터베이스 마이그레이션을 관리합니다.

## 설정 파일 (`application.yml`)

아래는 애플리케이션 설정 파일인 `application.yml`의 주요 설정에 대한 설명입니다.

```yaml
spring:
  application:
    name: board  # 애플리케이션 이름 설정

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver  # MySQL 데이터베이스 드라이버 설정
    url: jdbc:mysql://localhost:3306/lab?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    # 데이터베이스 URL 설정
    # - useSSL=false: SSL 연결 비활성화
    # - serverTimezone=Asia/Seoul: 서버 시간대를 'Asia/Seoul'로 설정
    # - characterEncoding=UTF-8: UTF-8 인코딩 사용
    username: klaatus  # 데이터베이스 사용자 이름
    password: 1234  # 데이터베이스 비밀번호

  jpa:
    open-in-view: true  # 웹 환경에서 지연 로딩 문제 해결을 위해 뷰 레이어까지 영속성 컨텍스트 열어둠
    hibernate:
      ddl-auto: none  # 스키마 자동 생성 비활성화 (Liquibase로 스키마 관리)
    show-sql: true  # Hibernate가 실행하는 SQL 쿼리를 로그로 출력
    properties:
      hibernate.format_sql: true  # SQL 쿼리를 가독성 있게 포맷하여 출력
      dialect: org.hibernate.dialect.MySQL8InnoDBDialect  # MySQL8 InnoDB용 Hibernate 다이얼렉트 설정

  liquibase:
    change-log: classpath:/db/changelog/db.changelog-master.xml  # Liquibase 체인지 로그 파일 경로
    enabled: true  # Liquibase를 활성화하여 데이터베이스 마이그레이션 수행

logging:
  level:
    root: INFO  # 기본 로깅 레벨을 INFO로 설정
    org.springframework.web: DEBUG  # Spring Web 모듈에 대한 디버그 레벨 로깅 활성화
```

# Spring Boot 프로젝트 빌드 설정

이 문서는 `build.gradle` 파일의 설정에 대한 설명입니다. 이 설정 파일은 Spring Boot 애플리케이션에 필요한 의존성 및 플러그인들을 포함하고 있으며, Lombok, MapStruct, QueryDSL, Liquibase 등을 활용하여 개발 환경을 구성합니다.

## build.gradle 파일

### 플러그인 설정

```gradle
plugins {
    id 'java'  // Java 플러그인 추가
    id 'org.springframework.boot' version '3.3.5'  // Spring Boot 플러그인 추가
    id 'io.spring.dependency-management' version '1.1.6'  // Spring 의존성 관리 플러그인
}
```

### 프로젝트 정보

```gradle
group = 'com.lab'  // 프로젝트 그룹 설정
version = '0.0.1-SNAPSHOT'  // 버전 설정
```

### 자바 버전 설정

```gradle
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)  // 자바 17 버전 사용
    }
}
```

- **Java 17**: 프로젝트에서 Java 17을 사용하도록 설정합니다.

### 컴파일 전용 설정

```gradle
configurations {
    compileOnly {
        extendsFrom annotationProcessor  // Lombok과 같은 애노테이션 프로세서를 포함하는 설정 확장
    }
}
```

- **compileOnly**: 컴파일 시에만 필요한 의존성을 설정합니다. Lombok과 같은 애노테이션 프로세서를 포함합니다.

### 저장소 설정

```gradle
repositories {
    mavenCentral()  // Maven Central 저장소 사용
}
```

- **mavenCentral**: 프로젝트에서 필요한 라이브러리를 Maven Central에서 다운로드합니다.

### 의존성 설정

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'  // JPA Starter
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'  // Thymeleaf Starter
    implementation 'org.springframework.boot:spring-boot-starter-web'  // Web Starter
    compileOnly 'org.projectlombok:lombok'  // Lombok (컴파일 전용)
    developmentOnly 'org.springframework.boot:spring-boot-devtools'  // 개발 도구
    runtimeOnly 'com.mysql:mysql-connector-j'  // MySQL 커넥터
    annotationProcessor 'org.projectlombok:lombok'  // Lombok 애노테이션 프로세서
    testImplementation 'org.springframework.boot:spring-boot-starter-test'  // 테스트 라이브러리
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'  // JUnit 플랫폼 런처
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'  // OpenAPI 문서화

    implementation 'org.mapstruct:mapstruct:1.5.5.Final'  // MapStruct 라이브러리
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'  // MapStruct 애노테이션 프로세서
    annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'  // Lombok과 MapStruct 바인딩

    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.0'  // Jackson JSON 라이브러리

    implementation 'org.liquibase:liquibase-core:4.30.0'  // Liquibase 마이그레이션 라이브러리

    implementation 'com.querydsl:querydsl-jpa:5.1.0:jakarta'  // QueryDSL JPA 지원
    annotationProcessor "com.querydsl:querydsl-apt:5.1.0:jakarta"  // QueryDSL APT
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"  // Jakarta 애노테이션
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"  // Jakarta Persistence 애노테이션

    testCompileOnly 'org.projectlombok:lombok'  // 테스트 시 Lombok 컴파일 전용
    testAnnotationProcessor 'org.projectlombok:lombok'  // Lombok 애노테이션 프로세서 (테스트용)
}
```

각 의존성 설명:
- **Spring Boot Starters**: `spring-boot-starter-data-jpa`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-web`을 포함하여 JPA, Thymeleaf 템플릿 엔진, 웹 MVC를 지원합니다.
- **Lombok**: 코드를 줄여주는 라이브러리로, 컴파일 시점에 애노테이션 프로세서가 코드 생성을 돕습니다.
- **DevTools**: 개발용 도구로, 애플리케이션 자동 재시작을 지원합니다.
- **MySQL Connector**: MySQL 데이터베이스와 연결하기 위한 드라이버입니다.
- **SpringDoc OpenAPI**: OpenAPI 문서를 생성하고 웹 UI로 제공하는 라이브러리입니다.
- **MapStruct**: DTO와 엔티티 간 매핑을 위한 라이브러리입니다.
- **Jackson Databind**: JSON 직렬화와 역직렬화를 위한 라이브러리입니다.
- **Liquibase**: 데이터베이스 마이그레이션 관리

