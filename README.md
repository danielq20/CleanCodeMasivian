# CleanCodeTest Masivian

Author: Daniel Quintero

## Introduction

To accomplish with technical test I developed an  API Web RESTful with Spring Boot and Redis.

## Prerequisites

The Endpoints were developed in Spring Boot and as data persistence mechanism I used Redis.
  - Redis must be installed to start database server.
  - Maven must be installed to compile and run Spring Boot app.
  
## Recomendaciones

- Spring Boot Aplication runs by port 8080 by default and Redis runs by port 6379 by default, if other services are running on those ports, they must be stopped.
- The proposal is developed in a Maven proyect. For view source code I recommend STS or IntelliJ IDEA.

## Instrucciones

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

Se procede a probar mediante Postman. Recordar poner el método de petición correcto.
### Primer Endpoint 

- /roulettes  POST

Endpoint que permite la creación de una nueva ruleta. No necesita ningún input. Devuelve el id de la rouleta creada. Con este endpoint se crea la colección Roulette en base de datos.

Para probar en Postman (colocar verbo POST):

      http://localhost:8080/roulettes

 ![](images/img4.JPG)

### Segundo Endpoint 
 
 - /roulettes/{rouletteId}/openRoulette PUT
 
Endpoint de apertura de ruleta. Recibe de input el id de una ruleta. Devuelve un mensaje que confirme que la operación fue éxitosa o denegada. Después de realizar este request se permite peticiones de apuestas. Para probar escogeré el id de la ruleta anteriormente creada, 163786231565777267
 
Para probar en Postman (colocar verbo PUT y id de una ruleta creada anteriormente):
 
      http://localhost:8080/roulettes/{rouletteId}/openRoulette
      
Ejemplo:

      http://localhost:8080/roulettes/163786231565777267/openRoulette

  ![](images/img5.JPG)     
  
  Si probamos una vez la operación será rechazada, ya que la ruleta está abierta.
  
  ![](images/img6.JPG)    
  
### Tercer Endpoint
  
  - /roulettes/{rouletteId}/{bet}/{value} PUT
  
Endpoint de apuesta a ruleta. Se puede apostar un número (del 0 al 36) o color (red or black) y un valor de apuesta (máximo 10000 dólares). Recibe de inputs, el id de la ruleta, la apuesta (ya sea color o número), y  el valor de la apuesta. En los HEADERS se pasa un id de usuario, el servicio que haga la petición ya realizó autenticación y validación de que el cliente tiene el crédito neceario para realizar la apuesta.Para probar escogeré la ruleta creada, id 163786231565777267, color black, valor 2000.
  
Para probar en Postman (colocar verbo PUT, id de ruleta creada anteriormente, número o color válido y valor de apuesta válido, en la sección de headers de Postman colocar como key userId y como value un número que representa el id de un usuario):

      http://localhost:8080/roulettes/{rouletteId}/{bet}/{value}
      
Ejemplo:

      http://localhost:8080/roulettes/163786231565777267/black/2000
      
![](images/img7.JPG)
   
Si ingresamos parametros de apuesta no válidos, la apuesta no será efectuada:

![](images/img8.JPG)
   
### Cuarto Endpoint
   
   - /roulettes/{rouletteId}/closeRoulette PUT
   
Endpoint de cierre de apuestas de una ruleta. Recibe de input el id de una ruleta. Devuelve el resultado de cada una de las apuestas hechas desde la apertura de la ruleta. Hasta el cierre de esta. Para probar escogeré la ruleta creada, id 163786231565777267 (creé más apuestas previamente).

Para probar en Postman (colocar verbo PUT y id de ruleta creada anteriormente):

       http://localhost:8080/roulettes/{rouletteId}/closeRoulette
       
Ejemplo:

       http://localhost:8080/roulettes/163786231565777267/closeRoulette
       
![](images/img9.JPG)

### Quinto Endpoint

- /roulettes  GET

Endpoint de listado de ruletas creadas con sus estados. No necesita ningún input. Para probar, previamente había creado más ruletas y aperturado algunas.

Para probar en Postman (colocar verbo GET):

        http://localhost:8080/roulettes
        
        
![](images/img10.JPG)

## Dependencias del proyecto

En el pom.xml aparte de utilizar las depencias de ***Spring Web*** y ***MongoDB***, utilicé una de **OpenAPI** (que tiene la configuración el paquete com.masivian.config) que permite generar una documentación sencilla de los Web Services y la deja en el siguiente formato:

![](images/img11.JPG)

## Notes

- I didn't use ORMs.
- I researched about CloudWatch and red que era para monitorear logs y el estado de aplicaciones desplegadas en EC2 o otras herramientas de AWS. 





