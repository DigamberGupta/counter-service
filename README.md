# Counter service

 - Counter service is a spring boot application which provides API's to store and retrieve number of the count for certain kubernetes kind and metadata name.
   (Use case: A kubernetes custom controller could store the information about the number of pod/resource by using the Counter service API)
 - The counter service follow the <b>API Contract First</b> process, which means the open-api specification specify using openAPI 3.0
 - This service demonstrate a way to generate controller using OpenApi specification Yaml 
 - Counter Service also provides a way to expose you OpenAPI specification using an external Swagger UI

## Requirements
- JAVA 11
- Gradle 7.1.1

### Open-API Specification and Swagger UI
- Open API specification http://{host}:{port}/api-docs/counter-api
- To access Swagger UI, visit to http://{host}:{port}/api-docs/ and click on the counter-api link in web browser which will redirect to the public Swagger-UI
  (https://editor.swagger.io/?url=http://{host}:{port}/api-docs/counter-api)

* ![Screenshot of Counter service SwaggerUI](https://github.com/DigamberGupta/counter-service/blob/master/src/main/resources/static/CounterServiceSwaggerUI.PNG)


### Following useful command,

 - ```gradlew clean build```  (to build the gradle project from the)
 - ```gradlew bootRun``` (to run the application)
 - ```gradlew docker``` (to build a docker image) 
 - ```kubectl apply -f src\main\k8s\deployment.yaml``` (to apply the kubernetes config to run your pods on kubernetes)


