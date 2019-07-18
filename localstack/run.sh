#!/bin/bash

cd "$(dirname "$0")"

export SERVICES=s3,sns,sqs,dynamodb
export TMPDIR=/private$TMPDIR
export PORT_WEB_UI=8080
export DEBUG=0
docker-compose up -d