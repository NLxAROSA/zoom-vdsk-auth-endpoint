# Zoom Video SDK Authentication Endpoint example
Java implementation/variation of https://github.com/zoom/videosdk-auth-endpoint-sample
## Running locally
Prerequisite: JDK 22+ installed. May work on lower versions, but this is what the repo is tested with.
1. Clone this repo
2. Copy the '.env.example' file to a new file called '.env'
```shell
cp .env.example .env
```
3. Replace the placeholders in the '.env' file with your SDK key and secret
4. Run the app
```shell
source .env && ./mvnw spring-boot:run
```
5. Endpoint will be available at http://localhost:8080
## Usage
See https://github.com/zoom/videosdk-auth-endpoint-sample/blob/master/README.md

Note that only a subset of parameters (the required ones) is implemented in this repo to match compatibility and act as a drop-in replacement authentication endpoint for this sample app: https://github.com/zoom/videosdk-ui-toolkit-javascript-sample