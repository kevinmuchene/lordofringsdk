# SDK Design

## Overview
This SDK is designed to interact with a Lord of the Rings API. It provides services for fetching data related to movies and quotes from the API. The SDK is structured to be modular and scalable. Each service is responsible for handling a specific aspect of the data and uses shared utilities and configurations to perform HTTP requests efficiently.

## Project Structure
The SDK is organized in the following main components:

### 1. API Layer (api)
This layer is responsible for handling API configuration and responses.

**ApiConfig**: This class contains the configuration required to interact with the external API, such as base URLs and API keys.

**ApiResponse**: This class represents a standard structure for handling API responses.

### 2. Filter System (filters)
The SDK provides a flexible filtering system to narrow down API responses.

**QueryFilter**: This is a model that allows the definition of various filters, such as filtering results by specific criteria.

**QueryFilterType**: This class defines the types of filters that can be applied when querying the API, which helps with customization of requests.

### 3. Models (models)
Models represent the data structures returned by the API.

**Movie**: This model represents a movie, containing fields like ID, name, runtime, budget, and award information.

**Quote**: This model represents a quote, which can be associated with characters or movies.

### 4. Services (services)
The SDK uses services to encapsulate the logic for fetching and processing data from the API.

#### 4.1 Base Service (AbstractService<T>)
A generic abstract class that provides common functionality for fetching lists and single items from the API. It can be extended by concrete services to reduce repetition.

**Filtering logic**: The abstract service includes support for filtering data based on user-defined criteria.

#### 4.2 Movie Service (MovieService and MovieServiceImpl)
Handles fetching and processing of movie-related data from the API.
Extends AbstractService<Movie>, inheriting the generic fetching logic and adding movie-specific features.

#### 4.3 Quote Service (QuoteService and QuoteServiceImpl)
Manages fetching and processing of quotes.
Also extends AbstractService<Quote>, reusing the fetching logic for single and list-based data requests.

### 5. JSON Processing with Jackson
The SDK utilizes Jackson for handling JSON serialization and deserialization. Jackson is used to map JSON responses from the API into Java objects, making it easier to work with the data returned from API requests. The following areas make use of Jackson:

**Deserialization**: Jackson is used to convert JSON responses into model objects such as Movie and Quote.

**ObjectMapper**: An instance of Jackson’s ObjectMapper is used throughout the SDK for parsing API responses. It simplifies the process of transforming JSON strings into usable Java objects.

### 6. Test Structure
#### In-Memory Testing Approach

In order to ensure robust and reliable tests, I have implemented an **in-memory testing approach** for the `HttpRequestExecutorInterface`. Instead of mocking third-party libraries or external HTTP calls, I use an in-memory implementation of this interface. This approach allows me to simulate HTTP responses and handle different scenarios, such as success, unauthorized access, and failure, without making actual network requests.

