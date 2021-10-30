<%@page import="shopping.Product"%>
<%@page import="user.User"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yasaee - Cart</title>
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
                <button type="submit" name="action" value="Logout" class="btn btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Log out
                </button>
            </form>
        </nav>
        <div class="container" >
            <h1 class="text-danger">${requestScope.PRODUCT_QUANTITY_ERROR}</h1>
            <%
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null || cart.getCart().isEmpty()) {
            %>
            <h1>You haven't choose any products!</h1>
            <%
            } else {
            %>
            <h1 align="center">View Cart</h1>

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
                            <th scope="col">Update</th>
                            <th scope="col">Remove</th>
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
                            <td><%=String.format("%.0f", prod.getPrice())%></td>
                            <td>                        
                                <!--This is buying quantity not stock-->
                                <input type="number" name="newQuantity" value="<%= prod.getQuantity()%>" min="1"/>
                            </td>
                            <td><%=String.format("%.0f", prod.getQuantity() * prod.getPrice())%></td>
                            <td>
                                <button class="btn btn-sm btn-success" name="action" value="Modify"><i class="fa fa-pencil"></i></button>
                                <input type="hidden" name="id" value="<%= prod.getId()%>"/>
                            </td>
                            <td>
                                <button class="btn btn-sm btn-danger" name="action" value="Remove"><i class="fa fa-trash"></i></button>
                                <input type="hidden" name="id" value="<%= prod.getId()%>"/>

                            </td>
                        </tr>
                    </form>

                    <%
                        }
                    %>

                    </tbody>
                </table>
            </div>
            <div class="submit-wapper">
                <h1>Total: <%=String.format("%.0f", total)%> VND</h1>
                <form action="MainController" align="right">
                    <button type="submit" name="action" value="Check Out" class="btn btn-primary">
                        <input type="hidden" name="username" value="${sessionScope.CURRENT_USER.getUsername()}"
                               <input type="hidden" name="total" value="<%=total%>"
                               <span class="glyphicon glyphicon-log-out"></span> Check Out
                    </button>
                </form>
            </div>
            <%
                }
            %>
            <a href="shopping.jsp" >Add more</a>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        </div>
    </body>

</html>
