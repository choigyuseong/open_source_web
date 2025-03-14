plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // PDF 파일 처리 라이브러리
    implementation("org.apache.pdfbox:pdfbox:2.0.27")

    // 자연어 처리 및 텍스트 요약 도구
    implementation("org.apache.opennlp:opennlp-tools:1.9.3")

    // Lombok (필요에 따라 사용, Kotlin에서는 데이터 클래스 등으로 대체 가능)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // 테스트 관련 라이브러리
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // 개발 편의를 위한 devtools (자동 재시작 등)
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
