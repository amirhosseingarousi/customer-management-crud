<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Customer Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="/jsp-servlet-jdbc-mysql-crud-tutorial-master" class="navbar-brand"> Customer Management App </a>
                    </div>


                </nav>
            </header>
            <br>

            <div class="row">

                <div class="container">
                    <h3 class="text-center">List of Customers</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/legal/new" class="btn btn-success">Add New Customer</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Register Date</th>
                                <th>Economic Code</th>
                                <th>Customer Number</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>

                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="customer" items="${listCustomer}">

                                <tr>
                                    <td>
                                        <c:out value="${customer.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.registerDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.economicCode}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.customerNumber}" />
                                    </td>

                                    <td><a href="edit?id=<c:out value='${customer.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${customer.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>