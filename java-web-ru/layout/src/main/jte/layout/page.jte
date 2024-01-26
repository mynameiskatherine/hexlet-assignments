@import gg.jte.Content
@param Content title
@param Content header = null
@param Content main


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    ${title}
</head>
<body>
<header>
    @if (header != null)
        ${header}
    @endif
    <ul>
        <li><a href="/">Main</a></li>
        <li><a href="/users">Users</a></li>
    </ul>
</header>
<main>
    ${main}
</main>
<footer>
    <p>buy-bye footer</p>
</footer>
</body>
</html>