# **Paymeter Backend Software Engineer Assessment**

## **Descripción**

Este proyecto es una aplicación de backend escrita en Java utilizando Spring Boot. El objetivo es calcular el precio de estacionamiento para diferentes escenarios basados en las tarifas y descuentos proporcionados por los clientes.


## **Estructura del Proyecto**
* **io.paymeter.assessment**: Contiene la clase principal de la aplicación y la configuración general.
* **io.paymeter.assessment.parking**: Contiene el controlador REST para manejar las solicitudes de cálculo de precios.
* **io.paymeter.assessment.pricing**: Contiene las clases relacionadas con el cálculo de precios, como las tarifas, descuentos y la lógica de cálculo de precios.
## **Requisitos**

Se requiere la implementación de un nuevo endpoint REST para calcular el precio del estacionamiento en función de los datos proporcionados, como el ID del estacionamiento, la hora de inicio y la hora de finalización.

## **Cómo Ejecutar**

* Java 17
* Gradle
* Docker (opcional)

### Pasos
1. Clona este repositorio desde Github.
2. Abre una terminal y navega hasta el directorio raíz del proyecto.
3. Ejecuta el siguiente comando para ejecutar la aplicación:

    `./gradlew bootRun`

    O, si prefieres utilizar Docker:

   `docker build -t app . && docker run -it -p 8080:8080 app`

4. Una vez que la aplicación esté en funcionamiento, puedes realizar solicitudes a través de la API REST.

## **Cómo Probar**

Para ejecutar las pruebas unitarias, utiliza el siguiente comando en la terminal:

`./gradlew test`

O, si prefieres utilizar Docker:

`docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:8-jdk17 gradle test`

### **Endpoints Disponibles**

#### **Calcular Precio de Estacionamiento**
* **Endpoint**: POST /tickets/calculate
* Solicitud: JSON
* parkingID: (cadena) ID del estacionamiento (obligatorio)
* from: (cadena) Marca de tiempo ISO 8601 de inicio (obligatorio)
* to: (cadena) Marca de tiempo ISO 8601 de finalización (opcional, por defecto es la hora actual)
* Respuesta: JSON
* parkingID: (cadena) ID del estacionamiento (obligatorio)
* from: (cadena) Marca de tiempo ISO 8601 de inicio (obligatorio)
* to: (cadena) Marca de tiempo ISO 8601 de finalización (obligatorio)
* duration: (entero) Duración en minutos
* price: (cadena) Precio en formato de moneda (p.ej. "235EUR")

#### **Estado de la Aplicación**

Para verificar el estado de salud de la aplicación, puedes acceder a la URL raíz:
`GET /`

Esta solicitud debería devolver _"Everything Ok!"_ si la aplicación está funcionando correctamente.


***

Angi Rubio

[linkedin.com/in/angirubio]()