rm -rf ./build/libs
./gradlew bootJar
sudo docker-compose down -v
sudo docker-compose build --no-cache
sudo docker-compose up -d --build
sudo docker-compose logs -f