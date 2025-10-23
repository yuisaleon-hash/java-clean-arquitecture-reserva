# 🐾 Sistema de Clínica Veterinaria

[![Build Status](https://github.com/company/veterinary-clinic/workflows/CI/badge.svg)](https://github.com/company/veterinary-clinic/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=veterinary-clinic&metric=coverage)](https://sonarcloud.io/dashboard?id=veterinary-clinic)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=veterinary-clinic&metric=alert_status)](https://sonarcloud.io/dashboard?id=veterinary-clinic)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> Sistema integral de gestión para clínicas veterinarias implementado con **Clean Architecture**, **Spring Boot** y **PostgreSQL**.

## 📋 Tabla de Contenidos

- [🎯 Características](#-características)
- [🏗️ Arquitectura](#️-arquitectura)
- [🚀 Inicio Rápido](#-inicio-rápido)
- [📖 Documentación](#-documentación)
- [🧪 Testing](#-testing)
- [🚢 Deployment](#-deployment)
- [🤝 Contribución](#-contribución)
- [📞 Soporte](#-soporte)

## 🎯 Características

### ✨ Funcionalidades Principales

- **📅 Gestión de Citas**: Programación, confirmación y seguimiento de citas veterinarias
- **🐕 Registro de Mascotas**: Información completa de mascotas y sus dueños
- **👨‍⚕️ Gestión de Veterinarios**: Control de especialistas y horarios
- **📧 Notificaciones**: Sistema automatizado de emails y recordatorios
- **📊 Reportes**: Estadísticas y reportes de la clínica
- **🔐 Seguridad**: Autenticación y autorización robusta

### 💡 Características Técnicas

- **🏛️ Clean Architecture**: Separación clara de capas y responsabilidades
- **📱 API REST**: Endpoints documentados con OpenAPI/Swagger
- **🗄️ Base de Datos**: PostgreSQL con migraciones automáticas
- **✅ Testing**: Cobertura > 80% con tests unitarios, integración y E2E
- **📈 Observabilidad**: Métricas, logs estructurados y monitoreo
- **🐳 Containerización**: Docker y Docker Compose
- **🚀 CI/CD**: Pipeline automatizado con GitHub Actions

## 🏗️ Arquitectura

### Clean Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    🌐 Infrastructure Layer                  │
│  Controllers • JPA • Email • Config • Security             │
├─────────────────────────────────────────────────────────────┤
│                   💼 Application Layer                      │
│  Use Cases • DTOs • Mappers • Ports                        │
├─────────────────────────────────────────────────────────────┤
│                     🎯 Domain Layer                         │
│  Entities • Value Objects • Domain Services • Repositories │
└─────────────────────────────────────────────────────────────┘
```

### Stack Tecnológico

| Capa | Tecnologías |
|------|-------------|
| **Backend** | Java 17, Spring Boot 3.2, Spring Security |
| **Base de Datos** | PostgreSQL 15, Flyway |
| **Testing** | JUnit 5, Mockito, TestContainers |
| **Documentación** | OpenAPI 3, Swagger UI |
| **Build** | Maven, Docker |
| **Calidad** | SonarQube, JaCoCo, Checkstyle |

## 🚀 Inicio Rápido

### Prerequisites

- **Java 17+** ☕
- **Maven 3.8+** 📦
- **Docker & Docker Compose** 🐳
- **PostgreSQL 15** (opcional, se puede usar con Docker) 🗄️

### 1. Clonar el Repositorio

```bash
git clone https://github.com/company/veterinary-clinic.git
cd veterinary-clinic
```

### 2. Configurar Base de Datos

#### Opción A: Docker (Recomendado)

```bash
# Iniciar PostgreSQL en contenedor
docker-compose up -d postgres

# Verificar que está funcionando
docker-compose logs postgres
```

#### Opción B: PostgreSQL Local

```sql
-- Crear base de datos
CREATE DATABASE veterinary_clinic;
CREATE USER veterinary_user WITH ENCRYPTED PASSWORD 'veterinary_pass';
GRANT ALL PRIVILEGES ON DATABASE veterinary_clinic TO veterinary_user;
```

### 3. Ejecutar Migraciones

```bash
# Ejecutar migraciones de base de datos
mvn flyway:migrate

# Verificar esquema
mvn flyway:info
```

### 4. Compilar y Ejecutar

```bash
# Compilar proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Iniciar aplicación
mvn spring-boot:run
```

### 5. Verificar Instalación

```bash
# Health check
curl http://localhost:8080/actuator/health

# Documentación API
open http://localhost:8080/swagger-ui.html
```

## 📖 Documentación

### 📚 Documentación Técnica

- **[🏛️ Arquitectura](docs/architecture/README.md)** - Diseño y decisiones arquitectónicas
- **[📊 Base de Datos](docs/database/schema.md)** - Esquema y relaciones
- **[🔌 API](docs/api/README.md)** - Documentación de endpoints
- **[🚢 Deployment](docs/deployment/README.md)** - Guías de despliegue

### 🌐 API Endpoints

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/api/v1/appointments` | POST | Programar nueva cita |
| `/api/v1/appointments` | GET | Buscar citas por fecha |
| `/api/v1/pets` | POST | Registrar nueva mascota |
| `/api/v1/pets/owner/{id}` | GET | Mascotas por dueño |
| `/actuator/health` | GET | Estado de la aplicación |

### 📋 Ejemplos de Uso

#### Programar una Cita

```bash
curl -X POST http://localhost:8080/api/v1/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "petId": "550e8400-e29b-41d4-a716-446655440000",
    "veterinarianId": "550e8400-e29b-41d4-a716-446655440001",
    "appointmentDate": "2025-01-15T10:00:00",
    "durationMinutes": 30,
    "reason": "Consulta general"
  }'
```

#### Registrar una Mascota

```bash
curl -X POST http://localhost:8080/api/v1/pets \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Buddy",
    "speciesId": "550e8400-e29b-41d4-a716-446655440002",
    "breed": "Golden Retriever",
    "birthDate": "2020-05-15",
    "weight": 25.5,
    "color": "Dorado",
    "gender": "MALE",
    "ownerId": "550e8400-e29b-41d4-a716-446655440003"
  }'
```

## 🧪 Testing

### Estrategia de Testing

La aplicación implementa una pirámide de testing completa:

```
        E2E Tests (Selenium + TestContainers)
           Integration Tests (Spring Boot Test)  
              Unit Tests (JUnit + Mockito)
```

### Ejecutar Tests

```bash
# Todos los tests
mvn clean test

# Solo tests unitarios
mvn test -Dtest="*Test"

# Solo tests de integración
mvn test -Dtest="*IntegrationTest"

# Tests con cobertura
mvn clean test jacoco:report

# Ver reporte de cobertura
open target/site/jacoco/index.html
```

### Métricas de Calidad

```bash
# Análisis estático
mvn spotbugs:check checkstyle:check

# Reporte completo de calidad
mvn clean verify sonar:sonar
```

## 🚢 Deployment

### Docker

```bash
# Build imagen
docker build -t veterinary-clinic:latest .

# Ejecutar con Docker Compose
docker-compose up -d

# Ver logs
docker-compose logs -f app

# Parar servicios
docker-compose down
```

### Variables de Entorno

| Variable | Descripción | Default |
|----------|-------------|---------|
| `DB_USERNAME` | Usuario de base de datos | `veterinary_user` |
| `DB_PASSWORD` | Contraseña de base de datos | `veterinary_pass` |
| `MAIL_HOST` | Servidor SMTP | `smtp.gmail.com` |
| `MAIL_USERNAME` | Usuario email | - |
| `MAIL_PASSWORD` | Contraseña email | - |
| `SPRING_PROFILES_ACTIVE` | Perfil activo | `dev` |

### Perfiles de Ejecución

```bash
# Desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Testing
mvn spring-boot:run -Dspring-boot.run.profiles=test

# Producción
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 🤝 Contribución

¡Las contribuciones son bienvenidas! Por favor lee nuestra [guía de contribución](CONTRIBUTING.md).

### Proceso de Desarrollo

1. **Fork** el repositorio
2. **Crear** branch para feature: `git checkout -b feature/amazing-feature`
3. **Commit** cambios: `git commit -m 'Add amazing feature'`
4. **Push** al branch: `git push origin feature/amazing-feature`
5. **Abrir** Pull Request

### Estándares de Código

- **Cobertura de tests**: Mínimo 80%
- **Checkstyle**: Seguir convenciones de Google Java Style
- **SonarQube**: Quality Gate debe pasar
- **Commits**: Usar [Conventional Commits](https://conventionalcommits.org/)

### Arquitectura

- **Respeta Clean Architecture**: No crear dependencias circulares
- **Domain-First**: Lógica de negocio en el dominio
- **Tests primero**: TDD para funcionalidades críticas
- **SOLID principles**: Aplicar principios SOLID

## 📊 Roadmap

### 🚀 Próximas Versiones

#### v1.1.0 - Q1 2025
- [ ] Autenticación JWT
- [ ] Roles y permisos
- [ ] API móvil
- [ ] Push notifications

#### v1.2.0 - Q2 2025
- [ ] Reportes avanzados
- [ ] Dashboard analytics
- [ ] Integración pagos
- [ ] Multi-tenancy

#### v2.0.0 - Q3 2025
- [ ] Microservicios
- [ ] Event Sourcing
- [ ] Machine Learning para diagnósticos
- [ ] App móvil nativa

## 📞 Soporte

### 🐛 Reportar Bugs

Usar [GitHub Issues](https://github.com/company/veterinary-clinic/issues) con la template de bug report.

### 💡 Solicitar Features

Usar [GitHub Issues](https://github.com/company/veterinary-clinic/issues) con la template de feature request.

### 📧 Contacto

- **Email**: dev@veterinaryclinic.com
- **Slack**: #veterinary-clinic-dev
- **Documentación**: [Wiki del proyecto](https://github.com/company/veterinary-clinic/wiki)

### 🆘 Soporte Urgente

Para problemas críticos en producción:
- **PagerDuty**: [veterinary-clinic-alerts](https://company.pagerduty.com)
- **Slack**: #veterinary-clinic-alerts
- **Email**: oncall@veterinaryclinic.com

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver [LICENSE](LICENSE) para más detalles.

---

## 🙏 Agradecimientos

- **Robert Martin** por Clean Architecture
- **Alistair Cockburn** por Hexagonal Architecture
- **Spring Team** por el excelente framework
- **Comunidad Open Source** por las herramientas utilizadas

---



