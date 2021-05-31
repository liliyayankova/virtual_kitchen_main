FROM mysql:8.0

ENV MYSQL_DATABASE=OrderDb \
    MYSQL_ROOT_PASSWORD=orderRoot

COPY ./createBasketAndOrders.sql /docker-entrypoint-initdb.d/