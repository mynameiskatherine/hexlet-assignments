@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    page=page,
content = @`
    <h2>Welcome to Posts page</h2>
    <p><a href="${NamedRoutes.buildPostPath()}">Create new Post</a></p>
    <table>
        @for (var post : page.getPosts())
        <tr>
            <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></td>
            <td>${post.getBody()}</td>
        </tr>
        @endfor
    </table>
`
)
