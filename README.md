# Cookbook Application

## Objective

This standalone Java application allows users to manage their favorite recipes. Users can add, update, remove, and fetch recipes. 
Additionally, users can filter available recipes based on the following criteria:
1. Whether the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions

### Example Search Requests
- All vegetarian recipes
- Recipes that can serve 4 persons and have "potatoes" as an ingredient
- Recipes without "salmon" as an ingredient that have "oven" in the instructions

## Requirements

1. It must be a REST application implemented using Java
2. Your code should be production-ready
3. REST API must be documented
4. Data must be persisted in a database
5. Unit tests must be present
6. Integration tests must be present

## Architectural Choices

This application is built using the following technologies and frameworks:
- **Spring Boot**: For building the RESTful web services
- **Hibernate**: For ORM (Object-Relational Mapping) and database interactions
- **H2 Database**: For in-memory database during development and testing
- **PostgreSQL**: For production
- **JUnit**: For unit testing
- **Mockito**: For mocking in unit tests
- **Spring Cloud Contract**: For integration testing

### Why These Choices?

- **Spring Boot**: Simplifies the development of RESTful services and provides production-ready features out of the box.
- **Hibernate**: Provides a powerful and flexible ORM framework.
- **H2 Database**: Lightweight and fast in-memory database, ideal for development and testing.
- **PostgreSQL**: Robust and reliable with advanced features for complex queries and indexing.
- **JUnit & Mockito**: Well-known frameworks for writing and running unit tests efficiently.
- **Spring Cloud Contract**: Generates and executes tests from contracts.
- **Spring Test**: Integrates well with Spring Boot for comprehensive integration testing.

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle 8.7 or higher

### Installation

1. **Clone the repository**
   ```sh
   git clone https://github.com/sergey-rubtsov/book.git
   cd book

2. **Build the project**
   ```sh
   ./gradlew build
   ```

3. **Run the application**
   ***With test local H2 DB***
      ```sh
      ./gradlew bootRun --args='--spring.profiles.active=local'
      ```
   ***Using docker with Postgres DB***
      ```sh
        docker-compose -f docker-compose.yaml -p book up -d postgres
        docker-compose -f docker-compose.yaml -p book up -d book
      ```

### Running Tests

To run unit and integration tests, execute:
```sh
./gradlew test
```

## API Documentation

The REST API is documented using OpenAPI. Once the application is running, you can access the API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

## Usage

### Adding a Recipe

- **Endpoint**: `POST /api/recipes`
- **Request Body**:
  ```json
  {
    "title": "Veggie Delight",
    "servings": 4,
    "ingredients": [
        {"name": "carrots", "category": "VEGETARIAN"}, 
        {"name": "potatoes", "category": "VEGETARIAN"},
        {"name": "peas", "category": "VEGETARIAN"}
    ],
    "instructions": "Boil the vegetables and mix them well."
  }
  ```

### Updating a Recipe

- **Endpoint**: `PUT /api/recipes/{id}`
- **Request Body** (by title):
  ```json
  {
    "title": "Veggie Delight Updated",
    "servings": 4,
    "ingredients": [
      {"name": "carrots", "category": "VEGETARIAN"}, 
      {"name": "potatoes", "category": "VEGETARIAN"},
      {"name": "peas", "category": "VEGETARIAN"}, 
      {"name": "corn", "category": "VEGETARIAN"}
    ],
    "instructions": "Boil the vegetables, add corn and mix them well."
  }
  ```
- **Request Body** (by id):
  ```json
  {
    "id": 1,
    "title": "Veggie Delight Updated",
    "servings": 4,
    "ingredients": [
      {"name": "carrots", "category": "VEGETARIAN"}, 
      {"name": "potatoes", "category": "VEGETARIAN"},
      {"name": "peas", "category": "VEGETARIAN"}, 
      {"name": "corn", "category": "VEGETARIAN"}
    ],
    "instructions": "Boil the vegetables, add corn and mix them well."
  }
  ```

### Removing a Recipe

- **Endpoint**: `DELETE /api/recipes/{id}`

### Fetching Recipes

- **Endpoint**: `GET /api/recipes`
- **Query Parameters**:
   - `vegetarian` (optional): `true` or `false`
   - `servings` (optional): number of servings
   - `include` (optional): list of ingredients to include
   - `exclude` (optional): list of ingredients to exclude
   - `instructions` (optional): text search within instructions, case-sensitive
   - `page` (optional): number of page
   - `size` (optional): number of recipes per page
