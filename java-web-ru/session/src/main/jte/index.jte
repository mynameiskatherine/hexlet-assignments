@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    <h1>Hello!</h1>
    <div>
    @if(page.getName() != null)
        <p>Welcome, ${page.getName().toString()}</p>
        <p>To exit press the button below</p>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" value="Выйти">
        </form>
    @else
        <p>Welcome</p>
        <p>To enter press the button below</p>
        <form action="${NamedRoutes.buildSessionPath()}" method="get">
            <button>Войти</button>
        </form>
    @endif
    </div>
`
)