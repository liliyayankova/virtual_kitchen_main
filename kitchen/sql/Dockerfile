FROM mysql:8.0

ENV MYSQL_DATABASE=KitchenDb \
    MYSQL_ROOT_PASSWORD=kitchenRoot

COPY ./createKitchenDb.sql /docker-entrypoint-initdb.d/