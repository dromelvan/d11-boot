# D1 Boot Application

build.sh:

docker build -t d11/d11-boot-v4 .
docker image save -o d11-boot-v4.tar d11/d11-boot-v4

Copy tar to lib directory

Deploy:

docker container stop d11-boot-v4
docker container rm d11-boot-v4
docker image rm d11/d11-boot-v4
docker load -i ../lib/d11-boot-v4.tar

Run:

docker run -d -v $(dirname $(pwd))/files:/files -v $(dirname $(pwd))/log:/log -p 8080:8080 --name d11-boot-v4 --network=d11 -e TZ=Europe/Helsinki -e JASYPT_ENCRYPTOR_PASSWORD=$JASYPT_ENCRYPTOR_PASSWORD d11/d11-boot-v4
