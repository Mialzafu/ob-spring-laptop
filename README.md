## Ejericio 1
* Se empaqueta la aplicación con maven package desde Intellij IDEA
* Se verifica desde la terminal en Intellij IDEA que se ejecuta correctamente con el comando:
```
\target> java -jar .\ob-spring-laptop-1.0.jar
```
* Se crean perfiles para dev y test con una propiedad nueva `app.message` que se carga en el controlador.

## Ejercicio 2
* Se crea el archivo `system.properties` con la línea:
```
java.runtime.version=18
```
* Se sube el proyecto `ob-spring-laptop` a GitHub con la opción: `VCS, Share projetc on GitHub`. La ruta del repositorio es:
```
https://github.com/Mialzafu/ob-spring-laptop
```
* Se crea la app `ob-spring-rest-laptop` en HEROKU seleccionando como método GitHub para el repositorio `ob-spring-laptop`, habilitando la opción `Enable Automatic Deploys` y se realiza además es despliegue automático con `Deploy Branch`. La ruta final de la app HEROKU es:
```
https://ob-spring-rest-laptop.herokuapp.com
```
Para la validación del funcionamiento se adjunta en el código la colección de Postman exportada `OB Spring REST Laptops.postman_collection.json` o bien desde el mismo Postman crear una nueva colección y una nueva solicitud y por ejemplo hacerla de tipo GET con la ruta:
```
https://ob-spring-rest-laptop.herokuapp.com/api/laptops -> Obtiene todas las laptops
https://ob-spring-rest-laptop.herokuapp.com/api/laptops/1 -> Obtiene la laptop con identificador 1
```

## Ejercicio 3
* Se agregan las dependencias de Spring Security sobre el proyecto API REST de Laptops
* Se configuran 2 usuarios (user y admin) en memoria utilizando una clase WebSecurityConfig