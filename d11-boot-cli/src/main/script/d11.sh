#!/bin/bash

environments=("production" "development")
environment="production"

authenticationCommands=("photos" "squads" "transferwindow" "upload")

username={username}

command=$1
shift

args=""

while getopts ":e:u:d:y:" option; do
  case $option in
  e)
    if [[ " ${environments[*]} " =~ $OPTARG ]]; then
      environment=$OPTARG
    else
      echo "Invalid argument $OPTARG for option -e."
      exit 1
    fi
    ;;
  u)
    username=$OPTARG
    ;;
  d)
    args+=" -d=$OPTARG"
    ;;
  y)
    args+=" -y=$OPTARG"
    ;;
  :)
    echo "Option -$OPTARG needs an argument."
    exit 1
    ;;
  *)
    echo "Invalid option -$OPTARG."
    exit 1
    ;;
  esac
done

if [[ " ${authenticationCommands[*]} " =~ $command ]]; then
  args+=" -u=$username -p"
fi

echo $args
java -jar -Dspring.profiles.active=$environment ../libs/d11-boot-cli-{version}.jar $command $args
