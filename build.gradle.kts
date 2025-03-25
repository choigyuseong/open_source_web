plugins {
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    java
}

group = "org.example.examhelper"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 웹 애플리케이션 개발용
    implementation("org.springframework.boot:spring-boot-starter-web")

    // PDF 파일 처리 라이브러리
    implementation("org.apache.pdfbox:pdfbox:2.0.27")

    // Lombok (보일러플레이트 코드 감소용)
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    // 개발 편의를 위한 DevTools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // 테스트 관련 라이브러리 (JUnit 5 포함)
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // JPA 기능 추가
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // mySQL 관련
    implementation("mysql:mysql-connector-java:8.0.33")
}

tasks.withType<Test> {
    useJUnitPlatform()
}