# Loqo.ai_Assignment

# Product Management Application

This is a Spring Boot application for managing products. It provides endpoints to add a new product and retrieve products based on filtering and sorting criteria.

## Instructions on how to run the application

### Prerequisites

- Java 8 or higher
- Maven 3.6.0 or higher
- MySQL database

### Setup MySQL Database

1. Create a new MySQL database.
2. Update the `application.properties` file with your database configurations:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

### Build and Run the Application

1.Clone the repository:

- git clone https://github.com/your_username/your_repository_name.git
- cd your_repository_name

2.Build the application:

-mvn clean install

3.Run the application:

-mvn spring-boot:run

##The application will start on http://localhost:8087.

### Endpoints

1.Add a New Product

--URL: /products/saveproduct
--Method: POST
--Request Body:
{
  "name": "Product Name",
  "category": "Category",
  "price": 100.0,
  "inStock": true,
  "rating": 4.5,
  "createdAt": "2023-07-19T00:00:00.000Z"
}

--Response:
{
  "id": 1,
  "name": "Product Name",
  "category": "Category",
  "price": 100.0,
  "inStock": true,
  "rating": 4.5,
  "createdAt": "2023-07-19T00:00:00.000Z"
}

2.Retrieve Products

--URL: /products/getproduct
--Method: GET
--Query Parameters:

-category (optional): Filter by category.
-minPrice (optional): Filter by minimum price.
-maxPrice (optional): Filter by maximum price.
-inStock (optional): Filter by stock availability.
-sortField (optional): The field to sort by (one of price, rating, createdAt). Default is createdAt.
-sortOrder (optional): The sort order (asc or desc). Default is asc.

#Example Request:
-GET /products/getproduct?category=Electronics&minPrice=50&maxPrice=500&inStock=true&sortField=price&sortOrder=desc

--Response:
[
  {
    "id": 2,
    "name": "Product B",
    "category": "Electronics",
    "price": 400.0,
    "inStock": true,
    "rating": 4.0,
    "createdAt": "2023-07-18T00:00:00.000Z"
  },
  {
    "id": 1,
    "name": "Product A",
    "category": "Electronics",
    "price": 150.0,
    "inStock": true,
    "rating": 4.5,
    "createdAt": "2023-07-19T00:00:00.000Z"
  }
]








