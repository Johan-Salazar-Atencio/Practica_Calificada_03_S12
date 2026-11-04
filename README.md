# Práctica Calificada 03 - Sistema de gestión de postulaciones

Evaluación 03 de Desarrollo de Aplicaciones Web (Tecsup). Sistema de microservicios con Spring Boot para publicar vacantes y recibir postulaciones.

**Autor:** Johan Salazar Atencio

## Microservicios

| Servicio | Responsabilidad | Puerto (dev) | Base de datos |
|---|---|---|---|
| empresa-service | Empresas que publican vacantes | 8081 | bd_empresa_daw |
| vacante-service | Vacantes de cada empresa | 8082 | bd_vacante_daw |
| postulacion-service | Postulaciones a una vacante | 8083 | bd_postulacion_daw |

Cada servicio tiene su propia base de datos; se relacionan solo por IDs (idEmpresa, idVacante).

## Estructura

```
empresa-service/ · vacante-service/ · postulacion-service/
  └── com.tecsup.<servicio>: model, dto, repository, services, controller, exception
sql/       script de las bases de datos
postman/   colección de pruebas
docs/      diagrama de microservicios
```

## Tecnologías

Java 21, Spring Boot 4.1.2, Maven, Spring Web, Spring Data JPA, MySQL, Validation y configuración con YAML (perfiles dev y prod).

> Los pasos de ejecución, endpoints y perfiles se completan a medida que avanza el trabajo.
