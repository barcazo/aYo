# aYo
aYo Technical Assessment

As per the requirements, a Spring Boot application has been created using Maven build tool. 
TestRestTemplate instance is spun up to facilitate integration testing by triggering all predefined endpoints.

The application has also been containerized by means of a <code>Dockerfile</code> located in the base directory. 
Specifying a file that contains native Docker commands to build the image. Theimage can be built from the root directory (ayoholdings) 
using the <code>docker build -t.</code> command. The image can then be spun up using <code>docker run -p 8090:8080 < your_tagged_image > </code> and will be exposed on 
port 8090 as per the <code>Dockerfile</code>. Alternatively you may use Docker Desktop to manage images and containers 
once the image is built and tagged.


## Solution

As per the requirements, a solution was designed that exposed a single REST API with multiple conversions between two 
metric and imperial systems. 

An example would be as follows:
http://localhost:8090/api/metric/weight/105.3 where <code>weight</code> is the type, <code>105.3</code> is the value 
to be converted which is in pounds and <code>metric</code> is the system measurement to convert the value into.

The selected measurements were:
1. Area - allowing conversions between Acre and SquareMetres (and vice-versa)
2. Length - allowing conversions between Mile and Kilometer (and vice-versa)
3. Temperature - allowing conversions between Fahrenheit and Celsius (and vice-versa)
4. Volume - allowing conversions between Galon and Litre (and vice-versa)
5. Weight - allowing conversions between Pound and Kilogram (and vice-versa)
  
NB:
  
All formulas are taken from https://convert.french-property.co.uk  

