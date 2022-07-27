#!/bin/sh
projectName="skyview"; # in case people want to name a derivative
containerName=$1;
if ! command -v mvn > /dev/null; # checks if maven is installed
then
	echo "$0: maven not installed. aborting";
	exit;
fi

if ! command -v docker > /dev/null;
then
	echo "$0: docker not installed. aborting";
	exit
fi

if [ -z $containerName ];
then
	echo "$0: requires container name. aborting";
	exit;
fi

echo "$0: packaging $projectName into jar...";
mvn clean package &> /dev/null; # packages the project into a jar file

echo "$0: containerizing $projectName...";
docker build . -t $projectName > /dev/null; # builds container
docker run -d --name $containerName $projectName > /dev/null; # starts the container
ip=$(docker inspect $containerName | grep "IPAddress\": "\" | head -n 1 | cut -f2- -d: | tr -d '"' | tr -d ','| tr -d ' ') # used to retrieve the ip address from the container
echo "$0: started container successfully. Use $ip:5000 to access the backend";
