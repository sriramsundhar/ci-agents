plugins {
    id("net.researchgate.release") version "2.8.1"
}

release {
    with(propertyMissing("git") as net.researchgate.release.GitAdapter.GitConfig) {
        requireBranch = "main"
    }
    tagCommitMessage = "Gradle Release Plugin - creating tag: "
    newVersionCommitMessage = "Gradle Release Plugin - new version commit: "
}

tasks {
    "afterReleaseBuild" {
        dependsOn(getTasksByName("publish", true))
    }
}
tasks.create("build") {}
tasks.register("docker") {
    dependsOn(subprojects.map { "${it.path}:docker" })
}


allprojects {
    group = "${project.group}"
    version = "${version}"
}
