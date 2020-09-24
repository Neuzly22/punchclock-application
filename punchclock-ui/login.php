<!DOCTYPE html lang="en">
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <section class="welcome__section">
            <div class="welcome__message">
                <h1>Welcome to the PunchClock Application!</h1>
                <h2>Please enter your credentials</h2>
            </div>
        </section>
        <section class="form__section" style="margin-top: 50px;">
            <div>
                <form class="login-form" action="validate.php" method="post">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password"> 
                    <button type="submit">Login</button>
                </form>
            </div>
        </section>
        
    </body>
</html>
