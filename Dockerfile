FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app
COPY target/vehicle-geolocation-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java","-jar","/app/vehicle-geolocation-0.0.1-SNAPSHOT.jar", "fabware.vehicle-geolocation.VehicleGeolocationApplication.java"]
