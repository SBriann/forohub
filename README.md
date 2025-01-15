# ForoHub

API para un Foro que maneja el endpoint de tópicos, permitiendo su creación, eliminación, actualización y consulta; además de tener la capa de seguridad que impide su uso sin haberse autenticado.

## Tecnologías usadas

- `Java 17`
- `Spring Boot 3`
- `MySQL`

## Librerías usadas

- `Spring Data JPA`
- `Spring Web`
- `Spring Security`
- `MySQL Driver`
- `Flyway Migration`
- `Validation`
- `Lombok`
- `JWT`

## Rutas

- **/login** `POST`: (*Autenticación*) Para poder usar el API, es necesario iniciar sesión, para ello se requiere pasar el siguiente body:
  ~~~js
  {
      "login": "nombre_usuario",
      "clave": "clave"
  }
  ~~~
- **/topicos** `POST`: (*Registrar tópico*) Se tiene la opción de crear un nuevo tópico, para ello se necesita el siguiente body:
  ~~~js
  {
      "titulo": "titulo_topico",
      "mensaje": "mensaje_topico",
      "curso": "curso_topico"
  }
  ~~~
  Es necesario que se envién todos los campos y que el título y el mensaje no estén registrados aún, de lo contrario se impedirá su registro.
- **/topicos** `GET`: (*Obtener tópicos*) Su finalidad es visualizar todos los tópicos registrados, haciendo uso de la paginación.
- **/topicos/*{id}*** `GET`: (*Obtener tópico*) Para observar un tópico a detalle, para ello es necesario pasar el `id` del mismo como un **Path Param**.
- **/topicos** `PUT`: (*Actualizar tópico*) Con la finalidad de modificar un tópico existente, es necesario usar el siguiente body:
  ~~~js
  {
      "id": 1,
      "titulo": "titulo_topico",
      "mensaje": "mensaje_topico",
      "curso": "curso_topico"
  }
  ~~~
  El campo `id` es obligatorio, mientras que los demás son opcionales.
- **/topicos/*{id}*** `DELETE`: (*Eliminar tópico*) Existe la opción de eliminar un tópico, para ello es necesario pasar el `id` del mismo como un **Path Param**.