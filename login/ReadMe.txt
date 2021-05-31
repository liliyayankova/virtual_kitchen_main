Install Docker:
https://www.docker.com/products/docker-desktop

Have Docker running - either open the application or in terminal (docker start)

docker ps -> check running containers
docker ps -a -> check all containers

Go into the main folder of this project
docker-compose down -v (stops the running containers and effectively deletes the database volumes)
docker-compose build --no-cache (builds fresh images)
docker-compose up -d (executes the docker-compose.yaml file)

To check if the containers are running - docker ps

Visit -> http://localhost:8000

To stop the containers - docker-compose down

For user service database:
mysql -u root -puserRoot -h127.0.0.1 -P9906 -> root user
mysql -u service_user -pservice_user -h127.0.0.1 -P9906 -> service_user