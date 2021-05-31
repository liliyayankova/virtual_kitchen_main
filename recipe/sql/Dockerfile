FROM mysql:8.0

ENV MYSQL_DATABASE=RecipeDb \
    MYSQL_ROOT_PASSWORD=recipeRoot

COPY ./createRecipeDb.sql /docker-entrypoint-initdb.d/