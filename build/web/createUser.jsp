<%-- 
    Document   : createUser
    Created on : Jun 17, 2021, 3:41:33 PM
    Author     : ASUS
--%>

<%@page import="user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <style>

            .form-container{
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px ;
            }
            .role-group{
                display: flex;
                align-items: center;
            }
            .label-role {
                margin-right: 20px;
                margin-bottom: 0px;
            }
            .submit-wrapper {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

        </style>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError("", "", "", "", "", "");
            }
        %>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Library System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="login.html">Home<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
        <br><h1 align="center">Register Form </h1><br/>
        <section class="container-fluid">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="MainController" method="POST">
                        <!--User ID-->
                        <div class="form-group">
                            <label for="InputUserID">User ID</label>
                            <input type="text" name="userID"  class="form-control" id="InputUserID"  value="${param.userID}" aria-describedby="userIDHelp" placeholder="Enter User ID">
                            <p style="color:red;"><%=userError.getUserIDError()%></p>
                        </div>
                        <!--Full Name-->
                        <div class="form-group">
                            <label for="InputFullName">Full Name</label>
                            <input type="text" name="name"  class="form-control" id="InputFullName" value="${param.name}" placeholder="Enter Full Name" required="">
                            <p style="color:red;"><%=userError.getNameError()%></p>
                        </div>
                        <!--Address-->
                        <div class="form-group">
                            <label for="InputAddress">Address</label>
                            <input type="text" name="address"  class="form-control" value="${param.address}" id="InputAddress"aria-describedby="InputAddressHelp" placeholder="Enter Address" required="">
                            <p style="color:red;"><%=userError.getAddressError()%></p>
                        </div>
                        <!--Role ID-->
                        <div class="form-group role-group">
                            <label class="label-role" for="InputRoleID">Role ID</label><br/>
                            <select name="roleID" class="form-select" aria-label="Default select example" required>
                                <option selected value="">Choose your role</option>
                                <option value="AD">Administrator</option> 
                                <option value="US">User</option>
                            </select>
                        </div>
                        <!--Password-->
                        <div class="form-group">
                            <label for="InputPassword">Password</label>
                            <input type="password" name="password"  class="form-control" id="InputPassword" aria-describedby="InputPasswordHelp" placeholder="Enter Password " required="">
                            <%=userError.getPasswordError()%>
                        </div>
                        <div class="form-group">
                            <label for="InputConfirmPassword">Confirm Password</label>
                            <input type="password" name="confirm"  class="form-control" id="InputConfirmPassword" aria-describedby="InputConfirmPasswordHelp" placeholder="Confirm Password" required="">
                          
                            <p style="color:red;"><%=userError.getConfirmPasswordError()%></p>
                        </div>
                        <div class="submit-wrapper">
                            <button type="submit" value="Create" name="action" class="btn btn-primary">Create</button>
                            <div>
                                <span style="margin-right: 10px">Already a User?</span>
                                <a href="login.html"> Login Here </a>
                            </div>
                        </div>
                    </form>
                </section>
            </section>
        </section>
    </body>
</html>
