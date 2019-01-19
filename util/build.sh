#!/bin/bash
set -e

chmod 400 /root/.ssh/id_rsa

git clone git@github.com:ScottG489/diff-info-service.git
cd diff-info-service
./gradlew build fatCapsule

docker build -t diff-info-service:latest .
docker push scottg489/diff-info-service:latest

# Things below we may only want to run once and not for every deployment
APP_ID=$(head /dev/urandom | tr -dc a-z0-9 | head -c 7 ; echo '')
aws s3 mb s3://app-versions-$APP_ID
aws s3 cp util/app.aws.json s3://app-versions-$APP_ID/app.aws.json

# Creates application as well with given file
aws elasticbeanstalk create-application-version --application-name $APP_ID --version-label vlbl_1 --auto-create-application --process --source-bundle S3Bucket=app-versions-$APP_ID,S3Key=app.aws.json
# create template to refer to when creating environment
aws elasticbeanstalk create-configuration-template --application-name $APP_ID --template-name my_tmpl_name --solution-stack-name "64bit Amazon Linux 2018.03 v2.12.6 running Docker 18.06.1-ce"
# dns name needs to be globally unique
# can use aws elasticbeanstalk check-dns-availability --cname-prefix my-cname to check if it's available
aws elasticbeanstalk create-environment --cname-prefix $APP_ID --application-name $APP_ID --template-name my_tmpl_name --version-label vlbl_1 --environment-name $APP_ID-env
