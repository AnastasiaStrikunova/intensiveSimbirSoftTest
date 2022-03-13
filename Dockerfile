# backend
# устанавливаем самую лёгкую версию JVM 11
FROM adoptopenjdk/openjdk11:alpine-jre

# указываем точку монтирования для внешних данных внутри контейнера (как мы помним, это Linux)
VOLUME /tmp

# внешний порт, по которому наше приложение будет доступно извне
EXPOSE 8080


# добавляем джарник в образ под именем Backend.jar
ADD ./target/private_notes-1.0-SNAPSHOT.jar private_notes.jar

# команда запуска джарника
ENTRYPOINT ["java","-jar","/private_notes.jar"]