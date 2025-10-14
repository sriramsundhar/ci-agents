plugins { id("com.palantir.docker") version "0.30.0" }

tasks.create("publish") { dependsOn(getTasksByName("dockerTagsPush", true)) }

docker {
  name = "sriramsundhar/${project.name}"
  tag("latest", "sriramsundhar/${project.name}:latest")
  tag("${project.version}", "sriramsundhar/${project.name}:${project.version}")
}
