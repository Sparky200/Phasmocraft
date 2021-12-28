FROM openjdk:17.0.1
WORKDIR /server
CMD java -Xmx4096M -Xms256M -jar server.jar nogui