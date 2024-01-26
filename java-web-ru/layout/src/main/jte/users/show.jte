@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
title = @`
    <title>User Id ${page.getUser().getId()}</title>
`,
header = @`
    <p>User page header</p>
`,
main = @`
    <table>
        <tr>
            <td>User ID: ${page.getUser().getId()}</td>
            <td>User full name: ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</td>
            <td>User email: ${page.getUser().getEmail()}</td>
        </tr>
    </table>
`
)
