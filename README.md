# ECASA Project

The ECASA API allows the management of sections as well as the products corresponding to these sections in his warehouse

## Prerequisites
Java 8 or higher installed on the system and Apache Maven to handle the libraries.

## Notes

In application.properties file:
- replace database url for yours
  
  spring.datasource.url=jdbc:postgresql://localhost:port*/database_name*
- replace USERNAME and PASSWORD for yours
  
  spring.datasource.username=*
  
  spring.datasource.password=*

Once you have successfully executed the project, go directly to your database and in the Role table enter the roles ( ROLE_ADMINISTRADOR and ROLE_OPERARIO)
with their ID (integer). Then register a first user who by default will have the ROLE_OPERARIO. 
To give this user the ADMINISTRADOR role you must go to the user_role table and enter the ID of this user next to the ROLE_ADMINISTRADOR ID 
to give it administrator privileges.

## Base URL
- https://localhost:8080/*

## Authentication
JSON Web Token(JWT) is used to handle authentication and roles through the AuthController as an entry point with the registerUser() and authenticateUser() methods

## Endpoints

- url: api/sections
  
  HTTP Method:GET

  description: return all sections

  required parameters:none

- url: api/sections/createSection
  
  HTTP Method:POST
  
  description: create a section. You need to be authenticated
  
  required parameters:section size(double type data), Type of product to be contain (ELECTRODOMESTICO, CARNICO, ROPA o ASEO)
  
  JSON example to create a section:{
  
"sizeSection": 29,

"productType":"CARNICO"

}

- url: api/products/createProduct
  
  HTTP Method:POST
 
  description: create a product. You need to be authenticated
 
  required parameters:Type of product (Electrodomésticos, Cárnicos, Ropa y Aseo), price(double data type), product size(double data type), color (String data type), 
 lot (String data type), fragile(Boolean data type) and type of container (CARTON, PLASTICO, CRISTAL o NYLON).

 JSON example to create a product:{

"productType": "ASEO",

"price": 25,

"size":15,

"color":"grey",

"lot":"12390",

"fragile": true,

"containerType": "NYLON"

}

- url: api/sections/addProductToSection/idSection*/idProduct*?quantity=*
  
  HTTP Method:POST
  
  description: a quantity of product is added to a certain section
  
  required parameters:idSection (integer type data), idProduct (integer type data), quantity of product in that section (integer type data)

- url: api/products
  
  HTTP Method:GET
  
  description: return all sections
  
  required parameters:none

- url: api/sections/update/idSection*
  
  HTTP Method:PUT
  
  description: update a section. You need to be authenticated
  
  path parameter required:idSection (integer type data)
  
  JSON example to update a section:{
  
"sizeSection": 100,

"productType":"CARNICO"

}

- url: api/sections/delete/idSection*
  
  HTTP Method:PUT
  
  description: delete a section if it is empty. Only the user with ADMINISTRATOR role can delete a section
  
  path parameter required:idSection (integer type data)

- url: api/products/search
  
  HTTP Method:GET
  
  description: Filter the products by different optional parameters. If you do not enter any parameter, it returns all the products
  
  optional parameters:sectionId (integer data type), range of price(double data type), color (String data type), 
 lot (String data type), fragile(Boolean data type) and type of container (CARTON, PLASTICO, CRISTAL o NYLON).

- url: api/sections/update/idSection*
  
  HTTP Method:PUT
 
  description: update a section. You need to be authenticated

  path parameter required:idSection (integer type data)

 JSON example to update a section:{

"sizeSection": 100,

"productType":"CARNICO"

}

- url: api/auth/signup
  
  HTTP Method:POST

  description: Register an user

  path parameter required:name (String data type), lastname (String data type), username (String data type) and password (String data type).

  JSON example to sign up:{
  
    "firstname":"Gaby",

    "lastname":"Montea",

    "username":"gabeadmin",

    "password":"123"
  
}

- url: api/auth/signin
  
  HTTP Method:POST
 
  description: Allows a user to login

  path parameter required: username (String data type) and password (String data type).

  JSON example to login:{
  
    "username":"gabeadmin",
  
    "password":"123"
  
}

## Unit test
SectionControllerUnitTest: class that handles unit tests for SectionController
