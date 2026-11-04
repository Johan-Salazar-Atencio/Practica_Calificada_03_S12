-- Bases de datos del sistema de postulaciones (una por microservicio).
-- Los servicios crean/ajustan las tablas solos (ddl-auto=update en dev);
-- las tablas se incluyen aquí como referencia y para el perfil prod (validate).

CREATE DATABASE IF NOT EXISTS bd_empresa_daw CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS bd_vacante_daw CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS bd_postulacion_daw CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- empresa-service
USE bd_empresa_daw;
CREATE TABLE IF NOT EXISTS empresa (
    id           BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ruc          VARCHAR(11)  NOT NULL UNIQUE,
    razon_social VARCHAR(150) NOT NULL,
    correo       VARCHAR(120) NOT NULL,
    telefono     VARCHAR(15),
    estado       VARCHAR(20)  NOT NULL
);

-- vacante-service (id_empresa es solo un ID: no hay llave foránea entre bases)
USE bd_vacante_daw;
CREATE TABLE IF NOT EXISTS vacante (
    id           BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(120) NOT NULL,
    descripcion  VARCHAR(500) NOT NULL,
    area         VARCHAR(80)  NOT NULL,
    fecha_cierre DATE         NOT NULL,
    id_empresa   BIGINT       NOT NULL,
    estado       VARCHAR(20)  NOT NULL
);

-- postulacion-service (id_vacante es solo un ID)
USE bd_postulacion_daw;
CREATE TABLE IF NOT EXISTS postulacion (
    id                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_vacante         BIGINT       NOT NULL,
    nombre_postulante  VARCHAR(120) NOT NULL,
    correo             VARCHAR(120) NOT NULL,
    fecha              DATE         NOT NULL,
    estado             VARCHAR(20)  NOT NULL
);
