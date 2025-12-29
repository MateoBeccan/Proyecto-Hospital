Backend + Base de Datos (MySQL) – Proyecto en Desarrollo

Este repositorio contiene el backend y el diseño completo de la base de datos para un Sistema de Gestión Hospitalaria, orientado a representar el funcionamiento real de un entorno clínico.
El objetivo es ofrecer una arquitectura robusta, escalable y fácil de mantener, que permita gestionar pacientes, médicos, turnos, historia clínica y más.

📌 Características principales
✔ Backend desarrollado en Java + Spring Boot

API REST completa y modular.

Servicios, controladores y DTOs organizados por entidad.

Validación de datos, manejo de excepciones y arquitectura limpia.

✔ Base de datos MySQL (EER + Normalización 3FN)

Diagrama completo de entidades y relaciones.

Integridad referencial mediante claves primarias y foráneas.

Módulos independientes y escalables.

✔ Endpoints probados con Postman

CRUD completo para Pacientes, Médicos, Especialidades, Salas, Afiliaciones, Citas, Medicamentos, etc.

Ejemplos de solicitudes y respuestas.

🗂️ Estructura del sistema
Módulos principales

Pacientes: datos personales, datos de contacto y afiliaciones.

Médicos: especialidades, departamentos y teléfonos.

Turnos/Citas: unión entre paciente–médico–sala–estado.

Salas y Departamentos: asignación dinámica a médicos.

Historia Clínica: diagnósticos, procedimientos y estudios.

Medicamentos y Prescripciones.
