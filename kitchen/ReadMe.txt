docker-compose up -d --build
mysql -u root -pkitchenRoot -h127.0.0.1 -P9908
mysql -u kitchen_user -pkitchen_user -h127.0.0.1 -P9908
