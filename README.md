# Spring Security Demo

This is a basic REST API built to experiment with the Spring Security module in Spring Boot.

The application defines a custom `SecurityFilterChain` via the `SecurityConfiguration` class. This configuration, in conjunction with an `AuthenticationManager` and a `DaoAuthenticationProvider`, secures the entire API.

The `DaoAuthenticationProvider` uses a `PasswordEncoder` and relies on a custom implementation of both `UserDetails` and `UserDetailsService` to authenticate and authorize users from a SQL database.

## Endpoints

### `GET /notSecured`
Public endpoint. Accessible to all users without authentication.

### `GET /secured`
Protected endpoint. Accessible only to authenticated users.

### `GET /admin`
Restricted endpoint. Accessible only to authenticated users with the `ROLE_ADMIN` authority.

## Testing

The project includes integration tests for all security rules using Spring Boot's `@SpringBootTest` and `MockMvc`, covering the following scenarios:

- Unauthenticated access to public endpoints  
- Authenticated access to protected endpoints  
- Forbidden access for authenticated users without the required role  
- Successful access for authenticated users with the correct role  
- Automatic rejection of unauthenticated access to protected endpoints

Additionally, unit tests are provided for the custom `UserDetailsService` implementation, verifying both successful lookups and proper exception handling when users are not found.
