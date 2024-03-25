import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

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
    }

    springBoot {
        mainClass = "com.pawith.ApiModuleApplication"
    }


    val asciidoctorExt = configurations.create("asciidoctorExt")

//    val jar: Jar by tasks
//    val bootJar: BootJar by tasks
//
//    bootJar.enabled = false
//    jar.enabled = true
//
//    if (name == "Api-Module") {
//        tasks.getByName<BootJar>("bootJar") {
//            enabled = true
//        }
//    }


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
        showProcessorStats = true
    }



    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        // jpa
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.mysql:mysql-connector-j")

        // retryable
        implementation("org.springframework.retry:spring-retry")

        // modulith
        implementation("org.springframework.modulith:spring-modulith-starter-core")
        runtimeOnly("org.springframework.modulith:spring-modulith-runtime")
        testImplementation("org.springframework.modulith:spring-modulith-starter-test")

        //AWS S3
        implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

        // Fixture testing tool
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter:1.0.0")
        testFixturesImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter:1.0.0")

        // mvc
        implementation("org.springframework.boot:spring-boot-starter-test")
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")

        // restdocs
        asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
        implementation("org.springframework.restdocs:spring-restdocs-mockmvc")
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
        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")


//        implementation("org.jetbrains.kotlin:kotlin-reflect")


    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.modulith:spring-modulith-bom:1.0.2")
        }
    }

//    tasks.named("bootBuildImage") {
//        builder = 'paketobuildpacks/builder-jammy-base:latest'
//    }

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

}