package exercise;

import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    private static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", db::getJdbcUrl);
        registry.add("spring.datasource.username", db::getUsername);
        registry.add("spring.datasource.password", db::getPassword);
    }

    // END
    @Test
    void testShowAll() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/people")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("John", "Smith");
    }

    @Test
    void testShow() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/people/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(patch("/people/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"BlaBlaBlaLastName\", \"lastName\": \"BlaBlaFirstName\"}")
                )
                .andReturn()
                .getResponse();
        MockHttpServletResponse response = mockMvc.perform(get("/people/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("BlaBlaBlaLastName", "BlaBlaFirstName");
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/people/2")
                .contentType(MediaType.APPLICATION_JSON));
        MockHttpServletResponse response = mockMvc.perform(get("/people/2")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();
        assertThat(response.getContentAsString()).isEmpty();
    }
}
