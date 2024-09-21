# Commands

to build and run the app and the database:
```sh
./gradlew build && docker-compose up -d
# OR for Windows Powershell
.\gradlew.bat build; docker-compose up -d
```

Afterwards you can build and restart the app:
```shell
./gradlew build && docker-compose restart app
# OR
.\gradlew.bat build; docker-compose restart app
```


This ended up being my preffered way to do it.

When you made code changes, in the terminal stop the docker-compose using Ctrl-c
and then rerun it by pressing the up arrow, to reuse the latest command and hit enter.

In the future I will make it so the docker environment
is going to build the app.

### Database Management through CLI

```shell
# Get in the docker container running PostgreSQL with the shell
docker exec -it <container_name_or_id> /bin/bash
docker exec -it <container_name_or_id> /bin/sh

# To find the name or id of the container
docker ps

# I think you can also give just part of the id and not the whole thing
docker exec -it 869 /bin/bash # actually is 869a000...
```

# Troubleshooting 

Problem: gradlew has no permission to run?
Fix: chmod u+x ./gradlew

Problem: docker no permissions
Fix: add current user to group

Problem: docker has no permissions after adding to group
Temporary fix: newgrp docker

---

## Some docker commands I tried + explanations

```sh
# I had a little journey with setting up docker
# I ended up creating a environment in docker to run the app
# but the build step is technically still local

# The following is the command you can run to build and run
# the app stack
./gradlew build && docker-compose up
# OR for Windows Powershell
.\gradlew.bat build; docker-compose up

# Using these will help you avoid running multiple commands,
# or having to rebuild docker images on each code change

docker-compose up # will use the docker-compose.yml

# will use the specified file to setup
docker-compose -f docker-compose.all.yml up

# EXTRA: To use just one of the Dockerfiles you can do:
docker build -t myapp:latest .
docker run -d -p 8080:8080 --name myapp-container myapp:latest
# But just use docker-compose files to avoid these cmd line things

```
