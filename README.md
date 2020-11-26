# CleanCodeTest Masivian

Author: Daniel Quintero

## Introduction

To accomplish with technical test I developed an  API Web RESTful with Spring Boot and Redis.

## Prerequisites

The Endpoints were developed in Spring Boot and as data persistence mechanism I used Redis.
  - Redis must be installed to start database server.
  - Maven must be installed to compile and run Spring Boot app.
  
## Recomendattions

- Spring Boot Aplication runs by port 8080 by default and Redis runs by port 6379 by default, if other services are running on those ports, they must be stopped.
- The proposal is developed in a Maven proyect. For view source code I recommend STS or IntelliJ IDEA.

## Instructions

1. Clone the repository:

        git clone https://github.com/danielq20/CleanCodeMasivian.git
      
2. Start Redis server:

        redis-server 
        
   Now we must start the Spring Boot application. For this we need to build the application jar, using Maven. Once the jar is built we can run it.

4. Build the application jar. From the terminal bash we access the cloned repository:

        mvn clean package  

5. Run the jar:

        java -jar target/rouletteServices-0.0.1-SNAPSHOT.jar        
   
        
    Now we are ready to test the endpoints.        
## Endpoints

There are 5 Endpoints:

- /roulettes                                     POST
- /roulettes/{rouletteId}/openRoulette           PUT
- /roulettes/{rouletteId}/{bet}/{value}/bet      PUT
- /roulettes/{rouletteId}/closeRoulette          PUT
- /roulettes                                     GET

Endpoints could be tested by Postman. Remember to put the correct request method.
### First Endpoint 

- /roulettes  POST

Endpoint that allows the creation of a new roulette. It does not need any input. Returns the id of the created roulette.

For test in Postman (remember put POST):

      http://localhost:8080/roulettes
  
  - Response 200: The new roulette id
  
         ae3ad603-1557-4636-8459-5af4f8525adc
  

### Second Endpoint 
 
 - /roulettes/{rouletteId}/openRoulette PUT
 
Roulette opening endpoint. It receives from input the id of a roulette. Returns a message confirming that the operation was successful or denied. After making this request, betting requests are allowed.
 
For test in Postman (rembember put PUT and id of a previously created roulette):
 
      http://localhost:8080/roulettes/{rouletteId}/openRoulette 
    
   - Response 200: Succesful operation
  
         Succesful operation
         
   - Response 400: Operation Denied (Roulette not found or roulette already opened)
  
         Operation denied      
  
### Third Endpoint
  
  - /roulettes/{rouletteId}/{bet}/{value}/bet PUT
  
Roulette betting endpoint. You can bet a number (from 0 to 36) or color (red or black) and a bet value (maximum $ 10,000). It receives inputs, the id of the roulette, the bet (either color or number), and the value of the bet. In HEADERS a valid user id is passed.
  
For test in Postman (remember put PUT, id of previously created roulette, valid number or color and valid bet value, in Postman's headers section put as key userId and as value a number that represents the id of a user):

      http://localhost:8080/roulettes/{rouletteId}/{bet}/{value}/bet
      
   - Response 200: Succesful operation
  
         The bet was made
         
   - Response 400: Operation Denied (Roulette is closed or Bet properties are invalid)
  
         The bet wasn't made because roulette is closed or bet is not valid    
      
   - Response 400: Operation Denied (Roulette not found)
  
         The bet wasn't made because id of roulette doesn't exist    
   
### Fourth Endpoint
   
   - /roulettes/{rouletteId}/closeRoulette PUT
   
Endpoint for closing bets on a roulette. It receives from input the id of a roulette wheel. Returns the result of each of the bets made from the opening of the roulette to the closing of this.

For test in Postman (rember put PUT and id of previously created roulette):

       http://localhost:8080/roulettes/{rouletteId}/closeRoulette
       
       
   - Response 200: Succesful operation
                              
          {
                "id": "dd48856a-9c7f-4614-b4f0-58afc2e5be33",
                "status": "Closed",
                "betsOfRoulette": [
                    {
                        "color": null,
                        "number": "15",
                        "result": "Lost",
                        "value": 600
                    },
                    {
                        "color": "black",
                        "number": null,
                        "result": "Won",
                        "value": 600
                    },
                    {
                        "color": "red",
                        "number": null,
                        "result": "Lost",
                        "value": 600
                    }
                ],
                "result": {
                    "color": "Black",
                    "number": "31"
                }
          }
      
   - Response 400: Roulette not found
   
### Fifth Endpoint

- /roulettes  GET

Roulette list endpoint created with their states. You don't need any input.

For test in Postman (remember put GET):

        http://localhost:8080/roulettes
        
   - Response 200: Succesful operation
  
          [
              {
                  "id": "b0ca9971-32cb-4550-9d96-075e0e5c8900",
                  "status": "Closed",
                  "betsOfRoulette": [],
                  "result": null
              },
              {
                  "id": "50f8a8b3-6e0f-458a-aa60-99c7024321be",
                  "status": "Closed",
                  "betsOfRoulette": [],
                  "result": null
              },
              {
                  "id": "dd48856a-9c7f-4614-b4f0-58afc2e5be33",
                  "status": "Closed",
                  "betsOfRoulette": [
                      {
                          "color": null,
                          "number": "15",
                          "result": "Lost",
                          "value": 600
                      },
                      {
                          "color": "black",
                          "number": null,
                          "result": "Won",
                          "value": 600
                      },
                      {
                          "color": "red",
                          "number": null,
                          "result": "Lost",
                          "value": 600
                      }
                  ],
                  "result": {
                      "color": "Black",
                      "number": "31"
                  }
              },
              {
                  "id": "ae3ad603-1557-4636-8459-5af4f8525adc",
                  "status": "Closed",
                  "betsOfRoulette": [],
                  "result": null
              } ]     
        
## Notes

- I didn't use ORMs.
