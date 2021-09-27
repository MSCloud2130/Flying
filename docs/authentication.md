# Authentication

Athentication va a ser el servicio encargado de la autenticación en la aplicación este cuenta con 4 endpoint para el manejo de sesiones. Para poner en funcionamiento el microservico se debe dirigir a la carpeta auth y ejecutar los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run
```

## Funcionalidades Auth

Estas funcionalidades se dividen según los tipos de usuarios de la aplicación, en este caso cliente y vendedor, para el uso de estos servicios se manejan usuarios de la siguiente forma enviados en un JSON siguiendo la siguiente estructura.

```JSON
{
  "email":"a@a.com",
  "password":"my_password"
}
```

###  SignIn

Este va a ser el encargado de autenticar a la persona frente a la aplicación y puede ser accedido en la siguiente URI.

```bash
#Vendedor
localhost:8080/auth/seller/signin
#Cliente
localhost:8080/auth/client/signin
```

### SignUp

El SignUp, va a ser el encargado de registrar un nuevo usuario en la aplicación y guaradarlo en la base de datos

```bash
#Vendedor
localhost:8080/auth/seller/signup
#Cliente
localhost:8080/auth/client/signup
```

Continúe con [Seeker](seeker.md)
