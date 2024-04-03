
val kotestVersion = "5.8.0"

dependencies {
    // Fixture testing tool
    "testImplementation"("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
    "testFixturesImplementation"("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
    "testImplementation"("io.kotest.extensions:kotest-extensions-spring:1.1.3")

    // mvc
    "implementation"("org.springframework.boot:spring-boot-starter-test")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testFixturesImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testImplementation"("io.mockk:mockk:1.4.1")

    // modulith
    "implementation"("org.springframework.modulith:spring-modulith-starter-core")
    "runtimeOnly"("org.springframework.modulith:spring-modulith-runtime")
    "testImplementation"("org.springframework.modulith:spring-modulith-starter-test")


    "testImplementation"("io.kotest:kotest-runner-junit5:$kotestVersion")
    "testImplementation"("io.kotest:kotest-assertions-core:$kotestVersion")
    "testImplementation"("io.mockk:mockk:1.13.10")
    "testImplementation"("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}

