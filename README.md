# Facts API
## *Karthik Viswanathan*

## Instructions
To build and run:
* Checkout the project
* build the project using `gradlew clean build`
  * this produces an executable jar `build/libs/facts-api-0.0.1-SNAPSHOT.jar`
* run the jar as follows
  * `java -jar -Dtranslate.api.key=<API_KEY> build/libs/facts-api-0.0.1-SNAPSHOT.jar` where `<API_KEY>` is a google API key which is valid for the Google Translations API

To use the API
* Swagger documentation can be found at `http://localhost:8080/swagger-ui.html` 
* The API uses in-memory username/password authentication. Two roles are present: `ADMIN` and `USER`. Only the `ADMIN` role can check the application status at `http://localhost:8080/status` 
* You may use a rest client of your choice. The commands below use the `curl` command-line utility as an example
* To use the API, first login as follows
  * `curl -X POST -d username=admin -d password=adminPass -c cookies.txt http://localhost:8080/login`
  * To login as a non-admin user, use `username=user and password=userPass`
* The application status can be checked (only by the `admin` user) using: `curl -b cookies.txt http://localhost:8080/status`. This returns json showing the total number of facts fetched and the number of unique facts loaded into the application
  * If the status is `ERROR` this indicates a problem communicating with the random useless facts API and the Facts API jar may have to be restarted
* The list of fact ids can be retrieved using: `curl -b cookies.txt http://localhost:8080/facts`
* A single fact can be retrieved using `curl -b cookies.txt http://localhost:8080/facts/<factid>` where `<factid>` is the fact ID. This retrieves the fact in its original language
* To retrieve a fact translated into another language, use `curl -b cookies.txt http://localhost:8080/facts/<factid>?lang=<language>` where `<language>` is the 2-letter ISO code for the language. If the specified language is the same as the original language of the fact, the original fact is retrieved. For a translated fact, the permalink field in the response will be null as the translation is not hosted on a permanent link
  * Fact translation will not work unless a valid API Key is set when running the jar file
   