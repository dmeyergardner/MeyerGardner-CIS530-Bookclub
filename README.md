# 📚 Bookclub Application

**Author:** Deb Meyer-Gardner  
**Created:** March 2025  
**Course:** CIS 623 – Software Engineering  
**Assignment:** 3.2 – Thymeleaf in Action  

## 📘 Overview

The Bookclub application is a Spring Boot web application that demonstrates the use of model-view-controller (MVC) architecture, Thymeleaf templating, data access through DAO patterns, and dynamic page routing. Users can browse a list of curated monthly book selections and view detailed information for each title.

---

## 🏗️ Technologies Used

- Java 17
- Spring Boot 3.x
- Thymeleaf (template engine)
- Bootstrap 5 (layout framework)
- Material Design Lite (MDL) for shadows and typography
- Font Awesome 6 (icons)
- Maven (project management)
- IntelliJ IDEA / VS Code (recommended IDEs)

---

## 📂 Project Structure

```
src/
 └── main/
     ├── java/
     │   └── com/
     │       └── bookclub/
     │           ├── model/         → Book.java
     │           ├── service/       → GenericDao.java
     │           │   ├── dao/       → BookDao.java
     │           │   └── impl/      → MemBookDao.java
     │           └── web/           → HomeController.java
     └── resources/
         ├── templates/
         │   ├── index.html
         │   ├── about.html
         │   ├── contact.html
         │   ├── monthly-books/
         │   │   └── view.html
         │   └── fragments/
         │       ├── header.html
         │       ├── footer.html
         │       └── scriptFooter.html
         └── static/
             └── css/
                 └── site.css
```

---

## ✨ Features

- Dynamic display of books using `th:each`, `th:text`, and `th:href`
- MVC structure separating logic, views, and models
- Data simulation using a memory-based DAO (no database required)
- Detail view page for each book with author list and metadata
- Bootstrap + MDL hybrid UI with shadows, spacing, and typography
- Font Awesome icons for accessibility and design clarity
- Mobile-responsive layout
- Dark mode toggle using custom CSS and JS

---

## 🚀 How to Run

1. Clone or download the repository.
2. Open in your preferred IDE.
3. Run the application using the Spring Boot main class.
4. Open your browser and visit:  
   ```
   http://localhost:8080
   ```
5. Click on any book card to view details.

---

## 🧪 Testing Checklist

- Home page displays 5 books
- "View" buttons route to `/1111`, `/2222`, etc. based on book ISBN
- Detail page shows correct data using Thymeleaf model binding
- Dark mode toggle works across all views
- Responsive layout adjusts on tablet and mobile screen sizes

---

## 📄 License

This application is intended for academic purposes only.

---

## 📚 References

- Baeldung. (2020). *Guide to Spring Expression Language (SpEL)*. https://www.baeldung.com/spring-expression-language  
- Spring Framework Documentation. https://docs.spring.io  
- Bootstrap 5 Docs. https://getbootstrap.com  
- MDL (Material Design Lite). https://getmdl.io  
- Font Awesome. https://fontawesome.com  
