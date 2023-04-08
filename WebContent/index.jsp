<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <html>
    </head>
        <title>User Management Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>
            <header>
                    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                        <div>
                            <a href="/jsp-servlet-jdbc-mysql-crud-tutorial-master" class="navbar-brand"> Customer Management App </a>
                        </div>

                        <ul class="navbar-nav">
                            <li><a href="<%=request.getContextPath()%>/private/list" class="nav-link">Private Customer</a></li>
                        </ul>

                        <ul class="navbar-nav">
                            <li><a href="<%=request.getContextPath()%>/legal/list" class="nav-link">Legal Customer</a></li>
                        </ul>
                    </nav>
            </header>
            <br>

    </body>
 </html>