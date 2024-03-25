//apply(from= "$rootDir/gradle/customplugin/gradleutils.gradle.kts")

fun getProjectsEndsWith(rootModuleName: String, endsWithModuleName: String): Collection<Project> {
    return project(":$rootModuleName").allprojects.stream()
        .filter { project -> project.name.endsWith(endsWithModuleName) }
        .toList();
}

dependencies {
    getProjectsEndsWith("Domain-Module", "Domain").forEach { module ->
        implementation(project(module.path))
    }
    implementation(project(":Common-Module"))
}





