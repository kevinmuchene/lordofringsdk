# Lord of the Rings SDK

This Java SDK provides an easy way to interact with Movie and Quote APIs. It offers functionality to fetch movie details, quotes, and apply filters to your requests. 
The SDK is built using Apache HttpClient, Jackson for JSON processing, and SLF4J for logging.

## Requirements

1. Java 8+
2. Maven

## Dependencies

This SDK requires the following dependencies. Add them to your pom.xml

### Adding Lord of the Rings SDK

To use the `lord-of-ring-1.0-SNAPSHOT.jar` in this project, follow the steps below:

#### 1. Place the JAR file in your project directory

Download the `lord-of-ring-1.0-SNAPSHOT.jar` and place it in the root directory of your project. And then updated pom.xml.



    <dependency>
        <groupId>com.lordofring</groupId>
        <artifactId>lord-of-ring</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lord-of-ring-1.0-SNAPSHOT.jar</systemPath>
    </dependency>

    <!-- Apache HttpClient for API requests -->
    <dependency>
        <groupId>org.apache.httpcomponents.client5</groupId>
        <artifactId>httpclient5</artifactId>
        <version>5.4</version>
    </dependency>
    
    <!-- SLF4J for logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.16</version>
    </dependency>
    
    <!-- Jackson for JSON parsing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.18.0</version>
    </dependency>
    
    <!-- JUnit 5 for testing -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.7.1</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Mockito for mocking dependencies in tests -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-inline</artifactId>
        <version>5.0.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.12.0</version>
        <scope>test</scope>
    </dependency>

**Note**:
**Ensure that the path ${project.basedir}/lord-of-ring-1.0-SNAPSHOT.jar points to the correct location of the JAR file in your project directory.**

## Usage

### Setting Up

Set the SDK with your own **Bearer Token** for authentication

#### ApiConfig.setBearerToken("Your-Bearer-Token");

#### MovieService movieService = new MovieServiceImpl(ApiConfig.getBearerToken());

#### QuoteService quoteService = new QuoteServiceImpl(ApiConfig.getBearerToken());

## Examples
### Fetch Movies


```java
public static void Main(String[][] args) {
    ApiResponse<List<Movie>> movies = movieServiceImpl.getMovies();
    
    if(movies.isSuccess()) {
        for(Movie movie : movies.getData()) {
            System.out.println(quote.toString());
        }
    } else {
        System.out.println(movies.getMessage());
    }
}
```
### Fetch Quotes

```java
public static void Main(String[][] args) {
    ApiResponse<List<Quote>> quotes = quoteServiceImpl.getQuotes();
    
    if(quotes.isSuccess()) {
        for(Quote quote : quotes.getData()) {
            System.out.println(quote.toString());
        }
    } else {
        System.out.println(quotes.getMessage());
    }
}
```

### Fetch Movie by ID

```java
public static void Main(String[][] args) {
    
    String movieId = "5cd95395de30eff6ebccde583";

    ApiResponse<Movie> movie = movieServiceImpl.getMovieById(movieId);

    if(movie.isSuccess()) {
        System.out.println(movie.getData().toString());
    } else {
        System.out.println(movie.getMessage());
    }
}
```

### Fetch Quote by ID

```java
public static void Main(String[][] args) {
    
    String movieId = "5cd96e05de30eff6ebcce7e9";

    ApiResponse<Quote> quote = quoteServiceImpl.getQuoteById(quoteId);

    if(quote.isSuccess()) {
        System.out.println(quote.getData().toString());
    } else {
        System.out.println(quote.getMessage());
    }
}
```

### Fetch Quote by Movie ID

```java
public static void Main(String[][] args) {
    
    String movieId = "5cd95395de30eff6ebccde5c";

    ApiResponse<List<Quote>> movieQuotes = quoteServiceImpl.getQuotesByMovieId(movieId);

    if(movieQuotes.isSuccess()) {
        for(Quote quote : movieQuotes.getData()) {
            System.out.println(quote.toString());
        }
    } else {
        System.out.println(movieQuotes.getMessage());
    }
}
```

### Filtering Results

The SDK allows to filter the fetched data using Filters. You can pass a list of Filter objects with different conditions.

#### Example
```java
public static void Main(String[][] args) {

    List<Filter> filters = new ArrayList<>();
    filters.add(new Filter("character", FilterType.EQUALS, "5cd99d4bde30eff6ebccfe9e")); // Match character ID
    filters.add(new Filter("movie", FilterType.EQUALS, "5cd95395de30eff6ebccde5d")); // Match movie ID

    ApiResponse<List<Quote>> filteredQuotes = quoteServiceImpl.getFilteredQuotes(filters);

    if(filteredQuotes.isSuccess()) {
        for(Quote quote : filteredQuotes.getData()) {
            System.out.println(quote.toString());
        }
    } else {
        System.out.println(filteredQuotes.getMessage());
    }

}
```

The available filter types are:
```
public enum QueryFilterType {
    EQUALS,
    NOT_EQUALS,
    INCLUDE,
    EXCLUDE,
    EXISTS,
    NOT_EXISTS,
    REGEX,
    LESS_THAN,
    GREATER_THAN,
    LESS_THAN_EQUAL,
    GREATER_THAN_EQUAL;
}

```

### API Response Handling

The ApiResponse<T> class is used to wrap all responses from the SDK. It contains the following fields:

**success (boolean)**: Indicates if the request was successful.

**message (String)**: A message providing additional information (e.g., error messages).

**data (T)**: The actual data returned from the API (e.g., List of Movies or Quotes).

**Example**

```
  if(response.isSuccess()) {
  
    // Process data
    List<Movie> movies = response.getData();
    
   } else {
   
    // Handle error
    System.out.println(response.getMessage());
    
}
```
If an Unauthorized response is received (status code 401), the message will be **"Unauthorized".**

### Testing
### Overview

This project uses an **in-memory testing** approach to simulate HTTP requests and responses without relying on external services or mocking third-party libraries. By using an in-memory implementation of the `HttpRequestExecutorInterface`, we are able to test the core functionality of the SDK, including `MovieService` and `QuoteService`, in isolation.

### Running Unit Tests
To run the unit tests for this project:

1. Navigate to the project root directory.
2. Run the tests individually using your preferred IDE.