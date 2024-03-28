//apply(from = "$rootDir/gradle/customplugin/gradleutils.gradle.kts")

fun getProjectsEndsWith(rootModuleName: String, endsWithModuleName: String): Collection<Project> {
    return project(":$rootModuleName").allprojects.stream()
        .filter { project -> project.name.endsWith(endsWithModuleName) }
        .toList()
}


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

    getProjectsEndsWith("Domain-Module", "Application").forEach { module ->
        implementation(project(module.path))
    }

    getProjectsEndsWith("Domain-Module", "Infrastructure").forEach { module ->
        implementation(project(module.path))
    }

}
