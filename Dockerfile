

# selects java 8 as base image

FROM openjdk:8-jdk-alpine


# Allows us to specify a place on the local machine that container data should be persisted to
# So that it can be accessed even after the container shuts down (or while its running)
VOLUME /tmp

# passes the profile as an argument in --build-arg
ARG profile=local

# sets an environment variable for resulting container
ENV PROFILE=${profile}

# copies created jar file into the image
COPY target/*.jar skyview.jar

EXPOSE 5000

# run during image building
RUN echo 'Making SkyView image...'
RUN ls

# ran when the container starts
CMD echo 'Successfully created image!'

ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=${PROFILE} skyview.jar" ]

