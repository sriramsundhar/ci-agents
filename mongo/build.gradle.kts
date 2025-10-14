plugins { id("com.palantir.docker") version "0.36.0" }

tasks.create("publish") { dependsOn(getTasksByName("dockerTagsPush", true)) }

docker {
  name = "sriramsundhar/${project.name}"
  tag("dockerhub", "sriramsundhar/${project.name}:latest")
  tag("dockerhub2", "sriramsundhar/${project.name}:${project.version}")
  buildx(true)
  platform("linux/amd64", "linux/arm64")
  load(true)
  copySpec.from("sync").into("sync")
}
