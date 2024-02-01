@import exercise.util.NamedRoutes

@template.layout.page(
    content = @`
        <h2>Welcome to Hexlet</h2>
    <p>Go to <a href="${NamedRoutes.postsPath()}">POSTS</a> page</p>
    `
)
