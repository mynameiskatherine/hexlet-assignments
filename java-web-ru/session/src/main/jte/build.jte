@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
content = @`
    <div>
        @if (page.getError() != null)
            <ul>
                <li>${page.getError()}</li>
            </ul>
        @endif
        <div>
            <form action="${NamedRoutes.loginPath()}" method="post">
                <input type="text" placeholder="Login" name="login" value="${page.getName()}"/>
                <input type="password" placeholder="Password" name="password" />
                <input type="Submit" value="Log in"/>
            </form>
        </div>
    </div>
`
)