<!DOCTYPE html>

<html lang="en">
<head>
    <link rel="stylesheet" href="/css/global.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div class="container login-div">
        <div class="card" style="width: 40%;">
            <div class="card-header" style="text-align: center;">
                Welcome to the Blog
            </div>
            <div class="card-dody">
                <div class="form-group">
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input id="email" class="form-control" placeholder="email"/>
                       </div>
                    </div>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input type="password" id="password" class="form-control" placeholder="password"/>
                        </div>
                    <div>
                    <div class="row" style="margin-top: 20px; margin-bottom: 20px;">
                        <div style="text-align: center;">
                            <button id="submit_btn" type="button" class="btn btn-primary">Log in</button>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px; margin-bottom: 20px;">
                     <div style="text-align: center;">
                        <a href="/register"><u>Create account</u></a>
                     </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script>
    $("#submit_btn").click(function() {
        const email = $("#email").val();
        const password = $("#password").val();

        if (!password || !email) {
            alert("Please fill all fields!")
        } else {
            const loginData = {
                email: email,
                password: password
            }

            fetch('http://localhost:8080/api/users/login', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            })
            .then(response => response.json())
            .then((data) => {
            console.log(data)
                if (data) {
                    console.log('yee')
                } else {
                    alert("Wrong email or password!")
                }
            });
        }

    });
</script>
</html>