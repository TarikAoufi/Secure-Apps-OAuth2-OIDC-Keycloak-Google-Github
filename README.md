# Secure-Apps-OAuth2-OIDC-Keycloak-Google-Github

## Project Description:

This project stands out for its implementation of a secure and modern distributed architecture, comprising four main components within a Maven multi-module project:

 1. Product-Service: A microservice dedicated to product management, providing a REST API for interacting with inventory data.

 2. Customer-Service: A microservice specialized in customer management, offering a REST API for manipulating customer information.

 3. Front-App-Thymeleaf: A frontend application based on Spring Boot and Thymeleaf, generating server-side HTML rendering for customer management. It directly communicates with Product-Service.

 4. Front-App-Angular: A frontend application developed with Angular, generating client-side HTML rendering. It interacts with both Product-Service and Customer-Service.

Security and user experience are at the core of this project, with a particular focus on authentication and authorization:

⦁ Authentication:
    
    ⦁ Front-App-Thymeleaf supports user authentication via Google, GitHub, or Keycloak.
        
    ⦁ Front-App-Angular uses Keycloak Angular to manage user authentication and authorization.

⦁ Authorization: 

    Access to features is controlled based on user roles, with mechanisms to display or hide certain functionalities according to assigned permissions. 
    Both frontend applications implement role-based access controls, ensuring secure management of CRUD (Create, Read, Update, Delete) operations.

For security, three different providers are used: Google, GitHub, and Keycloak. Each frontend and backend application is secured using specific methods, ranging from form-based authentication to the use of JWT (JSON Web Tokens) for API REST authentication. Security best practices are rigorously followed throughout the development process.

The microservices are secured in "bearer only" mode, requiring a JWT in the request for access. The frontend applications employ redirection and interception mechanisms to ensure user authentication and authorization.

Technologies Used:

    ⦁ Spring Boot 3
    ⦁ Java 21
    ⦁ REST API
    ⦁ Keycloak
    ⦁ JWT (JSON Web Tokens)
    ⦁ OAuth 2.0
    ⦁ OpenID Connect (OIDC)
    ⦁ Maven
    ⦁ PostgreSQL
    ⦁ Angular 17
    ⦁ Thymeleaf
    ⦁ SLF4J
    ⦁ Bootstrap 5
    ⦁ CSS
    ⦁ Lombok

In summary, this project demonstrates the implementation of a secure distributed architecture within a Maven multi-module setup, utilizing modern technologies for the development of frontend and backend applications, while ensuring fine-grained and secure management of roles and access.
