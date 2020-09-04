# Wenance Challenge

_API para la obtencion de cotizacion de BTC por Fecha_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Pre-requisitos 📋

_Debes tener instalado JAVA jdk 1.8 o posterior (https://www.oracle.com/ar/java/technologies/javase/javase-jdk8-downloads.html)._
_y Maven para poder compilar el proyecto (https://maven.apache.org/download.cgi)._


### Instalación 🔧

_Una vez clonado el repositorio dirigirse a la carpeta de descarga, para ejecutarlo debe correr la siguiente linea de commandos_

```
mvn spring-boot:run
```

## Pruebas ⚙️

_Para probar el servicio puede hacer un GET a alguno de los siguientes endpoints:_

```
_http://localhost:8080/wenance/price?at=YYYY-MM-DDThh:mm:ss_
```
```
_http://localhost:8080/wenance/average?from=YYYY-MM-DDThh:mm:ss&to=YYYY-MM-DDThh:mm:ss_
```
_donde las fechas y horas deben ser posteriores al inicio del servicio y anterior al momento actual_



### Analice las pruebas end-to-end 🔩

_Explica que verifican estas pruebas y por qué_

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Autores ✒️

* **Héctor Edgardo del Mastro** - *Trabajo Inicial* - [hdelmastro](https://github.com/hdelmastro)

