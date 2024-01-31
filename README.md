# Mini RESTful API Project

This is a simple Java-based mini RESTful API project that demonstrates basic CRUD (Create, Read, Update, Delete) operations. The project uses the [Spring Boot](https://spring.io/projects/spring-boot) framework to build a lightweight and easily deployable API.
You can download the Chrome extension [Talend API Tester](https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?pli=1) to experiment with API.

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - version 17 or later
- [Maven](https://maven.apache.org/download.cgi) - for building and managing the project dependencies
- Ensure Spring Boot version is 3.1 or higher
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [MySQL Shell](https://dev.mysql.com/doc/mysql-shell/8.0/en/mysql-shell-install-windows-quick.html)

### Installing

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/mini-restful-api-java.git
    ```

2. Navigate to the project directory:

    ```bash
    cd mini-restful-api-java
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/mini-restful-api.jar
    ```

The API will be accessible at `http://localhost:8080`.

## API Endpoints

### Create a new user

- **Endpoint**: `/users`
- **HTTP Method**: `POST`
- **Request Body**:

    ```json
    {
        "user_name": "John",
        "birth_date": "2014-01-01"
    }
    ```

### Get all users

- **Endpoint**: `/users`
- **HTTP Method**: `GET`

### Get a specific user

- **Endpoint**: `/users/{Id}`
- **HTTP Method**: `GET`

### Delete an item

- **Endpoint**: `/users/{Id}`
- **HTTP Method**: `DELETE`

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for building Java-based applications
- [Maven](https://maven.apache.org/) - Dependency management

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
