./gradlew bootJar
docker build -t mrbedrockpy/neocodebackend-app:latest .
docker push mrbedrockpy/neocodebackend-app:latest