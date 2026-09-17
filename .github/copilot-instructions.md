# Copilot instructions

## Project overview

This repository builds Docker images used as auxiliary CI/test services. It is a Gradle 7.4.2 multi-project build with the modules listed in `settings.gradle.kts`:

- `sbt-agent`: a Google Cloud SDK Alpine image with Java 11, jq, Docker/Compose, and SBT 1.4.9.
- `faker`: a Node 14 image running `node-mock-server`. The runtime reads fake API resources from `FAKER_PATH` (defaulting to its `rest` directory) and mounts them under `URL_PATH` (default `/rest/v1`).
- `mongo`: a MongoDB 8 image. Its Docker build includes the sync scripts and initializes data through `sync/scripts/restore.sh`; `mongo/docker-compose.yml` runs it as a single-node replica set.
- `ai-cli`: an Ubuntu 24.04 developer image with common shell tools and the Aider, Antigravity, Claude Code, Codex, Cody, Cursor, and Gemini CLIs. Authentication is supplied at runtime.

The root build is orchestration only. Each module applies the Palantir Docker Gradle plugin, names images `sriramsundhar/<module>`, and defines `publish` in terms of Docker tag pushing. The root `afterReleaseBuild` hook runs publishing after a release.

## Build, test, and lint commands

Use the Gradle wrapper from the repository root:

```sh
./gradlew docker                 # Build Docker images for all modules
./gradlew :faker:docker         # Build one module image
./gradlew :mongo:docker
./gradlew :sbt-agent:docker
./gradlew test                   # No test sources currently exist
```

There is no configured lint task or test suite in the repository. If tests are added to a module, run one test with Gradle's standard selector, for example:

```sh
./gradlew :faker:test --tests 'com.example.SomeTest.someCase'
```

The CI build uses JDK 11, Docker Buildx, and QEMU before invoking `docker`; use JDK 11 locally for parity with `.github/workflows/ci.yml`.

## Local service workflows

For the fake API, run `docker compose up` from `faker/`. It serves port 3001 and mounts `faker/rest` into the container. To use another resource directory, set `FAKER_PATH` and mount the same container path; `URL_PATH` changes the API base path.

For MongoDB, run `docker compose up` from `mongo/`. The compose file uses `sriramsundhar/mongo:main`, exposes port 27017, and starts MongoDB with replica-set and key-file options. Use `mongo/config.sample` as the shape of local connection settings. Backups and restores are shell scripts under `mongo/sync/scripts`; the backup script expects collection query JSON files and MongoDB credentials, while the restore script consumes BSON files under `/tmp/dw`.

## Repository-specific conventions

- Add a new image by creating a module directory, adding its Dockerfile and module `build.gradle.kts`, and registering the module in `settings.gradle.kts`. The module build should follow the existing Docker plugin pattern and provide `publish` through `dockerTagsPush`.
- Keep image naming and tags consistent with the existing `sriramsundhar/<project>` convention. The `latest` and version tags are configured in each module rather than centrally.
- Docker build context is deliberately assembled with `copySpec`: `faker` copies `resources`, and `mongo` copies `sync`. Changes to runtime files must preserve those paths or update the corresponding Docker build configuration.
- The fake server is configured through environment variables rather than Gradle properties. Keep `FAKER_PATH` aligned with the mounted path and `URL_PATH` with the desired route prefix.
- Release configuration in the root build requires the `main` branch. The workflow builds on pushes, and on `main` it runs the Gradle release flow, which creates a release and publishes Docker tags. Release publishing requires the Docker and CI SSH credentials configured by the workflow secrets.
- The Gradle project version is taken from `gradle.properties`; use the Gradle Release Plugin flow rather than manually changing release tags or version commits.
