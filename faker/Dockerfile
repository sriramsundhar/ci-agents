FROM node:14.2.0-alpine3.10

WORKDIR /home/node/faker
COPY resources .

RUN apk add --no-cache python3 make g++ \
    && npm install \
    && apk del python3 make g++
CMD [ "node", "index.js" ]

EXPOSE 3001