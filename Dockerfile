FROM java:8



ADD ./build/libs/CCUserPreferencesService.jar /root

RUN chmod 500 /root/CCUserPreferencesService.jar
WORKDIR /root

EXPOSE 4000 8888

ENTRYPOINT [ "java", "-jar", "/root/CCUserPreferencesService.jar", "2>&1" ] 
