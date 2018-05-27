# CARRITO DE COMPRAS:

Ejercicio técnico de carrito de compras para ser evaluado por el equipo de ingenieros de Garbarino S.A.
Ver el [ENUNCIADO.md](ENUNCIADO.md) para mayor detalle.

## Getting Started

Instrucciones para obtener una copia del proyecto levantado y corriendo en tu entorno local para propósitos de desarrollo y testing. 

### Prerequisites

Tener instalado java 8 y gradle.

### Installing

Al ejecutar gradle se compilará el jar y ejecutarán todos los tests unitarios.
```
./gradlew build
```

Luego se podrá ejecutar el jar generado de la siguiente forma:
```
java -jar build/libs/shopping-cart-0.0.1-SNAPSHOT.jar 
```

## Running the tests

Los tests se pueden ejecutar directamente por separado con gradle ejecutando la siguiente instrucción:

```
./gradlew check
```

Para ejecutar tests interactivos con la API REST se puede utilizar la interface de Swagger levantando la aplicación (jar) y luego ingresando desde un browser a la dirección http://localhost:8080/swagger-ui.html . Allí se visualizarán toda la interfaz expuesta por la API para interactuar con la misma y reallizar pruebas más manuales. Cabe destacar que en la consola se loguean los registros de Productos y Carts que se crean en con el seeder en caso de necesitar los IDs para ingresar en los argumentos para invocar dichos métodos de la página de swagger.

## Built with

* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - The web framework used
* [Gradle](https://docs.gradle.org/current/userguide/userguide.html) - Dependency Management
* [Swagger](https://swagger.io/docs/) - Generate API documentation

## Authors

* **Alejandro Sánchez** - [sanchezalejandro86](https://github.com/sanchezalejandro86)