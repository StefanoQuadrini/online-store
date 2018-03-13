<h1 align="center">
    OnlineShopService
</h1>

A RESTful backend API with five services:
- Product service (*product entity: productId, name, price,description,categories*)
- Clothing service (*clothing entity: size, brand, colour*)
- HandMade service (*handmade entity: type, madeIn*)
- Videogame service (*videogame entity: platform, plot*)
- Cell phone service (*cellphone entity: operatingSystem, display,processor,camera,warrantyPeriod*)



## Toolset
- Spring Boot
- Spring MVC
- Spring Data JPA
  * out-of-the-box DAO-generation at runtime via method-naming conventions
  * Inheritance with SINGLE_TABLE strategy
- Embedded db H2
- Maven
- Git
- Mockito
- Lombok
## Features
### Implemented requirements
- endpoint for providing to a merchant the possibility to offer goods (in Json). The good are divided in four categories(clothing, handmade, videogame, cellphone) 
- endpoint for creating products of each category
- the embedded DB is initialized with a set of products.
 - backend services able to handle multiple POST requests independently at the same time
 - backend services able to handle GET for a single product or for a whole category

### DB initialization
- with the start of the application, the database is populated with a set of products.
- the product inizializated in DB have the follow id: 1, 2, 3, 4, 5.
   
### Authentication
- no authentication



### Unit and Integration Testing
- mocking
- service unit testing
- repository integration testing with embedded in-memory database accessed through Spring JPA *TestEntityManager*
- controller integration testing using *MockMvc* instance to setup a Spring MVC context with a web server

## Prerequisites
- Requires at least Java Runtime 1.8 - [download](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## Quick start
Below all the commands to clone, build and run the project with Maven and Java 8 JDK:
- `git clone https://github.com/StefanoQuadrini/online-store.git`
- `cd online-store/com-quad-ws-shop-web`
- `mvn clean install`
- `java -jar target/com-quad-ws-shop-web-0.1.jar`
- the embedded servlet container starts at `http://localhost:4000`

## Running
- to manual-testing the Rest application use Postman or similar tools.
### POST Videogame 
- URL is `http://localhost:4000/quad/ws/shop/videogame`

JSON examples to POST a videogame:
````
 {
        "productId": 10,
        "name": "Call of Duty",
        "price": 50.9,
        "description": "Amazing War game",
        "categories": "VIDEO_GAMES",
        "platform": "Playstation 3",
        "plot": "exciting shooting game"
    }
````

### POST Clothing 
- URL is `http://localhost:4000/quad/ws/shop/clothing`

JSON examples to POST a clothing:
````
{
        "productId": 12,
        "name": "Jacket",
        "price": 300,
        "description": "very elegant",
        "categories": "CLOTHING",
        "size": "XL",
        "brand": "Valentino",
        "colour": "black"
    }
````

### POST Cell Phone 
- URL is `http://localhost:4000/quad/ws/shop/cellphone`

JSON examples to POST a cellphone:
````
{
        "productId": 14,
        "name": "Huawei",
        "price": 250.33,
        "description": "Goog Quality respect price",
        "categories": "CELL_PHONES",
        "operatingSystem": "Android",
        "display": "Camera 5x",
        "processor": "i7",
        "camera": "F55Px",
        "warrantyPeriod": 0
    }
````

### POST Handmade 
- URL is `http://localhost:4000/quad/ws/shop/handmade`

JSON examples to POST a handmade:
````
{
        "productId": 15,
        "name": "earrings",
        "price": 110.36,
        "description": "All the component are worked with hands",
        "categories": "HANDMADE",
        "type": "original handmade product",
        "madeIN": "Italy"
    }
````


### GET  views of a product details  by *productId*

- URL is `http://localhost:4000/quad/ws/shop/{productId}`

Examples of returned JSONs:

**http://localhost:4000/quad/ws/shop/1**
````
{
    "productId": 2,
    "name": "Huawei",
    "price": 250.33,
    "description": "Goog Quality respect price",
    "categories": "CELL_PHONES",
    "operatingSystem": "Android",
    "display": "Camera 5x",
    "processor": "Cortex-A73",
    "camera": "F55Px",
    "warrantyPeriod": 0
}
````
### GET  views of all products in a category by *categories*

- URL is `http://localhost:4000/quad/ws/shop/category/{category}`

- The categories are:  VIDEO_GAMES,CELL_PHONES,CLOTHING,HANDMADE
Examples of returned JSONs:

**http://localhost:4000/quad/ws/shop/category/VIDEO_GAMES**
````
[
    {
        "productId": 1,
        "name": "Call of Duty",
        "price": 50.9,
        "description": "Amazing War game",
        "categories": "VIDEO_GAMES",
        "platform": "Playstation 3",
        "plot": "world war history"
    },
    {
        "productId": 5,
        "name": "Battlefield",
        "price": 100,
        "description": "Fantascientific War game",
        "categories": "VIDEO_GAMES",
        "platform": "Playstation 4",
        "plot": "Galactic war"
    }
]
````

