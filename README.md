
# Movie API

API REST desarrollada con Spring Boot para la gestión de películas.  
Permite crear, consultar y actualizar  con validaciones integradas y control de errores personalizados.


## Como ejecutar la aplicación

1. Clonar el repositorio:
   ```bash
   https://github.com/SofiaCantalupi/springboot-practice-movies.git

2. Abrir el proyecto en IntelliJ IDEA

3. Asegurarse de tener Java 17 y Maven configurado

4. Ejecutar la clase principal:

   ```
   MovieApiApplication.java
   ```

5. Acceder a la API desde:

   ```
   http://localhost:8080/api/movies
   ```

---

## Endpoints disponibles

### Crear película

`POST /api/movies`

**Body (JSON):**

```json
{
  "title": "The Brutalist",
  "director": "Brady Corbet",
  "releaseYear": 2024,
  "genre": "Drama"
}
```

**Posibles respuestas:**

* `201 Created`: Película creada exitosamente
* `400 Bad Request`: Datos inválidos (ej. título vacío)
* `409 Conflict`: Ya existe película con ese título y director

---

### Obtener película por ID

`GET /api/movies/id/{id}`

**Ejemplo:**

```
GET /api/movies/id/1
```

**Respuestas:**

* `200 OK`: Devuelve la película
* `404 Not Found`: No se encontró la película

---

### Obtener películas por año

`GET /api/movies/year/{releaseYear}`

**Ejemplo:**

```
GET /api/movies/year/2010
```

---

### Actualizar película

`PUT /api/movies/{id}`

**Body (JSON):**

```json
{
  "title": "Tenet",
  "director": "Christopher Nolan",
  "releaseYear": 2020,
  "genre": "Sci-Fi"
}
```

**Respuestas:**

* `200 OK`: Película actualizada
* `204 No Content`: ID no encontrado
* `400 Bad Request`: Datos inválidos
* `422 Unprocessable Entity`: Año inválido

---

## Validaciones aplicadas

| Campo         | Regla                                                   |
| ------------- | ------------------------------------------------------- |
| `title`       | No puede estar vacío, entre 2 y 100 caracteres          |
| `director`    | No puede estar vacío                                    |
| `releaseYear` | Mayor o igual a 1895 y menor o igual a (año actual + 2) |
| `genre`       | Opcional                                                |

Además de las validaciones estándar, se aplica la siguiente regla de negocio:

> El género `"documental"` no puede usarse si el año de lanzamiento es menor a 1920.
> No pueden crearse con peliculas con director y titulo ya existentes.


## Tecnologías utilizadas

* Java 17
* Spring Boot 3.x
* Spring Web, Spring Data JPA
* H2 Database (modo en memoria)
* Lombok
* Postman (para pruebas)

---

## Autor

* Cantalupi Sofia

---

## Estructura del proyecto


src/
├── controller/
│   └── MovieController.java
├── service/
│   └── MovieService.java
├── model/
│   └── Movie.java
├── movieRepository/
│   └── MovieRepository.java
├── validations/
│   └── MovieValidator.java
├── exception/
│   └── ApiError.java
│   └── DuplicatedMovieException.java
│   └── InvalidYearException.java
│   └── BusinessException.java
│   └── GlobalExceptionHandler.java
└── MovieApiApplication.java

---