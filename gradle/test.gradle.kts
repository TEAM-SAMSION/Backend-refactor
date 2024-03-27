

dependencies {
    // Fixture testing tool
    "testImplementation"("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
    "testFixturesImplementation"("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.14")
//    "testImplementation"("com.navercorp.fixturemonkey:fixture-monkey-kotlin:1.0.14")

    // mvc
    "implementation"("org.springframework.boot:spring-boot-starter-test")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testFixturesImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testImplementation"("io.mockk:mockk:1.4.1")

    // modulith
    "implementation"("org.springframework.modulith:spring-modulith-starter-core")
    "runtimeOnly"("org.springframework.modulith:spring-modulith-runtime")
    "testImplementation"("org.springframework.modulith:spring-modulith-starter-test")
}

