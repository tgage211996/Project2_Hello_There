# Project2_HelloThere

## Project Description
This system allows for quick and effecient handling and viewing of expense reimbursement requests. Managers are able to view any submitted requests, and can filter those requests by a single employee or by pending status. Managers can also approve or deny any pending requests. Employees are able to submit new requests, and they are able to see the status of all of their previous requests.

## Technologies Used
* Java - version 1.8
* Intellij Ultimate  - 2020.2.4
* Apache Tomcat - version 8.5.60
* Apache Maven - version 3.6.3
* AWS RDS - db.t2.micro
* DBeaver
* Hibernate
* HTML/CSS/JavaScript
* Angular
* Fetch API
* JUnit
* log4J
* JDBC
* Bootstrap
* SQL
* Mockito
* Servlets
* Spring
* Jackson
* HikariCP
* Node.js

## Features

Features ready

* Make a post
* Sign up
* comment on a post
* edit a post
* like other posts
* view other posts

To-do list:
* update user information
* login with facebook
* see other peoples profiles with their posts followers and information 
* add friends/followers 
* view past posts 
* message another user 
* user can check their dm history 
* delete your account 
* block other users so they cant see them 
* have a notification stream button 
* Post an image 
* hide parts of their profile
* create, register, and change for events  
* add a location to where they are on a post
* see if another user is online



## Getting Started

Here is the git clone command for windows and Unix that will clone the project in the specifide directory you desire
> git clone https://github.com/tgage211996/Project2_Hello_There.git

Step 1: Download and Setup Java
- Download Java 1.8 from oracle https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- video to help you through the setup https://www.youtube.com/watch?v=GcG2nzYn1PA&ab_channel=RomaRomanik

Step 2: Download and Setup Node.js
- Download the appropriate LTS version that matches your OS and follow the installation instructions and leave options at their default

Step 3: Download and Setup Angular
- if you want a deep explanation on how to get Angular going and more tutorials go to https://angular.io/guide/setup-local
- Dowload Angular's CLI with the following command in your OS command line
> npm install -g @angular/cli

Step 4: Download and Setup IntelliJ
- Download IntelliJ Ultimate or Community (I will explain when project setup may differ and I will explain how to setup up for both versions) from https://www.jetbrains.com/idea/download/#section=windows

Step 5: Download and Setup Tomcat
- Download Apache Tomcat 8 embedded zip file from https://tomcat.apache.org/download-80.cgi
- Video to help install Tomcat https://www.youtube.com/watch?v=yDAXfPxUGPE&ab_channel=CodeTechClub


Step 6: Download and Setup Maven
- Download Maven from https://maven.apache.org/download.cgi
- Video to help install Maven https://www.youtube.com/watch?v=RfCWg5ay5B0&ab_channel=CodingMagic

Step 7: Setup an Acount with AWS and create a RDS instance
- Go to AWS https://aws.amazon.com/console/
- click "sign in to consol" in the top right of the browser
- click "create a new AWS account" and go through the process to create the process"
- once you have create an account and you are at the home page, type "RDS" in the search bar at the top and click on "RDS: Managed Relational Database Services"
- click the "create database" button that is orange and will be on the page you just arrived to
- once you have options available, change the database "Engine Options" to PostgreSQL
- Scroll down to the next section, "Template options", and select Free tier.
- Scroll down to the next section, "Settings", and setup your master username and password (make sure to wright these down, for you will need them later to setup database      connections).
- Now click "create database" at the botton of the page to start the creation of your database.
- Once your database has been created, go to the database dashboard and then click on the name of the database (The name of the databased should be under the database identifier   column
- Underneath the first tab "Connectivity and Security" you should see your Database's endpoint that you need to writedown for later when we setup database connections.

Step 8: Download and Setup DBeaver
- Download DBeaver from https://dbeaver.io/download/
- Once you have downloaded and opened the dbeaver program, find the symbol that looks like an electrical plug with a green plus sign and click it
- Select PostgreSQL as you type of database instance and click next
- Put your database endpoint in the "Database field" and the database's username and password in the appropriate fields and click finish.
- The needed scripts for the project are given below

  Table creation scripts:
  
   > create table if not exists users (
   > user_id serial primary key,
   > email varchar(60) not null unique,
   > username varchar(12) not null unique,
   > password_hash integer not null,
   > display_name varchar(30),
   > is_admin boolean not null default false
   > );

   > create table if not exists messages (
   > message_id SERIAL primary key,
   > from_user_id integer not null references users(user_id) not null,
   > to_user_id integer not null references users(user_id) not null,
   > message_content varchar(140) not null,
   > message_date date not null default now()::date,
   > message_time time not null default now()::time
   > );

   > create table if not exists posts (
   > post_id SERIAL primary key,
   > author_id integer not null references users(user_id) not null,
   > post_content varchar(140) not null,
   > post_date date not null default now()::date,
   > post_time time not null default now()::time,
   > post_location varchar(40),
   > is_flagged boolean not null default false
   > );

   > create table if not exists comments (
   > comment_id SERIAL primary key,
   > post_id integer not null references posts(post_id) not null,
   > author_id integer not null references users(user_id) not null,
   > comment_content varchar(140) not null,
   > comment_date date not null default now()::date,
   > comment_time time not null default now()::time
   > );

   > create table if not exists likes (
   > post_id integer not null references posts(post_id) not null,
   > user_id integer not null references users(user_id) not null,
   > primary key (post_id, user_id)
   > );

   > create table if not exists friends (
   > user1_id integer not null references users(user_id) not null,
   > user2_id integer not null references users(user_id) not null,
   > primary key (user1_id, user2_id)
   > );
   
  Scripts to run to create starting data for the projcet:
  
  > INSERT INTO public.users
  > (email, username, password_hash, display_name, is_admin)
  > VALUES('email@example.com', 'username', password, 'displayName', false);
  


Step 9: Open the cloned project in IntelliJ from where you downloaded the project

Step 10: Click the import Maven Templat popup that will appear in the bottom right corner of the window or add the maven framework by right clicking on the project name in the file directory and click on "add framework support" and scroll through your given options until you see maven. Click the Maven option and then click the "Okay" button

Step 11.a: Setup Tomcat in Intellij Ultimate
 - https://www.youtube.com/watch?v=_Uq2mn56SDs&ab_channel=HamzaMalik
 
Step 11.b: Setup Tomcat in Intellij community
 - https://www.youtube.com/watch?v=Sr9DqV3hZhA&ab_channel=LearningFromExperience

Step 12: got to the resources folder underneath src/main/java/com/ex/config/ in the file called PersistenceConfig.java and provide the following and your database's credentials
> ds.setJdbcUrl("jdbc:postgresql://endpoint/");
> ds.setUsername("username");
> ds.setPassword("password");

## Usage
- Once this is done you should be able to start the Tomcat Run configuration and the program will start in you default browser!

## Licence

This project uses the following license: [MIT]
