@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Template task - list of users page</title>
</head>
<body>
<h1>${page.getTitle()}</h1>
@if(page.getUserList().isEmpty())
    <p>No Users added</p>
@else
    @for(var user : page.getUserList())
        <table>
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td><a href = "/users/${user.getId()}">link</a></td>
            </tr>
        </table>
    @endfor
@endif
</body>
</html>
