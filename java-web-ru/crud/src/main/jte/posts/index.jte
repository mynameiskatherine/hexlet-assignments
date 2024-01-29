@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
        @for (var post : page.getPosts())
            <div>
                <h3><a href="${NamedRoutes.postsPath()}/${post.getId()}"> ${post.getName()}</a></h3>
                <p>${post.getBody()}</p>
            </div>
        @endfor
            <div>
                <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() - 1}"> back </a>
                <span> ${page.getCurrentPage()} </span>
                <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() + 1}"> forward </a>
            </div>
        </main>
    </div>
`
)
