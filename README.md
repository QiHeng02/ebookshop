# EbookShop

*IM2073 Project: A comprehensive e-commerce webapp for digital book sales*

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=java&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=flat-square&logo=apache-tomcat&logoColor=black)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview

EbookShop is a full-stack web application developed as part of the IM2073 course project. It provides a complete e-commerce platform for buying and selling digital books, featuring user authentication, book catalog management, shopping cart functionality, and order processing.

### Why EbookShop?

This project demonstrates the implementation of a complete web application using traditional Java web technologies. Key learning objectives include:

- Understanding MVC architecture in web development
- Database design and SQL integration
- Server-side programming with Java Servlets
- Session management and user authentication
- E-commerce workflow implementation

## Features

- 📚 **Book Catalog Management:** Browse, search, and filter books by category, author, or price
- 👥 **User Authentication:** Secure user registration and login system
- 🛒 **Shopping Cart:** Add books to cart, modify quantities, and manage selections
- 💳 **Order Processing:** Complete checkout process with order history
- 🔐 **Admin Panel:** Administrative functions for managing books and users
- 📱 **Responsive Design:** Mobile-friendly interface for all devices

## Getting Started

### Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK):** Version 8 or higher
- **Apache Tomcat:** Version 8.5 or higher
- **MySQL Database:** Version 5.7 or higher
- **IDE:** Eclipse, IntelliJ IDEA, or similar Java IDE

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/QiHeng02/ebookshop.git
   cd ebookshop
   ```

2. **Database Setup**
   - Create a new MySQL database named `ebookshop`
   - Import the database schema from `database/schema.sql`
   - Update database connection settings in your configuration files

3. **Configure Tomcat**
   - Copy the project to your Tomcat webapps directory
   - Or deploy the WAR file to Tomcat

4. **Update Configuration**
   - Modify database connection parameters in your configuration files
   - Ensure all required libraries are in the classpath

## Usage

1. **Start the Application**
   - Start your MySQL database server
   - Start Apache Tomcat server
   - Navigate to `http://localhost:8080/ebookshop`

2. **User Functions**
   - **Register/Login:** Create an account or login with existing credentials
   - **Browse Books:** Explore the book catalog and search for specific titles
   - **Shopping:** Add books to cart and proceed with checkout
   - **Profile:** Manage account details and view order history

3. **Admin Functions** (if implemented)
   - **Book Management:** Add, edit, or remove books from catalog
   - **User Management:** View and manage user accounts
   - **Order Management:** Process and track customer orders

## Project Structure

```
ebookshop/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ebookshop/
│   │   │       ├── controller/     # Servlet controllers
│   │   │       ├── model/          # Data models
│   │   │       ├── dao/            # Database access objects
│   │   │       └── utils/          # Utility classes
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       ├── css/                # Stylesheets
│   │       ├── js/                 # JavaScript files
│   │       ├── images/             # Static images
│   │       └── *.jsp               # JSP pages
├── database/
│   └── schema.sql                  # Database schema
└── README.md
```

## Technologies Used

- **Backend:** Java Servlets, JSP (JavaServer Pages)
- **Frontend:** HTML5, CSS3, JavaScript
- **Database:** MySQL with JDBC
- **Server:** Apache Tomcat
- **Architecture:** MVC (Model-View-Controller)

## Contributing

This is an academic project, but if you'd like to contribute improvements:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is created for educational purposes as part of the IM2073 course.

## Contact

**QiHeng02** - [GitHub Profile](https://github.com/QiHeng02)

Project Link: [https://github.com/QiHeng02/ebookshop](https://github.com/QiHeng02/ebookshop)

Portfolio Site: https://qiheng-portfolio.netlify.app/

---

**Course:** IM2073  
**Project Type:** Web Application Development  
**Academic Year:** [Add your academic year]
