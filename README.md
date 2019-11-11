

# School Management System
> 

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
A web app using servlets, jsp and mySql database to create a student management system. 
The purpose of the project is to learn technologies mentioned above.


## Screenshots

* Login page with two options - student or teacher
![screenshot](https://user-images.githubusercontent.com/46251960/68549076-7222ac80-03f4-11ea-8b75-8df557736b28.png)

* Main page - teacher status
![screenshot](https://user-images.githubusercontent.com/46251960/68549092-a7c79580-03f4-11ea-8b86-4a1915fbd96a.png)

* Student status
![screenshot](https://user-images.githubusercontent.com/46251960/68623400-b6857980-04d4-11ea-940e-fbad7d8b5c4a.png)

* Search function
![screenshot](https://user-images.githubusercontent.com/46251960/68549101-c332a080-03f4-11ea-9582-be5a4f9d6aca.png)

* Add student function
![screenshot](https://user-images.githubusercontent.com/46251960/68623450-d9b02900-04d4-11ea-8553-ac3389f9efb7.png)

* Student's semestral results started when "More" link is clicked
![screenshot](https://user-images.githubusercontent.com/46251960/68549116-fe34d400-03f4-11ea-8c42-c9a7da510d6c.png)

* Add semester function
![screenshot](https://user-images.githubusercontent.com/46251960/68549154-5c61b700-03f5-11ea-9c79-05b346d48d7d.png)

* Prompt the user before saving semester results
![screenshot](https://user-images.githubusercontent.com/46251960/68549292-e3635f00-03f6-11ea-86e8-54efd8c486ca.png)

* Prompt the user before deleting a student
![screenshot](https://user-images.githubusercontent.com/46251960/68549302-f70ec580-03f6-11ea-97ff-edf58174a047.png)

* Update student
![screenshot](https://user-images.githubusercontent.com/46251960/68623483-e9c80880-04d4-11ea-8808-b7579f9961b7.png)

## Technologies
* Java EE, Compiled using JDK 11, Eclipse 2019-06 (4.12)
* Servlets, jsp (jstl, scriptlets), jdbc
* Apache Tomcat v9.0 Server
* MySql 5.1.38


## Setup
* Project facets - Dynamic Web Module 4.0, Java 11, JavaScript 1.0;


## Features
* Login as teacher, application checks if email and password written by the user exist in database. If not, it sends the user
back to login page, if yes - user is logged in;

   * Features of the teacher view:
     - add student;
     - delete student;
     - update personal data (form which pops up is prepopulated with current data of student from database);
     - "More" link - displays "Student's semestral results" view. Here are displayed all the marks of the student
       from all the semesters;
     - add semester fuction which enables teacher to write in semester results of a given student for a given school year;
     - prompt the teacher before deleting and adding new semester;
     - logout - user is sent back to login page.

* Features to be added:

    - Login as student;
    - Student view should enable user to view only his grades and semesters;
    - Option of sign in on log in page;
    - Student who received mark "2" should be prompted by sending automatically email to his adress;
    - Block possibility of adding empty grades and school years and assign them some range;
    - Convert project to Maven and deploy it to free server to test it out.

## Status
Project is in progress

## Inspiration
Project is inspired on series of tutorials of Chad Darby on JSP, Servlets and JDBC.

