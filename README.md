# Cimr: Chat microservice

## Prerequisites

```bash
mvn clean package
//poženi docker image chat
java -jar chat-api-1.0.0-SNAPSHOT.jar

//docker building
docker build -f Dockerfile_with_maven_build -t frijugsincek/chat:latest .
docker push frijugsincek/chat:latest   

docker network create cimr

docker run -d --name chat -e POSTGRES_USER=dbchat -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=chat -p 5432:5432 --network cimr postgres:13
```

geslo za povezavo na elephantdb bazo je treba ročno nastavit v k8s configuraciji dokler ne uspostavimo secretov
