
<%@page import="shopping.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yasaee - Shopping</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <style>
        .table-wrapper{
            width: 100%;
            margin: 0 auto;
            box-shadow: 0 2px 12px 0 rgb(105 106 105);
            margin-top: 50px;
            border-radius: 20px;
        }
        img{
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 120px;
        }
        .hello-user{
            padding: 12px;
        }
    </style>
    <body>
        <%
            User user = (User) session.getAttribute("CURRENT_USER");
            if (user != null) {
                if (user.getRoleId().equals("AD")) {
                    response.sendRedirect("admin.jsp");
                }
            }
            String searchBook = (String) request.getParameter("searchBook");
            if (searchBook == null) {
                searchBook = "";
            }
        %>
        <c:choose>
            <c:when test="${sessionScope.CURRENT_USER.roleId eq 'CU'}">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="#">Yasaee</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="ViewAllProductController">Home<span class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                    </div>
                    <form action="MainController" align="right">
                        <button type="submit" name="action" value="View Cart" class="btn btn-info">
                            <span class="glyphicon glyphicon-log-out"></span> View Cart
                        </button>
                        <button type="submit" name="action" value="Logout" class="btn btn-danger">
                            <span class="glyphicon glyphicon-log-out"></span> Log out
                        </button>
                    </form>
                </nav>
            </c:when>    
            <c:otherwise>
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="#">Yasaee</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="user.jsp">Home<span class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                    </div>
                    <div class="submit-wrapper">
                        <form action="login.jsp" align="right" >
                            <button class="btn btn-primary">
                                <span class="glyphicon glyphicon-log-out"></span> Login
                            </button>
                        </form>
                    </div>
                </nav>
            </c:otherwise>
        </c:choose>

        <h1 class="hello-user"> Welcome back, ${sessionScope.CURRENT_USER.name}</h1>
        <br/>
        <div class="container" >
            <div>
                <form  action="MainController">
                    <input  type="text" placeholder="Search"  name="searchBook" value="<%=searchBook%>">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="SearchBook">Search</button>
                </form>
            </div>
            <h1 style="color:green;">${requestScope.BUY_MESSAGE}</h1>
            <h1 style="color:green;">${requestScope.SHOPPING_MESSAGE}</h1>
            <%
                List<Product> list = (List<Product>) session.getAttribute("LIST_PRODUCT");
                if (list != null) {
                    if (!list.isEmpty()) {

            %>
            <div class="table-wrapper">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Category</th>
                            <th></th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity in Stock</th>
                            <th scope="col">Buy quantity</th>
                            <th scope="col">Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            int count = 1;
                            for (Product prod : list) {
                                float total = 0;
                        %>
                    <form action="MainController">
                        <tr>
                            <td scope="row"><%=count++%></td>
                            <td><%=prod.getCategoryId()%></td>
                            <td><img src="<%=prod.getImage_url()%>"></td>
                            <td><%=prod.getName()%></td>
                            <td><%=String.format("%.0f", prod.getPrice())%></td>
                            <td><%=prod.getQuantity()%></td>
                            <td>
                                <input  name="orderQuantity" type="number" value="1" min="1" max="<%=prod.getQuantity()%>" /></td>
                            </td>

                            <td> 
                                <button class="btn btn-sm btn-success" name="action" value="Add to Cart"><i class="fa fa-shopping-cart"></i> </button>
                                <input type="hidden" name="id" value="<%= prod.getId()%>"/>
                                <input type="hidden" name="name" value="<%= prod.getName()%>"/>
                                <input type="hidden" name="price" value="<%=prod.getPrice()%>"/>
                                <input type="hidden" name="image_url" value="<%=prod.getImage_url()%>"/>
                            </td>
                        </tr>
                    </form>
                    <%
                        }
                    %>   
                    </tbody>
                </table>
            </div>
        </div>

        <%
                }
            }
        %>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
