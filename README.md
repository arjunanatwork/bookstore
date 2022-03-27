# Online Bookstore App

A Online Book Store App based on Spring Boot

## How to Run

** Running through cmd
 - Run 'mvn clean package' from project location
 - Run java -jar target/bookstore.jar
 - Open http://localhost:8090/swagger-ui/index.html to view application API Swagger 
 

** Running through Docker
 - mvn clean package
 - docker build -t bookstore:1.0 . (From the Dockerfile Location)
 - docker run -d -p 8090:8090 -t bookstore:1.0
 
** Others Steps **
 - Open http://localhost:8090/swagger-ui/index.html to view application API Swagger
 - Run the Author and Classification API to get the Ids of the enities
 - Run the 'Save Book API' by passing the relevant details along the Author and Classification ID
 - Run the 'Checkout API' by passing the Book IDs in a comma seperated fashion along with promoCode
	- A Test Promo Code(TEST10) is created for 'FRICTION' Classification
 - A Promo Code can added to the in-memory(http://localhost:8090/h2-console) table by inserting the relevant details
	-  For Example: INSERT INTO PROMOTION (id, classification_id, promotion_code, discount, created_date, last_updated_date,created_by,last_updated_by) VALUES
                    (promotion_seq.NEXTVAL , (select id from classification where name='FRICTION'), 'TEST10',10,SYSDATE, NULL, 'SYSTEM', NULL);


## Application Related Information

 - The application runs on port 8090
 - Sample Data are inserted using scripts which can be found in resources/scripts/tables.sql
 
** API Documentation for the Project **
 - Swagger Documention will be available on the url - http://localhost:8090/swagger-ui/index.html where we can perform CRUD operation
 
## Assumptions made while developing

 - User will be having Java 8+, Maven, Docker installed in his local PC


## Improvements

 - Error Handling
 - Comprehensive test cases can be written


