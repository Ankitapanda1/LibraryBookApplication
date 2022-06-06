<h1> App Feature Description</h1>
<div>
<ul>
<li>JDK 18</li>
<li>Maven Build</li>
<li>Spring boot web – (For exposing REST API’s)</li>
<li>Spring boot JPA – (For CRUD operations)</li>
<li>SpringRunner – (For creating unit test cases)</li>
<li>Spring boot Validation – ( For native & Custom bean validation.Checks ISBN number is 13 digit or not)</li>
<li>Spring Doc – (for Open API document specification)</li>
<li>GitHub – (For version control )</li>
<li>IntellijIdea– (IDE for web development)</li>
<li>MySQL – For database</li>
</ul>
</div>

<h1>prerequisite</h1>
<li>Before starting the application create a  sql schema with name library</li>
<h1>Calss Description</h1>
<div>
<ul>
<li>config/GlobalExceptionHandler .java - Custom global error handling for Rest API's</li>
<li>config/ISBNValidation. java - Custom Bean Annotation for ISBN number</li>
<li>config/ISBNValidator.java - Custom validation for ISBN number</li>
<li>controller/BookController.java - Exposes rest API's for the library</li>
<li>model/Book. java - Entity/Bean class for Book</li>
<li>model/ValidationErrorResponse. java - Model wrapper for custom error handling</li>
<li>model/Violation. java - Model for custom error handling</li>
<li>presistence/BookRepository.java - Native ORM for CRUD operations</li>
<li>presistence/CustomBookRepository.java - Interface for Custom ORM</li>
<li>presistence/CustomRepositoryImpl.java - Implementation for Custom ORM</li>
<li>service/BookService. java.  - Controller for exposing API’s</li>
<li>service/BookServiceImpl. java.  - Controller for exposing API’s</li>
<li>LibraryDemoApplication.java. - Spring boot App initialiser</li>
<li>/resources/application.properties- Application configuration file</li>
<li>/resources/data.sql - SQL queries for inserting Mock Data</li>
<li>/test/BookControllerTest.java - Unit test cases for REST API’s contoller methods</li>
<li>/pom.xml - Maven Build file</li>
</ul>
</div>
