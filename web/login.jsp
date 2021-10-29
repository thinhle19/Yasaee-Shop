<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Yasaee - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
    </head>
    <style>
        .form-container{
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px ;
        }
        .btn-google {
            color: #545454;
            background-color: #ffffff;
            box-shadow: 0 1px 2px 1px #ddd
        }

        .button-submit{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .text-center{
            padding: 5px;
        }

        /*TODO:the icon is not in middle*/
        #google-icon{
            padding: 5px;
        }
    </style>
    <body>
        <h1 align="center">Yasaee Login</h1><br/>
        <section class="container-fluid">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="MainController" method="POST">
                        <div class="form-group">
                            <label for="InputUserID">Username</label>
                            <input type="text" name="username" value="" class="form-control" id="InputUserID" aria-describedby="userIDHelp" placeholder="Enter username">
                        </div>
                        <div class="form-group">
                            <label for="InputPassword1">Password</label>
                            <input type="password" name="password" value="" class="form-control" id="InputPassword1" placeholder="Enter password">
                        </div>
                        <div class="text-center" style="color:red;">
                            ${requestScope.ERROR_LOGIN}
                        </div>
                        <div class="button-submit">
                            <button type="submit" value="Login" name="action" class="btn btn-primary">Submit</button>
                            <a class="btn btn-info" href="sign-up.jsp">Create new account</a>
                        </div>
                        <div class="text-center">
                            <a  href="shopping.jsp">Shopping now</a>
                        </div>
                        <!--Login Google-->
                        <div >
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/LibraryManagement/LoginGoogleController&response_type=code&client_id=982147591002-hukrhkpbe7hc8ts3s5gjerv3p4jfoohn.apps.googleusercontent.com&approval_prompt=force"
                               class="btn btn-lg btn-google btn-block text-uppercase btn-outline"/>
                            <img id="google-icon" src="https://img.icons8.com/color/16/000000/google-logo.png" 
                                 alt="Google Icon" height="30">Login With Google</a>
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
