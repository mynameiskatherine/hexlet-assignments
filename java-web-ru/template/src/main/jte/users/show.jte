@import exercise.dto.users.UserPage
@param UserPage page

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Template task - user page</title>
</head>
<body>
<table>
    <tr>
        <td>${page.getUser().getId()}</td>
        <td>${page.getUser().getFirstName()}</td>
        <td>${page.getUser().getLastName()}</td>
        <td>${page.getUser().getEmail()}</td>
    </tr>
</table>
</body>
</html>

