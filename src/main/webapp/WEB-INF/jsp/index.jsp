<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel="stylesheet" href="static/css/bootstrap.css">
        <title>TwitMini</title>
    </head>
    <body>
        <h1>Welcome to Mini Twitter</h1>
        <div class="row">
            <div class="span8">
                <h1>Sign in</h1>

            </div>
            <div class="span8">
                <h1>Sign up</h1>
                <p>Content for column #2.</p>
                <form:form method="POST" commandName="user" action="/user/new">
                    <table>
                        <tr>
                            <td>Name</td>
                            <td><form:input path="name" /></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><form:input path="email"/></td>
                        </tr>
                        <tr>
                            <td>Username</td>
                            <td><form:input path="username"/></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><form:password path="password"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Sign me up!" />
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
         </div>
    </body>

</html>