plugins { id("com.palantir.docker") version "0.36.0" }

tasks.create("publish") { dependsOn(getTasksByName("dockerTagsPush", true)) }

docker {
  name = "sriramsundhar/${project.name}"
  tag("latest", "sriramsundhar/${project.name}:latest")
  tag("${project.name}${project.version}", "sriramsundhar/${project.name}:${project.version}")
  copySpec.from("sync").into("sync")
}
