dependencies {
    // actuators
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation(project(":Log-Module"))
    implementation(project(":Alarm-Module"))

    implementation(project(":Common-Module"))
    testImplementation(testFixtures(project(":Common-Module")))

    implementation(project(":Batch-Module"))
    implementation(project(":Domain-Module"))
    implementation(project(":Infra-Module"))
    implementation(project(":Image-Module"))

}
