docker-compose build --no-cache
docker-compose up -d
mysql -u root -precipeRoot -h127.0.0.1 -P9907 -> root user
mysql -u recipe_user -precipe_user -h127.0.0.1 -P9907 -> recipe_user