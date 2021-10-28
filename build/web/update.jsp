 <%-- 
    Document   : update
    Created on : Jun 16, 2021, 7:08:12 PM
    Author     : ASUS
--%>

<%@page import="user.UserError"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <style>
        .form-container{
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px ;
        }
        .input-submit{
            display: flex;
            justify-content: space-between;
        }

        .role-group{
            display: flex;
            align-items: center;
        }
        .label-role {
            margin-right: 20px;
            margin-bottom: 0px;
        }

    </style>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError("", "", "", "", "", "");
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
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
            </div>
        </nav>
        <br><h1 align="center">Update Page</h1><br/>
        <section class="container-fluid">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="MainController" method="POST">
                        <!--User ID-->
                        <div class="form-group">
                            <label for="InputUserID">User ID</label>
                            <input type="text" name="userID"  class="form-control" value="<%=request.getParameter("userID")%>" id="InputUserID" aria-describedby="userIDHelp" readonly="" >
                        </div>
                        <!--Full Name-->
                        <div class="form-group">
                            <label for="InputFullName">Full Name</label>
                            <input type="text" name="name"  value="<%=request.getParameter("name")%>" class="form-control" id="InputFullName"  required="">
                            <p style="color:red;"><%=userError.getNameError()%></p>
                        </div>

                        <!--Role ID-->
                        <div class="form-group role-group">
                            <label class="label-role" for="InputRoleID">Role ID</label><br/>
                            <select name="roleID" class="form-select" aria-label="Default select example" required>
                                <option selected value="">Choose role </option>
                                <option value="AD">Administrator</option> 
                                <option value="US">User</option>
                            </select>
                        </div>

                        <!--Address-->
                        <div class="form-group">
                            <label for="InputAddress">Address</label>
                            <input type="text" name="address"  value="<%=request.getParameter("address")%>" class="form-control" id="InputAddress" aria-describedby="InputAddressHelp"  required="">
                           
                            <p style="color:red;"> <%=userError.getAddressError()%></p>

                        </div>

                        <!--Status-->
                        <div class="form-group role-group">
                            <label class="label-role" for="InputStatus">Status</label><br/>
                            <select name="status" class="form-select"  required>
                                <option selected value="">Choose status</option>
                                <option value="active">Active</option>
                                <option value="inactive">Inactive</option>
                            </select>
                        </div>
                        <div class="input-submit">
                            <input type="hidden" name="search" value="<%=request.getParameter("search")%>"/>
                            <input type="reset" value="Reset" class="btn btn-danger"/>
                            <input type="submit" value="Confirm Update" name="action" class="btn btn-success" />
                        </div>
                    </form>
                </section>
            </section>
        </section>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
