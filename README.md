# available-flights

API Documentation:

https://documenter.getpostman.com/view/3868288/S11LsHwx

Description:

This is an API aggregation for flights on Back-end side. When you want to send query to this API, you should enter page size and count number in order to get pagable data. In addition, you can get easily filtered data when you enter related query parameters like arrival, destination or times. The API gives you in a way that closer time for arrival flight at top.

Installation:
  
  Build:

    mvn clean install
  
  Run:

    java -jar ${PROJECT_FOLDER}/target/search-flight-0.0.1-SNAPSHOT.jar
