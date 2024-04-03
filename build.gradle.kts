import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("java-test-fixtures")

    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"

    id("org.asciidoctor.jvm.convert") version "3.3.2"

    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23" apply false

    kotlin("plugin.lombok") version "1.9.23"
    id("io.freefair.lombok") version "8.1.0"
}



allprojects {
    group = "com.pawith"
    version = "0.0.1"


    repositories {
        mavenCentral()
    }

    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("kotlin-spring")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.lombok")
        plugin("org.jetbrains.kotlin.plugin.jpa")

        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")

        plugin("org.asciidoctor.jvm.convert")

        plugin("java-test-fixtures")
        plugin("io.freefair.lombok")

        from("$rootDir/gradle/customplugin/gradleutils.gradle.kts")
        from("$rootDir/gradle/spring.gradle.kts")
        from("$rootDir/gradle/test.gradle.kts")
    }

    springBoot {
        mainClass = "com.pawith.ApiModuleApplication"
    }


    val asciidoctorExt = configurations.create("asciidoctorExt")
    // Querydsl 설정부 추가 - start
    val generated = file("${buildDir}src/main/generated")

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    kapt {
        keepJavacAnnotationProcessors = true

    }



    dependencies {
        // retryable
        implementation("org.springframework.retry:spring-retry")



        //AWS S3
        implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

        // kotlin json serial/deserial
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")



        // restdocs
        asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
        implementation("org.springframework.restdocs:spring-restdocs-mockmvc")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
        testFixturesImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

        // lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")

        // fcm
        implementation("com.google.firebase:firebase-admin:9.2.0")

        // expire map
        implementation("net.jodah:expiringmap:0.5.11")


        // Feign
        implementation("io.github.openfeign:feign-httpclient:12.1")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.3")
        // JWT
        implementation("io.jsonwebtoken:jjwt-api:0.12.5")
        implementation("io.jsonwebtoken:jjwt-impl:0.12.5")
        implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")
        //OAuth
        implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

        // webclinet
        implementation("org.springframework.boot:spring-boot-starter-webflux")

        // configuration properties
        implementation("org.springframework.boot:spring-boot-configuration-processor")

        // aop
        implementation("org.springframework.boot:spring-boot-starter-aop")

        // querydsl
        implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
        kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
        kapt("jakarta.annotation:jakarta.annotation-api")
        kapt("jakarta.persistence:jakarta.persistence-api")



    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.modulith:spring-modulith-bom:1.0.2")
        }
    }


    // RestDocs 설정

    val snippetsDir = file("build/generated-snippets")

    tasks {
        test {
            outputs.dir(snippetsDir)
            useJUnitPlatform()
        }

        asciidoctor {
            configurations("asciidoctorExt")
            baseDirFollowsSourceFile()
            inputs.dir(snippetsDir)
            dependsOn(test)
        }
    }

//    tasks.register("copySnippets", Copy) {
//        if (project.name != 'Api-Module') {
//            dependsOn(tasks.test)
//            from(snippetsDir)
//            into(rootProject.file("Api-Module/build/generated-snippets"))
//        }
//    }
//
//    build {
//        dependsOn(tasks.copySnippets)
//    }
//
//    if (project.name == 'Api-Module') {
//        asciidoctor {
//            configurations("asciidoctorExtensions")
//            inputs.dir(snippetsDir)
//            baseDirFollowsSourceFile()
//        }
//
//        tasks.register("generateRestDocs", Copy) {
//            dependsOn(asciidoctor)
//            from file("build/docs/asciidoc")
//            into file("src/main/resources/static/docs")
//        }
//    }



    // querydsl QClass 파일 생성 위치를 지정
    tasks.withType<JavaCompile> {
        options.generatedSourceOutputDirectory.set(generated)
    }

    // kotlin source set 에 querydsl QClass 위치 추가
    sourceSets {
        main {
            kotlin.srcDirs += generated
        }
    }

    // gradle clean 시에 QClass 디렉토리 삭제
    tasks.named("clean") {
        doLast {
            generated.deleteRecursively()
        }
    }

    kapt{
        generateStubs = true
    }

}

