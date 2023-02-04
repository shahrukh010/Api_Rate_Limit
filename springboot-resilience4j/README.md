# Rate Limit with Resilience4J

## Application Description:
Using the Spring Boot Resilience4j RateLimiter Module:

Assume that we are building a website for an airline to allow its customers to search for and book flights.
Our service talks to a remote service encapsulated by the class FlightSearchService.

Let’s see how to use the various features available in the RateLimiter module. 
This mainly involves configuring the RateLimiter instance in the application.yml file 
and adding the @RateLimiter annotation on the Spring @Service component that invokes the remote operation.
In production, we’d configure the RateLimiter based on our contract with the remote service.

# Basic Example
Suppose our contract with the airline’s service says that we can call their search API at 2 rps (requests per second). 

For this project required dependencies
# io.github.resilience4j

Run the SpringbootResilience4j Application.
# Local Setup:
    Install Java 11
    Install Maven
Clone the repo and run the below command.

 mvn clean install

To start the application: 
   Choose Any IDE (Intellij Idea,StS)






