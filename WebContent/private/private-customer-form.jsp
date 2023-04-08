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
                            <label>First Name</label> <input type="text" value="<c:out value='${customer.firstName}' />" class="form-control" name="firstName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Last Name</label> <input type="text" value="<c:out value='${customer.lastName}' />" class="form-control" name="lastName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Father name</label> <input type="text" value="<c:out value='${customer.fatherName}' />" class="form-control" name="fatherName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Date of birth</label> <input type="date" value="<c:out value='${customer.dob}' />" class="form-control" name="dob" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>National code</label> <input type="text" value="<c:out value='${customer.nationalCode}' />" class="form-control" name="nationalCode" required="required">
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