#!/usr/bin/env bash

cd ui && npm ci && npm run build
cd ..
rm -rf src/main/resources/public
cp -r ui/dist src/main/resources/public
env mvn clean install
