# RomanBudget
## Requirements:
This web application's name is RomanBudget.
As the name described, the web is built for manage regions and budget of the empire Roma. 
There are two different roles of people can access this website: emperor Augustus and the consuls - ones who are leaders of the regions.

## Key features:
### Management of Regions:
Region CRUD (Create, Read, Update, Delete):
Ability to add, edit, and delete information about regions such as Italia, Gaul, Britannia, Africa, Asia Minor, etc.
Store detailed information about each region and smaller areas within (tribes, kingdoms), including names, market values, growth rates, rebellion rates, and food supply.

Consuls CRUD:
Add, edit, and delete information about each Consul: name, age, address, number of terms, salary, noble status.

### Budget and Tax Management:
Territory Budget Management:
Monitor the budget for each territory, including income and expenses.
Tax Collection and Tribute:
Provide functionality for tax collection from regions and manage tax data.
Track tribute payments from regions to the Empire.

### Report Management:
Report Creation and Prediction:
Consuls can generate reports on the region's status, make future predictions, and provide other requested information by the Empire.
Store reports and predictions for review and assessment purposes.

### Communication and Task Management:
Request Reports from Consuls:
Ability to request reports from Consuls regarding the region's situation.
Store sent and received reports from Consuls, including information on dates of writing, sending, and positions.

### Consuls Task Management:
Provide Consuls with tools to create reports, view report history, and receive feedback from the Empire.

### User Management:
Authorization and Administration:
Build an authorization system to manage access rights and functionalities for Consuls and the Empire.
User-Friendly Interface:
Develop a user-friendly interface for Consuls and the Empire to manage information and interact with the system.


## System design:
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

## Conclusion:
This application provides the basic features satisfying the given requirement. However, it needs to improve a lot in many aspects: UI/UX, Security,... and optimize performance.
