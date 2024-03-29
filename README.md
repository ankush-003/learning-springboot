# JShare
a platform for sharing payments hassle-free
## JPA
- JPA is a specification for accessing, persisting, and managing data between Java objects / classes and a relational database.

## Spring App Layers
![image](https://github.com/ankush-003/learning-springboot/assets/94037471/d6884874-40cc-46ef-828c-f7e7db20ee1c)
![image](https://github.com/ankush-003/learning-springboot/assets/94037471/e5fdd083-50d9-4bf3-b0d9-d5b442a7c820)
![image](https://github.com/ankush-003/learning-springboot/assets/94037471/39847976-2a28-4cdb-bf42-43fa52c870db)

## Bean 
- A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.
- two ways to define a bean:
  - XML configuration file
  - Java annotation
- `@Component` is used to mark the class as a Spring bean.
- `@Autowired` is used to inject a dependency into a class.
```java
    @Component
    public class UserService {
    }
```
- `@Configuration` is used to define a configuration class, which contains methods annotated with `@Bean`. This is another method to define a bean.
```java
    @Configuration
    public class AppConfig {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
```
- `@ConfigurationProperties` is used to bind the configuration properties to a class (taken from the `application.yml` file).
```java
    @Configuration
    @ConfigurationProperties(prefix = "app")
    public class AppProperties {
        private String name;
        private String description;
    }
```
- `@Value` is used to inject a value into a field.
```java
    @Component
    public class UserService {
        @Value("${app.name}")
        private String name;
    }
```

## Entities
- An entity is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
- Lombok is used to generate getters, setters, constructors, and other boilerplate code.
- The `@Entity` annotation is used to mark the class as an entity.
- The `@Id` annotation is used to mark the field as a primary key.
- In Lombok, the `@Data` annotation is used to generate getters, setters, constructors, and other boilerplate code.
- Lombok also provides `@Slf4j` to generate a logger.
```java
    @Entity
    @Data
    @Slf4j
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
    }
```

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
- `verify` is used to verify that a method has been called, we can use `times` to specify the number of invocations.

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

> `@DirtyContext` is used to indicate that the test method has modified the context and that the context should be reset after the test method has been executed.

```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    @AutoConfigureMockMvc
    @DirtyContext
    public class UserControllerTest {
    }
```

## Connecting to CockroachDB
- CockroachDB is a distributed SQL database built on a transactional and strongly-consistent key-value store.
- `spring.datasource.url` is used to specify the database URL.
- Update the `application.yml` file with the database URL.
```yaml
spring:
  datasource:
    url: <Visit the CockroachDB UI to get the URL>
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          lob:
            non_contextual_creation: true
      database: postgresql
      database-platform: org.hibernate.dialect.PostgreSQLDialect
```

## Postman Testing 🚀
![image](https://github.com/ankush-003/learning-springboot/assets/94037471/bd1c9a5a-2cf9-4365-91a2-977ab1ba8c29)


