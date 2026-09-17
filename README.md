# CI Agents
Repo to publish docker images to be used in CI as aux images for testing or build.

## Build all images

Run the following command from the repository root to build every Docker image:

```sh
./gradlew docker
```

The image modules are:

- `sbt-agent`
- `faker`
- `mongo`
- `ai-cli`

To build one image only, use its Gradle project path:

```sh
./gradlew :sbt-agent:docker
./gradlew :faker:docker
./gradlew :mongo:docker
./gradlew :ai-cli:docker
```

The Docker images are tagged with `latest` and the version from `gradle.properties`.

## Add an image

- Add a folder with `docker` file.
- Add corresponding [build.gradle.kts](./sbt-agent/build.gradle.kts)
- Add folder name to [settings.gradle.kts](./settings.gradle.kts).
- Once merged to main branch images will be pushed to docker repo by [CI](./.github/workflows/ci.yml).
