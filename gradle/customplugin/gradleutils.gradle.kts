//Collection<Project> getProjectList(String rootModuleName, String containsModuleName) {
//    return project(":$rootModuleName").allprojects.stream()
//            .filter { project -> project.name.contains(containsModuleName) }
//            .toList();
//}
//
//Collection<Project> getProjectsEndsWith(String rootModuleName, String endsWithModuleName) {
//    return project(":$rootModuleName").allprojects.stream()
//            .filter { project -> project.name.endsWith(endsWithModuleName) }
//            .toList();
//}

fun getProjectsEndsWith(rootModuleName: String, endsWithModuleName: String): Collection<Project> {
    return project(":$rootModuleName").allprojects.stream()
            .filter { project -> project.name.endsWith(endsWithModuleName) }
            .toList();
}


//ext {
//    getProjectsEndsWith = this.&getProjectsEndsWith
//}

