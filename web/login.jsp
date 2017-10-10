<head>
   <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/login.css">

        <!-- Website Font style -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
        <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="row">

            <div class="main">

                <h3>Please Log In, or <a href="#">Sign Up</a></h3>
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <a href="#" class="btn btn-lg btn-primary btn-block">Facebook</a>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <a href="#" class="btn btn-lg btn-info btn-block">Google</a>
                    </div>
                </div>
                <div class="login-or">
                    <hr class="hr-or">
                    <span class="span-or">or</span>
                </div>

                <form role="form" action="/LoginServlet" method="post">
                    <div class="form-group">
                        <label for="inputUsernameEmail">Username or email</label>
                        <input type="text" name = "username"class="form-control" id="inputUsernameEmail">
                    </div>
                    <div class="form-group">
                        <a class="pull-right" href="#">Forgot password?</a>
                        <label for="inputPassword">Password</label>
                        <input type="password" name="password" class="form-control" id="inputPassword">
                    </div>
                    <div class="checkbox pull-right">
                        <label>
                            <input type="checkbox">
                            Remember me </label>
                    </div>
                    <c:if test="${error != null}">
                        <span style="color: red">${error}</span>
                    </c:if>
                    <button type="submit" class="btn btn btn-primary">
                        Log In
                    </button>
                </form>

            </div>

        </div>
    </div>
</body>