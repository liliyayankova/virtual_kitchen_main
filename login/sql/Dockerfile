FROM mysql:8.0

ENV MYSQL_DATABASE=userdb \
    MYSQL_ROOT_PASSWORD=userRoot

COPY ./create_user.sql /docker-entrypoint-initdb.d/