#!/bin/sh
projectName="skyview"; # in case people want to name a derivative
containerName=$1;
if ! command -v mvn > /dev/null; # checks if maven is installed
then
	echo "$0: maven not installed. aborting";
	exit;
fi

if [ -z $containerName ];
then
	echo "$0: requires container name. aborting";
	exit;
fi

echo "$0: now packaging $projectName into jar...";
mvn clean package > /dev/null; # packages the project into a jar file

echo "$0: containerizing $projectName...";
docker build . -t $projectName # > /dev/null; # builds container
docker run -d --name $containerName $projectName  > /dev/null; # starts the container
echo "$0: started container successfully";
