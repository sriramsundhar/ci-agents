FROM ubuntu:24.04

ARG TARGETARCH
ARG MAVEN_VERSION=3.9.16
ENV DEBIAN_FRONTEND=noninteractive \
    JAVA_HOME=/usr/lib/jvm/temurin-25-jdk-${TARGETARCH} \
    MAVEN_HOME=/usr/local/maven \
    PATH=/root/.local/bin:/root/.local/share/pnpm:/usr/local/maven/bin:/usr/local/bin:${PATH}

RUN apt-get update \
    && apt-get install --no-install-recommends -y \
        bash \
        ca-certificates \
        curl \
        git \
        gnupg \
        jq \
        python3 \
        python3-pip \
        python3-venv \
        ripgrep \
        unzip \
        xz-utils \
    && curl -fsSL https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor -o /usr/share/keyrings/adoptium.gpg \
    && echo "deb [signed-by=/usr/share/keyrings/adoptium.gpg] https://packages.adoptium.net/artifactory/deb $(. /etc/os-release && echo "$VERSION_CODENAME") main" > /etc/apt/sources.list.d/adoptium.list \
    && apt-get update \
    && apt-get install --no-install-recommends -y temurin-25-jdk \
    && mkdir -p "${MAVEN_HOME}" \
    && curl -fsSL "https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" \
        | tar xz -C "${MAVEN_HOME}" --strip-components=1 \
    && mvn -version \
    && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install --no-install-recommends -y nodejs \
    && python3 -m pip install --break-system-packages --no-cache-dir pipx \
    && pipx ensurepath \
    && pipx install aider-chat \
    && npm install --global @openai/codex @google/gemini-cli @sourcegraph/cody-cli \
    && curl -fsSL https://antigravity.google/cli/install.sh | bash -s -- --dir /root/.local/bin \
    && curl -fsSL https://claude.ai/install.sh | bash \
    && curl -fsS https://cursor.com/install | bash \
    && ln -sf /root/.local/bin/agy /usr/local/bin/agy \
    && ln -sf /root/.local/bin/claude /usr/local/bin/claude \
    && ln -sf /root/.local/bin/agent /usr/local/bin/agent \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace
ENTRYPOINT ["/bin/bash"]
