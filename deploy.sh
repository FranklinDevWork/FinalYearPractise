#!/bin/bash

### allows the changing of the db username and password and any other secure data before being the war file
### this means the username and password and any other secure data will never be stored in git or visible to the user
## will restart the docker apps, using itermocil will load in the logs in panels

# properties:
# $1 is for the db_username

sed -i '' -e 's/{db_username}/'$1'/g' src/main/webapp/WEB-INF/classes/configuration.txt
./gradlew clean build
sed -i '' -e 's/'$1'/{db_username}/g' src/main/webapp/WEB-INF/classes/configuration.txt
docker-compose restart
itermocil final-year