@import exercise.util.NamedRoutes;
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <p><a href="${NamedRoutes.postsPath()}">Все посты</a></p>
            <div>
                <h3>${page.getPost().getName()}</h3>
                <p>${page.getPost().getBody()}</p>
            </div>
        </main>
    </div>
`
)
