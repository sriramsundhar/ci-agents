FROM mongo:8
#COPY ./sync/mongoLocal/dw/* /tmp/dw
COPY ./sync/scripts/backup.sh /tmp/scripts/backup.sh
COPY ./sync/scripts/restore.sh /docker-entrypoint-initdb.d/restore.sh
