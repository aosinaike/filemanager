FROM adoptopenjdk:11-jre-hotspot as  fileManager
COPY target/FileManager-0.0.1-SNAPSHOT-exec.jar FileManager-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=target/FileManager-0.0.1-SNAPSHOT-exec.jar
ADD ${JAR_FILE} FileManager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/FileManager-0.0.1-SNAPSHOT.jar"]
EXPOSE 9090