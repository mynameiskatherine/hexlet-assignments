@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
title = @`
    <title>Users start page</title>
`,
header = @`
    <h1>Users start page</h1>
`,
main = @`
    @if(page.getUsers().isEmpty())
        <p>No Users added</p>
    @else
        @for(var user : page.getUsers())
            <table>
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getFirstName()} ${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                    <td><a href = "/users/${user.getId()}">link</a></td>
                </tr>
            </table>
        @endfor
    @endif
`
)
