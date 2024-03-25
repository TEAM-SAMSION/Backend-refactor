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

    implementation(project(":Log-Module"))
    implementation(project(":Alarm-Module"))

    implementation(project(":Batch-Module"))

    getProjectsEndsWith("Domain-Module", "Presentation").forEach { module ->
        implementation(project(module.path))
    }

}
