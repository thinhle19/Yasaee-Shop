<%-- 
    Document   : admin
    Created on : Jun 15, 2021, 8:04:00 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light"  >
            <a class="navbar-brand" href="#">Library System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="admin.jsp">Home<span class="sr-only">(current)</span></a>
                    </li>

                </ul>
                <form class="form-inline my-2 my-lg-0" action="MainController">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="search" value="<%=search%>">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="Search">Search</button>
                </form>
            </div>
        </nav>
        <br><h2 class="font-italic">Hello Administrator: <%=loginUser.getName()%></h2>
        <form action="MainController">
            <button type="submit" name="action" value="Logout" class="btn btn-danger">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </button>
        </form>
        <br>
        <%
            String error_message = (String) request.getAttribute("ERROR_MESSAGE");
            if (error_message != null) {
        %>
        <h1 align="center"> <%=error_message%></h1>
        <%
            }
        %>
        <%
            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">User ID</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Role ID</th>
                    <th scope="col">Address</th>
                    <th scope="col">Password</th>
                    <th scope="col">Create Date</th>
                    <th scope="col">Status</th>
                    <th scope="col">Update</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO user : list) {
                %> 
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= user.getUserID()%></td>
                    <td><%= user.getName()%></td>
                    <td><%= user.getRoleID()%></td>
                    <td>
                        <%= user.getAddress()%>
                    </td>
                    <td>
                        <%= user.getPassword()%>
                    </td>
                    <td>
                        <%= user.getDate()%>
                    </td>
                    <td>
                        <%= user.getStatus()%>
                    </td>
                    <!--                    <td>
                                            <a href="MainController?userID=<%= user.getUserID()%>&action=Delete&search=<%=search%>">Delete</a>
                                        </td>-->
                    <td>
                        <!--<input type="submit" name="action" value="Update"/>-->
                        <button class="btn btn-sm btn-primary" name="action" value="Update"><i class="fa fa-pencil"></i></button>
                        <input type="hidden" name="userID" value="<%= user.getUserID()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                        <input type="hidden" name="name" value="<%= user.getName()%>"/>
                        <input type="hidden" name="roleID" value="<%= user.getRoleID()%>"/>
                        <input type="hidden" name="address" value="<%= user.getAddress()%>"/>
                        <input type="hidden" name="createDate" value="<%= user.getDate()%>"/>
                    </td>
                    <td>
                        <button class="btn btn-sm btn-danger" name="action" value="Delete"><i class="fa fa-trash"></i></button>
                        <input type="hidden" name="userID" value="<%= user.getUserID()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>

            </form>

            <%
                }
            %>   

        </tbody>
    </table>
    <%
            }
        }
    %>
    
</body>
</html>
