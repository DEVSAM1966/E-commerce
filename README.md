<!-- =============================== -->
<!--        BANNER PERSONALIZADO     -->
<!-- =============================== -->

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:0A66C2,100:1F6FEB&height=220&section=header&text=Sebastián%20Asunción%20Montero&fontSize=45&fontColor=ffffff&animation=fadeIn" />
</p>

<p align="center">
  </h2><b>Proyecto en arquitectura Hexagonal - E-commerce (backend)</b>
</p>

---

<!-- =============================== -->
<!--            BADGES               -->
<!-- =============================== -->

<p align="center">
  <img src="https://img.shields.io/badge/Java-21+-red?logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-17-blue?logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-DevOps-2496ED?logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/OpenAPI-3.1-6BA539?logo=openapiinitiative&logoColor=white" />
</p>

---

## Proyecto E-commerce (Introducción)

La finalidad de este proyecto es la implementación de una arquitectura hexagonal en el backend bajo:

1. **Java 21**
2. **Spring Boot 4.0.6**
3. **Contenedor Docker para la BD PostgreSQL 17.2**
4. **Seguridad bajo JWT**
5. **Documentación OpenAPI 3.1**

Se implementa una plataforma E-commerce sencilla para dar contexto al uso de una arquitectura hexagonal desde el lado backend.


Para el frontend emplearé:
1. **Angular**
2. **JWT para validar los usuarios**

---

## Parte backend

### Integración de versión Java / Spring Boot / Librerias

La dificultad inicial es integrar en el **pom.xml** las sigientes librerias y versión de Java, Spring Boot: 

- La versión **Java 21** con **Spring Boot 4.X** (Esta versión de Spring obliga a usar la versión 21 de Java).

- **MapStruct 1.6.3** (primera versión estable compatible con Java 21).

- **Lombok 1.18.34** o superior (versión que corrigió los problemas con annotation processors).

- **lombok-mapstruct-binding 0.2.0** (sigue siendo necesario).

- **Maven Compiler Plugin 3.12.1** (obligatorio para Java 21).


### BD mediante contenedor Docker

En la raiz del proyecto tendremos el archivo docker-compose.yml, donde se configura el motor de BD (PostgreSQL 17.2), nombre de la BD, usuario, contraseña, puertos, persistencia.

Recordemos que en nuestra máquina local se debera tener instalado docker; para crear el contenedor situarse en la raiz del proyecto (en local), ejecutar en CMD o bash el siguiente comando:

```bash
docker compose up
```

Este comando solo lo ejecutaremos la 1ª vez para crear el contenedor, después no es necesario.

### Ficheros application.properties

Tendremos para el entorno de produción y desarrollo los siguientes ficheros:

- Desarrollo: **application-dev.properties**
- Producción: **application-pdn.properties**

En el fichero **application.properties** tendremos la siguiente entrada:

```java
spring.profiles.active = dev
```

Se indica que se use el fichero **application-dev.properties** (entorno de desarrollo).

