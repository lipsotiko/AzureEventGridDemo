# AzureEventGridDemo
Demonstrating how to leverage Azure Event Grid to support communication between microservices.
Both services are in the same web application for simplicity's sake; but, could easily be separated.

[Try It Out](https://azure-event-grid-demo.herokuapp.com)

## Local Development
- Java 14
- Maven
- NPM

### Running the application

- Backend: `mvn spring-boot:run`
- Frontend: `cd client && npm install && npm run serve`
- Build: `./build.sh`

### Environment Variables

- SERVICE_A_TOPIC_ACCESS_KEY
- SERVICE_B_SUBSCRIPTION_TOKEN

### Microsoft Docs

- https://docs.microsoft.com/en-us/azure/event-grid/webhook-event-delivery
- https://docs.microsoft.com/en-us/azure/event-grid/event-schema
