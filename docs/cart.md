# Cart

Este microsevicio va a ser el correspondiente a manejar el carrito de compras y el proceso de pago.

Para poner en funcionamiento el microservico se debe dirigir a la carpeta cart y ejecutar los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run
```

## Funcionalidades Cart

###  Agregar productos

Sirve para agregar un producto en específico al carrito de compras con un id específico, esté puede ser accedido por el siguiente endpoint.

```bash
localhost:8080/cart/{id}
```
![add](Images/addproduct1.png)
![add2](Images/addproduct2.png)

### Eliminar productos

Este trae productos que coincidan con una lista de posibles valores dados. Para acceder a este se debe dirigir al siguiente endpoint:

```bash
localhost:8080/products/cart?removeProductId={id}
```
![remove](Images/remove.png)

### Obtener el carrito de un Usuario

Este sirve para obtener el carrito de compras del usuario de la sesión actual. Para acceder a este se debe dirigir al siguiente endpoint con estos headers:
```bash
localhost:8080/cart
```
![getCart](Images/cart1.png)
![getCart2](Images/cart2.png)

### Payment

Este se utiliza para proceder al pago de los productos en el carrito de compras del usuario. Para acceder a este se debe dirigir al siguiente endpoint con estos headers:

```bash
localhost:8080/cart/payment
```
![payment](Images/pay.png)
