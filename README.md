# API _ClearZone_

## Endpoints

Estos son todos los **Endpoints** públicos de la API

### /auth/login _(POST)_
Endpoint para logearte, recive 2 parametros y debuelve un codigo **200** si el login fue exitosom, devuelve un **406** si las credenciales son incorrectas
#### Headers
Ninguno
#### Parametros
* email _(text)_
    * Email ocupado para el Inicio de Sesión
* password _(text)_
    * Contraseña de la cuenta
#### Respuesta
* message _(text)_
    * Indica el estado de la respuesta con una cadena de texto simple
* token _(text)_
    * El token de autentificación, este campo puede ser **Nulo** en caso de que no fuera posible la autentificación del usuario

#### Ejemplo

1. Ejemplo con login **Correcto**

    **Petición**
    ```
    email=hola@gmail.com&password=12345
    ```
    **Respuesta**
    ```json
    {
        "message": "Login Successful",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJIb2xhIiwic3ViIjoiaG9sYTFAZ21haWwuY29tIiwicm9sZSI6IlJPTEVfQ0xJRU5URSJ9.8YXBU6pEdNK-f30q2Mep3PphX_EKGTQYWmF6hGRtH6s"
    }
    ```

1. Ejemplos con login **Incorrecto**

    * Email no registrado

        **Petición**
        ```
        email=some@gmail.com&password=NoPassword
        ```
        **Respuesta**
        ```json
        {
            "message": "Usuario no encontrado",
            "token": null
        }
        ```

    * Contraseña incorrecta

        **Petición**
        ```path
        email=hola@gmail.com&password=NoPassword
        ```
        **Respuesta**
        ```json
        {
            "message": "Password incorrecta",
            "token": null
        }
        ```

    * Algun campo vacio

        **Petición**
        ```path
        email=hola@gmail.com&password=
        ```
        **Respuesta**

        ```json
        {
            "message": "Campos vacios",
            "token": null
        }
        ```
>
> Funciona por medio de x-www-form
>


---


### /auth/register/cliente _(POST)_

Endpoint para crear la cuenta **Cliente**, recive 7 parametros y debuelve un codigo **200** si el login fue exitosom, devuelve un **406** si el _Email_ ya ha sido ocupado, devuelve un **403** si algo en la peticion esta mal
#### Headers
Ninguno
#### Parametros
* email _(email)_
    * Email para el Inicio de Sesión e identificador de la cuenta
* password _(text)_
    * Contraseña de la cuenta
* nombre _(text)_
    * Nombre del cliente
* apellidos _(text)_
    * Apellidos del cliente
* direccion _(text)_
    * Dirección a donde seran entregados los pedidos
* pais _(text)_
    * País de donde es el cliente (Solo se aceptan 2 letras, ejemplo _MX_)
* telefono _(text)_
    * El teléfono por el cual nos contactaremos con el usuario (Acepta prefijos de marcación, ejemplo _+52 7296023144_)

#### Respuesta
* message _(text)_
    * Indica el estado de la respuesta con una cadena de texto simple
* token _(text)_
    * El token de autentificación, este campo puede ser **Nulo** en caso de que no fuera posible la autentificación del usuario

#### Ejemplo

1. Ejemplo con registro **Correcto**

    **Petición**
    ```
    email=some@gmail.com&password=12345&nombre=Fernando&apellidos=VergaraGalarza&direccion=Av. Fisherman #54&pais=US&telefono=+52 154554882
    ```
    **Respuesta**
    ```json
    {
        "message": "Login Successful",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJIb2xhIiwic3ViIjoiaG9sYTFAZ21haWwuY29tIiwicm9sZSI6IlJPTEVfQ0xJRU5URSJ9.8YXBU6pEdNK-f30q2Mep3PphX_EKGTQYWmF6hGRtH6s"
    }
    ```

1. Ejemplos con registro **Incorrecto**

    * Email ya registrado

        **Petición**
        ```
        email=hola@gmail.com&password=12345&nombre=Fernando&apellidos=Vergara Galarza&direccion=Av. Fisherman #54&pais=US&telefono=+52 154554882
        ```
        **Respuesta**
        ```json
        {
            "message": "El email ya esta registrado",
            "token": null
        }
        ```

    * Algun campo vacio

        **Petición**
        ```path
        email=hola@gmail.com&password=&nombre=Fernando&direccion=Av. Fisherman #54&pais=US&telefono=+52 154554882
        ```
        **Respuesta**

        ```json
        {
            "message": "Campos vacios",
            "token": null
        }
        ```
>
> Funciona por medio de x-www-form
>

