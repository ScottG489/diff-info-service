# diff-info-service
A service to get information from diffs.

## Building and deploying application from scratch
Here is an example of how to build and deploy the application
```bash
docker build -t dis .
docker run -v /var/run/docker.sock:/var/run/docker.sock -it dis "$(cat id_rsa)" "$(cat credentials)" "$(cat config.json)"
```