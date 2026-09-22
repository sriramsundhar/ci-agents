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
        "docker", "buildx", "build",
        "--platform", "linux/amd64,linux/arm64",
        "-t", "ghcr.io/sriramsundhar/${project.name}:latest",
        "-t", "ghcr.io/sriramsundhar/${project.name}:${project.version}",
        "--push",
        project.projectDir
    )
}
