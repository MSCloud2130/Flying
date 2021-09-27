# Store

El Store funciona como punto de entrada a los microservicios de la aplicación utilizando una estrategia de Service Discovery y un Load Balancer del lado del cliente

Para ejecutar el Store se debe dirigir a la carpeta store y ejecutar los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run
```

Este estará escuchando en la dirección de localhost en el puerto 8080.

Continúe con [Seeker](seeker.md)
