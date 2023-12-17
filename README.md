# task-microservice-springboot
microservice using springboot


### Completion:
1. User crud using Restful API.
2. Caching using Redis.
3. Generate events through user activites e.g. create, update using Kafka. Another kafka consumber reveive those event name "user-processor-service".
4. Service registry and API gateway added.
5. Dockerize all the services using "jib-maven-plugin".
6. Docker compose added for orchestrating all services.

### Run using docker:
1. To run this project using docker just open the project, go to terminal and type "docker compose up -d"
### Run without docker:
1. commented out all custom services write by me in docker-compose.yml file.
2. Go to terminal and type "docker compose up -d"
3. Run all the service individually using any IDE or mvn command from terminal.

### Build:
1. For Dockerize build run "mvn clean compile jib:build" in all the services

### Test:
1. There is a post man collection file in root to test the API.