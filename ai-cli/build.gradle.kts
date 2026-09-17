tasks.register<Exec>("docker") {
    commandLine(
        "docker", "build",
        "-t", "sriramsundhar/${project.name}:latest",
        "-t", "sriramsundhar/${project.name}:${project.version}",
        project.projectDir
    )
}

tasks.register<Exec>("publish") {
    dependsOn("docker")
    commandLine(
        "sh", "-c",
        "docker push sriramsundhar/${project.name}:latest && " +
            "docker push sriramsundhar/${project.name}:${project.version}"
    )
}
