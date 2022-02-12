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
                Register
            </div>
            <div class="card-dody">
                <div class="form-group">
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input id="first_name" class="form-control" placeholder="First name"/>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input id="last_name" class="form-control" placeholder="Last name"/>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input id="email" class="form-control" placeholder="email"/>
                       </div>
                    </div>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-md-8 offset-2">
                            <input id="password" type="password" class="form-control" placeholder="password"/>
                        </div>
                    <div>
                    <div class="row" style="margin-top: 20px; margin-bottom: 20px;">
                        <div style="text-align: center;">
                            <button type="submit" id="submit_btn" type="button" class="btn btn-primary">Register</button>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px; margin-bottom: 20px;">
                     <div style="text-align: center;">
                        <a href="/"><u>Log in</u></a>
                     </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script>

    $("#submit_btn").click(function() {
        const first_name = $("#first_name").val();
        const last_name = $("#last_name").val();
        const email = $("#email").val();
        const password = $("#password").val();

        if (!password || !email || !first_name || !last_name) {
            alert("Please fill all fields!")
        } else {

            if (!validateEmail(email)) {
                alert('Please add a valid email address!')
            } else {

                const registerData = {
                    email: email,
                    password: password,
                    firstName: first_name,
                    lastName: last_name
                }

                fetch('http://localhost:8080/api/users/', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(registerData)
                })
                .then(response => response.json())
                .then((data) => {
                    if (data.status === 'true') {
                        alert("User created successfully!")
                         window.location.replace("/");
                    } else {
                        alert(data.message)
                    }
                })
            }
        }
    });

    /**
    * Validates if email input is a valid email address
    *@return Boolean
    */
    function validateEmail (email) {
        return String(email)
               .toLowerCase()
               .match(
                 /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
               );
    }
</script>
</html>