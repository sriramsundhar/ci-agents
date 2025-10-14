plugins {
    id("com.palantir.docker") version "0.36.0"
}

tasks.create("publish") {
    dependsOn(getTasksByName("dockerTagsPush", true))
}

docker {
    name = "docker.io/sriramsundhar/${project.name}"
    tag("latest", "${project.version}")
    //tag("${project.version}", "sriramsundhar/${project.name}:${project.version}")
    buildx(true)
    platform("linux/amd64","linux/arm64")
    copySpec.from("sync").into("sync")

}
