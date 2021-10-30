<%@page import="shopping.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="shopping.Cart"%>
<%@page import="user.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yasaee - Checkout</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <style>
        .table-wrapper{
            width: 100%;
            margin: 0 auto;
            box-shadow: 0 2px 12px 0 rgb(105 106 105);
            margin-top: 50px;
        }
        .submit-wapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        img{
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 120px;
        }
    </style>
    <body>
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
            <form action="MainController" align="right">
                <button type="submit" name="action" value="View Cart" class="btn btn-info">
                    <span class="glyphicon glyphicon-log-out"></span> View Cart
                </button>
                <button type="submit" name="action" value="Logout" class="btn btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Log out
                </button>
            </form>
        </nav>
        <div class="container">
            <%
                Cart cart = (Cart) session.getAttribute("CART");
            %>
            <h1 align="center">Check Out</h1>
            <div class="table-wrapper">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col"></th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 1;
                            double total = 0;
                            for (Product prod : cart.getCart().values()) {
                                total += prod.getQuantity() * prod.getPrice();
                        %>
                    <form action="MainController">
                        <tr>
                            <th scope="row"><%=count++%></th>
                            <td><img src="<%=prod.getImage_url()%>"></td>
                            <td><%=prod.getName()%></td>
                            <td><%=prod.getPrice()%></td>
                            <td><%= prod.getQuantity()%></td>
                            <td><%= prod.getQuantity()%></td>
                            <td><%= prod.getQuantity() * prod.getPrice()%></td>
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <div class="submit-wapper">
                <h1>Total: <%=total%></h1>
                <form action="MainController" align="right">
                    <button type="submit" name="action" value="Buy" class="btn btn-primary">
                        <span class="glyphicon glyphicon-log-out"></span> Buy
                    </button>
                    <input type="hidden" name="username" value="${sessionScope.CURRENT_USER.getUsername()}"/>
                    <input type="hidden" name="total" value="<%=total%>"/>
                </form>
            </div>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
