# App Engine Standard Java 8 - Pub/Sub Sample


## Endpoints
- GET `/` : Homepage provides a form to submit a message to be published
- GET `/info` : Returns environment information
- GET `/publish` : returns message ID on successfully publishing a message


## Setup

- Make sure [`gcloud`](https://cloud.google.com/sdk/docs/) is installed and authenticated.
- [Enable](https://console.cloud.google.com/launcher/details/google-cloud-platform/cloud-pub-sub)
 Google Cloud Pub/Sub API in your Google Cloud Platform project.
- Create a topic:
```
       gcloud beta pubsub topics create <your-topic-name>
```
- Update the environment variables `PUBSUB_TOPIC` in [`appengine-web.xml`](src/main/webapp/WEB-INF/appengine-web.xml).

## Run locally
Set the following environment variables and run using shown Maven command. You can then
direct your browser to `http://localhost:8080/`.

```
export PUBSUB_TOPIC=<your-topic-name>
mvn clean appengine:run
```

## Deploy

```
mvn clean appengine:deploy
```
Direct your browser to `https://pubsub.your-project-id.appspot.com`