# Big Data Web Information System

## Project Overview

The **Big Data Web Information System** is designed to handle employee and department data of a company. It integrates Hadoop HDFS for file storage and uses a Java Spring-Boot application to read and display employee information stored in a database (MySQL or PostgreSQL) and images stored in HDFS. The information is displayed on a web page where employee details such as name, manager, salary, commission, and department are shown.

The system also supports dynamic image retrieval from HDFS and combines this with a JOIN operation on the database to present data in an easily accessible format.

## Features

- **Hadoop Integration**: Employee images are stored in Hadoop HDFS, allowing the system to utilize big data infrastructure for file storage.
- **Spring Boot Web Application**: A simple Java Spring Boot application for displaying the employee information in a table.
- **MySQL/PostgreSQL Database**: The personnel and department data are stored in a relational database.
- **Employee Image Storage and Retrieval**: Images corresponding to employees are stored in HDFS and retrieved dynamically for display.
- **Data Display**: Employee information is displayed using a JOIN operation on the database to show the employee’s name, manager name, salary, commission, and department.
- **Web Page**: A single page displays the joined data from both employee and department tables.

## Requirements

- **Hadoop-HDFS**: A single-node Hadoop cluster must be installed on your local machine.
- **Java**: Java 8 or higher is required.
- **Spring Boot**: The application is built with Spring Boot.
- **Database**: MySQL or PostgreSQL for storing employee and department information.
- **HDFS**: Hadoop Distributed File System (HDFS) for storing employee images.
- **GitHub**: Clone or download the example CSV files and image files provided in the repository.

### Example Files
The following files will be used in the project:

#### emp.csv
```plaintext
empno,ename,job,mgr,hiredate,sal,comm,deptno,img
7369,SMITH,CLERK,7902,17-DEC-1980,800,,20,smith.jpg
7499,ALLEN,SALESMAN,7698,20-FEB-1981,1600,300,30,allen.jpg
7521,WARD,SALESMAN,7698,22-FEB-1981,1250,500,30,ward.jpg
7566,JONES,MANAGER,7839,2-APR-1981,2975,,20,jones.jpg
7654,MARTIN,SALESMAN,7698,28-SEP-1981,1250,1400,30,martin.jpg
7698,BLAKE,MANAGER,7839,1-MAY-1981,2850,,30,blake.jpg
7782,CLARK,MANAGER,7839,9-JUN-1981,2450,,10,clark.jpg
7788,SCOTT,ANALYST,7566,09-DEC-1982,3000,,20,scott.jpg
7839,KING,PRESIDENT,\000,17-NOV-1981,5000,,10,king.jpg
7844,TURNER,SALESMAN,7698,8-SEP-1981,1500,0,30,turner.jpg
7876,ADAMS,CLERK,7788,12-JAN-1983,1100,,20,adams.jpg
7900,JAMES,CLERK,7698,3-DEC-1981,950,,30,james.jpg
7902,FORD,ANALYST,7566,3-DEC-1981,3000,,20,ford.jpg
7934,MILLER,CLERK,7782,23-JAN-1982,1300,,10,miller.jpg
```

#### dept.csv
```plaintext
deptno,dname,loc
10,ACCOUNTING,NEW YORK
20,RESEARCH,DALLAS
30,SALES,CHICAGO
40,OPERATIONS,BOSTON
```

## Project Structure

```plaintext
BigDataWebSystem/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── bigdataweb/
│   │   │   │       ├── BigDataWebSystemApplication.java    # Main Spring Boot application class
│   │   │   │       ├── EmployeeController.java             # Controller to handle web requests
│   │   │   │       ├── EmployeeService.java                # Service to manage employee data
│   │   │   │       └── HDFSFileService.java                # Service to interact with HDFS for image storage
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── images/   # Employee images stored here
├── pom.xml   # Maven configuration file for dependencies
├── README.md # Project documentation
└── emp.csv   # Example employee data
└── dept.csv  # Example department data
```

## How to Run

### Prerequisites

- Install **Hadoop-HDFS** in a single-node configuration on your local machine.
- Ensure that **Java** and **Maven** are installed.
- Set up a **MySQL** or **PostgreSQL** database to store the employee and department data.

### Steps to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ozmen54/SWE307-2024.git
   cd SWE307-2024
   ```

2. **Set Up the Database**:
   - Import the `emp.csv` and `dept.csv` into your MySQL/PostgreSQL database.
   - Make sure the schema matches the format described in the CSV files.

3. **Install Dependencies**:
   ```bash
   mvn clean install
   ```

4. **Start Hadoop HDFS**:
   - Start your HDFS cluster by running:
     ```bash
     start-dfs.sh
     ```

5. **Start the Spring Boot Application**:
   ```bash
   mvn spring-boot:run
   ```

6. **Access the Application**:
   - The application will be available at `http://localhost:8080`. Open this URL in your web browser to see the employee data displayed on a single page.

### Expected Web Page

- A table displaying employee information (name, manager, salary, commission, and department) by performing a JOIN operation between the `emp` and `dept` tables.
- Employee images will be dynamically retrieved from HDFS and displayed next to the employee’s details.

## Conclusion

This project demonstrates how to create a big data-based web information system that integrates Hadoop HDFS for file storage and uses Spring Boot to provide a web interface for data visualization. By leveraging both relational databases and Hadoop, the system efficiently manages and displays large sets of employee and department information.
