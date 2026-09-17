FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive \
    PATH=/root/.local/bin:/root/.local/share/pnpm:/usr/local/bin:${PATH}

RUN apt-get update \
    && apt-get install --no-install-recommends -y \
        bash \
        ca-certificates \
        curl \
        git \
        jq \
        python3 \
        python3-pip \
        python3-venv \
        ripgrep \
        unzip \
        xz-utils \
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
