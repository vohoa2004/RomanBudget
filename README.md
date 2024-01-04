# RomanBudget
## 1. Requirements:
This web application's name is RomanBudget.
As the name described, the web is built for manage regions and budget of the empire Roma. 
There are two different roles of people can access this website: emperor Augustus and the consuls - ones who are leaders of the regions.

Key features:
- Emperor managing the regions, officers, taxes and budget, ...
- Provide the officers with reliable tools for self-managing and report management (create report, report history, reply of the emperor, etc.)

## 2. Database design:


## 3. System design:
### Design Pattern:
This project is developed based on MVC2 architecture with Java Servlet and JSP. 
- MVC2 Architectue: When user send request, that request will be send first to Controller.
  Then, Controller request needed data from Model to put in the targeted view. Model handles data get from database and make business logic.
  After Model processed all needed data, Controller send signal to the target view.
  Then, that view take right data from Model and render or update its interface with those data.
  When the interface is done, View send signal to Controller, Controller annouce to user and user can see the targeted view with data.
### Technologies used:
- Java Servlet: A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.
- JSP: Java Server Pages (JSP) is a server-side programming technology that enables the creation of dynamic, platform-independent method for building Web-based applications.

## 4. Conclusion:
This application provides the basic features satisfying the given requirement. However, it needs to improve a lot in many aspects: UI/UX, Security,... and optimize performance.
