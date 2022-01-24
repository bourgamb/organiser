# The Toda Organiser :-) 

**To build and install locally:**<br>
Execute `mvn install` in root directory

**To run please execute below command from root directory containing pom:**<br>
`mvn spring-boot:run -Dspring-boot.run.profiles=local`

To Test:
Once start up is completed go to following URL: <br>
`http://localhost:8080/swagger-ui.html`

---
# Create new user and todo
- Go to Organiser-controller and run the `**GET** /organiser/todos`.
This will retrieve all users/tasks bootstraped during startup. 

- To create a new user and associated todo:
Go to `/organiser/users/save` endpoint.

- Copy and paste below example JSON which will create new user 
associated todo.

```yaml
{"userFirstName":"Frank","userSurname":"Marshall","tasks":[{"taskName":"Haircut","taskDescription":"Get haircut again!","taskStatus":"IN_PROGRESS"}]}
```

- hit execute within swagger.<br>
- Go to Organiser-controller and run the `GET /organiser/todos`. This will retrieve all users/tasks to verify new user
___
# Update todo with new status "Completed"

- Go to Organiser-controller and run the `**POST** /organiser/todos/update`.
  
- Copy and paste below example JSON which will create new user
  associated todo.

```yaml
{"id":"4","userId":"4", "taskName":"Haircut","taskDescription":"Get haircut again!","taskStatus":"COMPLETED"}
```

- hit execute with swagger. 

- Go to Organiser-controller and run the `**GET** /organiser/todos`.
  This will retrieve all users/tasks.
- Haircut for Frank Marshall users should be set to completed

___
# Remove todo
- Go to Organiser-controller and run the `**PUT** /organiser/{userId}/todos/{taskId}/remove`
- Include userId (4 for Frank Marshall) and taskId (4 for Haircut task) as parameters (can be obtained from `GET /organiser/todos`)

- hit execute with swagger.

- Go to Organiser-controller and run the `**GET** /organiser/todos.`
  This will retrieve all users/tasks - haircut todo should be removed

---
#Design Considerations 
- Springboot project using constructor injection for dependency injection
- Strategy design pattern used
- Concrete Command objects created to decouple UI instantiated object from backend JPA entities
- Spring Converters created to convert JPA entities from/to command objects
- Base JPA MappedSuperClass created to abstract id and audit columns from inheriting entities
- Audit Listeners included to enable auditing of entities
- Swagger configured with APIInfo details
- Enum for Status of todos
- Spring Data JPA repositories created
- Task and User services interface for decoupling implementation from behaviour definition
- Custom AdviceController created to handle custom exception UserNotFound
- data-h2.sql provided to load sample data at startup
- banner.txt include for Organiser banner
- logback-spring.xml configured ensuring no exposing to log4shell security issue CRE
- Maven plugins configured:
  - Auto-Clean
  - Surefire configured for Unit tests
  - Failsafe for running integration tests
  - Jacoco plugin to generate UT coverage reports

- Unit Tests / Integration Test
  - Standalone MockMvc for testing endpoints
  - Converters UTs run very quickly using Mockito

---
#Future Improvements 
- Introduce spring-security to include Authentication/Authorization of users
- Introduce OAuth token
- Encrypt any property sensitive info using spring-cloud
- Include input validation on user id / task id using hibernate-validator and provide "friendly" client feedback
- Expand out exceptionHandler to capture any corner cases
- Centralise creation of beans in config class instead of using spring annotations
  - This would enable use of Quarkus to enable native builds for faster startup and reduced memory footprint
- Investigate use of spring native to reduce war size and start up
- CI/CD integration using CircleCI 