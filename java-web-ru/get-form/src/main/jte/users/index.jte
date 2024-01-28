@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
    </form>
    @if(page.getUsers().isEmpty())
        @if(page.getTerm() != null)
            <p>No users found</p>
        @else
            <p>No Users added</p>
        @endif
    @else
        @for(var user : page.getUsers())
            <table>
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getFirstName()} ${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            </table>
        @endfor
    @endif
`
)
