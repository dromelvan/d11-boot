# D1 Boot Application

docker build -t d11/d11-boot .
docker image save -o d11-boot.tar d11/d11-boot

docker image load -i d11-vue.tar

docker run -d -v $(dirname $(pwd))/files:/files -v $(dirname $(pwd))/log:/log -p 8080:8080 --name d11-boot --network=d11 -e TZ=Europe/Helsinki -e JASYPT_ENCRYPTOR_PASSWORD=$JASYPT_ENCRYPTOR_PASSWORD d11/d11-boot
