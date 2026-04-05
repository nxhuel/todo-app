# API To-do List
## I - DESCRIPCIÓN
Mediante este sistema web denominado To-do APP los usuarios fácilmente pueden crear, listar, editar, eliminar, marcar como completada y filtrar tareas por estado. El desarrollo del mismo tiene como objetivo mejorar la gestión y planificación de tareas mediante una API. Cualquier usuario frontend debe poder entender cómo realizar la conexión a su web [Ver Contrato de Endpoints si desea ir al grano]. El público objetivo es desarrolladores frontend.
## II- ANÁLISIS DE REQUERIMIENTOS
### A - REQUERIMIENTOS FUNCIONALES
El usuario debe poder crear, listar, editar, eliminar, marcar como completada y filtrar tareas por estado
### B - REQUERIMIENTOS NO FUNCIONALES
README: descripción del proyecto, cómo correrlo localmente, variables de entorno. 
Swagger/OpenAPI: documentar cada endpoint con descripción, parámetros y respuestas. 
Decisiones técnicas: por qué usaste DTOs, cómo manejás los errores. 
Presentar Diagrama de entidades (db diagram.io) entre otros. 
### C - REQUERIMIENTOS TÉCNICOS
Java 21
Spring Boot 3
MySQL
Spring Data JPA
Maven
Docker
## III - DISEÑO DEL SISTEMA
### A - MODELO DE NEGOCIO
Los negocios se generan a partir de suscripciones, el desarrollador que consume la API FREE puede elegir si poner suscripciones de inicio, poner máximo de tareas u optar por un modelo free.
### B - FLUJO TÍPICO DEL SISTEMA
El usuario accede a la app y ve su lista de tareas pendientes.
Crea una tarea nueva ingresando un título, descripción?, prioridad, categoría, y fecha_límite?.
La tarea aparece en la lista principal.
A medida que avanza, marca las tareas como completadas.
Las tareas completadas se mueven a una sección separada o se tachan visualmente.
Si una tarea ya no es relevante, la elimina directamente de la lista.
### C - CASOS DE USO
| FLUJO PRINCIPAL - CREAR TAREA | CAMINOS ALTERNATIVOS |
|--------------------------------|----------------------|
| 1. u: Hace clic en “Nueva tarea” | 3a - Título vacío → El sistema muestra error de validación. El flujo vuelve al paso 3. |
| 2. s: Muestra el formulario | 3b - Fecha inválida (pasada) → El sistema advierte pero permite continuar. |
| 3. u: Ingresa título, descripción?, prioridad, categoría, y fecha_límite? | 5a - Error de red → El sistema guarda la tarea offline y sincroniza cuando recupera conexión. |
| 4. u: Presiona "Guardar" | 5b - Sesión expirada → Redirige al login. La tarea redactada se preserva en localStorage. |
| 5. s: Valida y persiste la tarea | |
| 6. s: Muestra la tarea en la lista | |
### D - DIAGRAMA ENTIDAD-RELACIÓN	
Tabla: tbl_task

| Columna     | Tipo de dato   | Notas                          |
|-------------|----------------|--------------------------------|
| id          | INTEGER        | Primary Key                    |
| title       | VARCHAR(255)   |                                |
| description | VARCHAR(255)   | Texto descriptivo              |
| completed   | BOOLEAN        | Estado de la tarea             |
| priority    | VARCHAR(255)   | Nivel de prioridad             |
| due_date    | DATE           | Puede ser NULL                 |
| created_at  | DATE           | Fecha de creación              |
| updated_at  | DATE           | Última actualización           |
| category    | VARCHAR(255)   | Categoría asignada             |

F - CONTRATO DE ENDPOINTS
| Método      | Endpoint       | descripción                    |
|-------------|----------------|--------------------------------|
| POST         | /api/tasks      | Crear tarea         |
| GET        | /api/tasks      | Obtener todas las tareas         |
| GET         | /api/tasks/1      | Obtener una tarea por ID        |
| PATCH         | /api/tasks/1      | Editar una tarea parcialmente por ID         |
| DELETE        | /api/tasks/1      | Eliminar una tarea por ID         |

## IV - IMPLEMENTACIÓN
### A - CONFIGURACIÓN Y EJECUCIÓN DEL SISTEMA
Se deberá implementar Docker docker-compose para almacenar contenedor del código y base de datos. 



