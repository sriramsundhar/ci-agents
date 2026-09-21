tasks.register<Exec>("docker") {
    commandLine(
        "docker", "build",
        "-t", "ghcr.io/sriramsundhar/${project.name}:latest",
        "-t", "ghcr.io/sriramsundhar/${project.name}:${project.version}",
        project.projectDir
    )
}

tasks.register<Exec>("publish") {
    dependsOn("docker")
    commandLine(
        "sh", "-c",
        "docker push ghcr.io/sriramsundhar/${project.name}:latest && " +
            "docker push ghcr.io/sriramsundhar/${project.name}:${project.version}"
    )
}
