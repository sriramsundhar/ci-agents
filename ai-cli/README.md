# AI CLI image

This image is based on `ubuntu:24.04` and includes common shell tools plus:

- Aider (`aider`)
- Antigravity CLI (`agy`)
- Claude Code (`claude`)
- Codex CLI (`codex`)
- Cody CLI (`cody`)
- Cursor CLI (`agent`)
- Gemini CLI (`gemini`)
- Warp Agent CLI (`warp`)
- OpenJDK 25 (Eclipse Temurin)
- Apache Maven 3.9.16 (`mvn`)

Claude.ai is a web service rather than a separate installable CLI; Claude Code is the terminal client included for Anthropic access.

Build it from the repository root with:

```sh
./gradlew :ai-cli:docker
```

Run it against the current repository with credentials supplied at runtime:

```sh
docker run --rm -it \
  -v "$PWD:/workspace" \
  -w /workspace \
  sriramsundhar/ai-cli:latest
```

The tools are installed, but authentication is intentionally not baked into the image. Configure each tool's API key or interactive login when the container runs.
