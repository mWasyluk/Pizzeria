<h1>GM Pizza</h1>
<h2>What is it?</h2>
<i>GM Pizza</i> is an MPA which allows a user to register, create custom pizza with their favourite ingredients and place a dummy order. The application is using NoSQL database (MongoDB) for storing all data.
<h2>Features</h2>
<ul>
  <li>Account registration - <strong>/registration</strong>
  <li>Logging into the account - <strong>/login</strong>
  <li>Editting the account details after logging in - <strong>/account</strong>
  <li>Logging out of the account - <strong>/logout</strong>
  <li>Creating a pizza with selected ingredients and name - <strong>/design</strong>
  <li>Forming an order which might contain one or more pizzas - <strong>/orders/current</strong>
  <li>Placing a dummy order by providing a credit card details - <strong>/orders/form</strong>
  <li>Desplaying a history of orders with their details - <strong>/orders</strong>
</ul>
<h2>How to run it?</h2>
The <i>GM Pizza</i> app can be pulled as a Docker Image and run on the target machine. Full instructions are available in the documentation on Docker Hub: <a href="https://hub.docker.com/repository/docker/mwas0122/gmpizza-mpa">Docker Hub - GM Pizza</a>

<h2>Technologies</h2>
This project is built with following technologies:
<ul>
  <li>Java 8</li>
  <li>Spring Boot</li>
  <li>Spring Web</li>
  <li>Spring Security</li>
  <li><s>Spring Data JDBC</s></li>
  <li>Spring Data MongoDB</li>
  <li>Thymeleaf</li>
  <li>Lombok</li>
  <li>HTML</li>
  <li>CSS</li>
