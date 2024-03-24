import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("java-test-fixtures")
    id ("org.springframework.boot") version "3.1.5"
    id ("io.spring.dependency-management") version "1.0.15.RELEASE"
    id ("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22" apply false
}

allprojects {
    group = "com.pawith"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }


    tasks.withType<JavaCompile>{
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

apply(from = "gradle/spring.gradle")
apply(from = "gradle/lombok.gradle")
apply(from = "gradle/test.gradle")
apply(from = "gradle/restdocs.gradle")
apply(from = "gradle/fcm.gradle")
apply(from = "gradle/externallib.gradle")
apply(from = "gradle/querydsl.gradle")