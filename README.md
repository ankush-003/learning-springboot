# JShare
a platform for sharing payments hassle-free
## JPA
- JPA is a specification for accessing, persisting, and managing data between Java objects / classes and a relational database.
## Entities
- An entity is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
- Lombok is used to generate getters, setters, constructors, and other boilerplate code.
- The `@Entity` annotation is used to mark the class as an entity.
- The `@Id` annotation is used to mark the field as a primary key.
- In Lombok, the `@Data` annotation is used to generate getters, setters, constructors, and other boilerplate code.

## Repositories
- A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
```java
    public interface UserRepository extends JpaRepository<User, Long> {
    }
```
  
## Services
- A service is a component that performs operations on entities.
```java
    @Service
    public class UserService {
    }
```

- services, repositories, and entities are used to perform CRUD operations on the database, they separate the presentation layer from the persistence layer.
- persistence layer: the layer that interacts with the database.
- presentation layer: the layer that interacts with the user.
- `@Autowired` is used to inject a dependency into a class. (dependency injection is a technique in which an object receives other objects that it depends on.)

## Junit & Mockito
- JUnit is a unit testing framework for the Java programming language.
- Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API.
- `@Mock` is used to create mock objects.
- `@InjectMocks` is used to create and inject the mock object.
- `@RunWith` is used to run the test with another runner.
- use `when` to define the behavior of the mock object, `thenReturn` to define the return value of the mock object.
- `underTest` is used to test the class under test.
```java
    @RunWith(MockitoJUnitRunner.class)
    public class UserServiceTest {
          @Mock
          private UserRepository userRepository;
          @InjectMocks
          private UserService userService;
    } 
   ```

## Controllers
- A controller is a component that handles HTTP requests and returns an HTTP response.
- `@RestController` is used to create a RESTful web service.
- `@RequestMapping` is used to map web requests to specific handler classes and/or handler methods.
- `@RequestBody` is used to bind the HTTP request body to the method parameter.
- `@PathVariable` is used to bind the URI template variable to the method parameter.
- `@GetMapping` is used to map HTTP GET requests onto specific handler methods.
- `@PostMapping` is used to map HTTP POST requests onto specific handler methods.
- `ResponseEntity` represents an HTTP response, including headers, body, and status.
```java
    @RestController
    @RequestMapping("/api/v1/users")
    public class UserController {
    }
```

## Testing Controllers
- `@SpringBootTest` is used to test the application.
- `@WebMvcTest` is used to test the controller layer.
- `MockMvc` is used to test the controller layer, without starting the server physically.
- `MockMvcRequestBuilders` is used to prepare a request.


