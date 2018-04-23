CARRITO DE COMPRAS:

Armar un repositorio de productos con los siguientes campos:
- id 
- description (nombre del producto)
- unit_price (precio unitario)
- stock (stock del producto)

Los datos se pueden guardar en una base de datos o en memoria y deberán estar precargados en el sistema (Pueden estar hardcodeados).
Armar luego un repositorio de "carts" (carritos de compra) con los siguientes campos:

- id
- fullName (nombre y apellido del cliente)
- email (email del cliente)
- creationDate (fecha de creación del carrito)
- products (lista de objetos con formato {id, unit_price, quantity} de productos que se desean comprar)
- total (precio total a pagar por los productos que haya en el carrito)
- status (estado actual del carrito)

Para este recurso (carts) desarrollar las siguientes acciones:

[POST] /carts:
Este servicio deberá crear un nuevo carrito y guardarlo en el repositorio. Deberá aceptar como body los campos requeridos para la creacion del mismo:
- fullName (nombre y apellido del cliente)
- email (email del cliente)

"creationDate" se asignará con la fecha actual. "total" arranca en 0. "status" arranca en estado "NEW". "products" será una colección vacía. Como respuesta del servicio, se debe retornar el carrito recien creado con su respectivo ID autogenerado.

[POST] /carts/{id}/products
Deberá permitir agregar productos al carrito cuyo ID sea {id}. El body del request es el id del producto y la cantidad del mismo que se desea agregar al carrito. Por ejemplo: {"id": 20, "quantity": 2}. 

IMPORTANTE: Si bien se ingresa un "id" y un "quantity" al carrito, es buena idea también guardar el precio unitario del producto al momento de agregarlo, para evitar problemas con posibles variaciones de precio.

[DELETE] /carts/{cartId}/products/{productId}
Deberá remover el producto {productId} del carrito con id {cartId}. El DELETE elimina el elemento del array de products (es decir: no resta a quantity, simplemente lo elimina por completo)

[GET] /carts/{id}/products
Deberá permitir ver todos los productos que haya en el carrito en ese momento (se retornara el producto completo con los campos: id, description, quantity y unit_price.)

[GET] /carts/{id}/ 
Permite ver el carrito armado, con su respectivo "total", y los ids de los productos en "products". 

[POST] /carts/{id}/checkout
Para simplificar el ejercicio no enviarmos datos de tarjeta ni nada por el estilo. Este servicio simplemente debera pasar el carrito a status: "READY" 


Finalmente desarrollar una tarea que corra cada "x" tiempo, y haga la simulación de que se procesan los pagos de los carritos. Esta simulación consistirá en pasar el carrito a status "PROCESSED" si es que hay suficiente stock. Caso contrario pasará a estado "FAILED". Guardar algun LOG para poder trackear estos casos. Tener en cuenta para este servicio que pueden llegar a procesarse miles de carritos a la vez, con lo cual sería recomendable usar algun ThreadPool, para poder procesar varios en paralelo.

Solo hace falta hacer un test unitario para esta lógica de definir si el carrito pasa a PROCESSED o FAILED.

Se apreciará el buen uso de multitheading, separación en capas, manejo de excepciones, uso de la stream api y claridad en el código. El framework usado queda a elección del candidato.