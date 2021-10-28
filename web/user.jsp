
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="shopping.Books"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>



    <style>
        .table-wrapper{
            width: 100%;
            margin: 0 auto;
            box-shadow: 0 2px 12px 0 rgb(105 106 105);
            margin-top: 50px;
        }
        img{
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 120px;
        }

    </style>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                if (user.getRoleID().equals("AD")) {
                    response.sendRedirect("login.html");
                }
            }
            String searchBook = (String) request.getParameter("searchBook");
            if (searchBook == null) {
                searchBook = "";
            }
        %>
        <c:choose>
            <c:when test="${sessionScope.LOGIN_USER.roleID eq 'US'}">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="#">Library System</a>
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
                    <form action="MainController" align="right">
                        <button type="submit" name="action" value="Logout" class="btn btn-danger">
                            <span class="glyphicon glyphicon-log-out"></span> Log out
                        </button>
                    </form>
                </nav>
            </c:when>    
            <c:otherwise>
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="#">Library System</a>
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
                        <form action="login.html" align="right" >
                            <button class="btn btn-primary">
                                <span class="glyphicon glyphicon-log-out"></span> Login
                            </button>
                        </form>
                    </div>
                </nav>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.LOGIN_USER.roleID eq 'US'}">
                <h1> Hello User :  ${sessionScope.LOGIN_USER.name}</h1>
                <br />
            </c:when>    
            <c:otherwise>
                <h1>Hello User :Anonymous </h1>
                <br />
            </c:otherwise>
        </c:choose>
        <div class="container" >
            <div>
                <form  action="MainController">
                    <input  type="text" placeholder="Search"  name="searchBook" value="<%=searchBook%>">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="SearchBook">Search</button>
                </form>
            </div>
<!--            <form action="MainController">
                <input type="submit" name="action" value="Show Book List">
            </form>-->
            <h1 style="color:green;">${requestScope.BUY_MESSAGE}</h1>
            <h1 style="color:green;">${requestScope.SHOPPING_MESSAGE}</h1>
            <%
                List<Books> list = (List<Books>) session.getAttribute("BOOK_LIST");
                if (list != null) {
                    if (!list.isEmpty()) {

            %>
            <div class="table-wrapper">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Book ID</th>
                            <th scope="col"></th>
                            <th scope="col">Book Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Quantity in warehouse </th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">View Cart</th>
                            <th scope="col">Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                    int count = 1;
                            for (Books book : list) {
                                float total = 0;
                        %>
                    <form action="MainController">
                        <tr>
                            <td scope="row"><%=count++%></td>
                            <td><%=book.getBookId()%></td>
                            <td><img src="<%=book.getImage()%>"></td>
                            <td><%=book.getBookName()%></td>
                            <td><%=book.getDescription()%></td>
                            <td><%=book.getQuantity()%></td>
                            <td><%=book.getPrice()%></td>
                            <td>
                                <input  name="orderQuantity" type="number" value="1" min="1" max="<%=book.getQuantity()%>" /></td>

                            </td>

                            <td> 
                                <button class="btn btn-sm btn-danger" name="action" value="View Cart"><i class="fa fa-eye"></i></button>

                            </td>
                            <td> 
                                <button class="btn btn-sm btn-success" name="action" value="Add to Cart"><i class="fa fa-shopping-cart"></i> </button>
                                <!--<input type="submit" name="action" value="Add to Cart">-->
                                <input type="hidden" name="bookId" value="<%= book.getBookId()%>"/>
                                <input type="hidden" name="bookName" value="<%= book.getBookName()%>"/>
                                <input type="hidden" name="quantity" value="<%= book.getQuantity()%>"/>
                                <input type="hidden" name="price" value="<%= book.getPrice()%>"/>
                                <input type="hidden" name="description" value="<%= book.getDescription()%>"/>
                                <input type="hidden" name="image" value="<%= book.getImage()%>"/>
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
        <%
            String message = (String) request.getAttribute("SHOPPING_MESSAGE");
            if (message == null) {
                message = "";
            }
        %>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
