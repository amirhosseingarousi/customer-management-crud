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
                        <a href="https://www.javaguides.net" class="navbar-brand"> Customer Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${customer != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${customer == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${customer != null}">
                                    Edit Customer
                                </c:if>
                                <c:if test="${customer == null}">
                                    Add New Customer
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${customer != null}">
                            <input type="hidden" name="id" value="<c:out value='${customer.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Name</label> <input type="text" value="<c:out value='${customer.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Register Date</label> <input type="date" value="<c:out value='${customer.registerDate}' />" class="form-control" name="registerDate" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Economic Code</label> <input type="text" value="<c:out value='${customer.economicCode}' />" class="form-control" name="economicCode" required="required">
                        </fieldset>

                    <c:if test="${customer != null}">
                        <fieldset class="form-group">
                            <label>Customer number</label> <input type="text" value="<c:out value='${customer.customerNumber}' />" class="form-control" name="customerNumber" disabled>
                        </fieldset>
                    </c:if>
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>