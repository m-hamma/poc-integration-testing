package com.example.test;

import com.example.repository.WelcomeRepository;
import com.example.service.WelcomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@Sql(scripts = "classpath:/resources/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class WelcomAppTest {
    private static Logger log = LoggerFactory.getLogger(WelcomAppTest.class);

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private WelcomeService welcomeService;
    @Spy
    private WelcomeRepository welcomeRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    private int port;
    @Autowired
    JdbcTemplate jdbcTemplate;
    String baseUrl = "http://localhost:";
    RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        this.setPort(8080);
        restTemplate = new RestTemplate();
        baseUrl = baseUrl + port;
        log.info(String.format("application started with base URL:%s and port:%s", baseUrl, port));
        log.info("Initializing DB..");
        executeSqlFile("data.sql");
        var totalRecords = jdbcTemplate.queryForObject("Select count(*) from Message", Integer.class);
        log.info(String.format("Total Records in DB:%s", totalRecords));
        mockMvc= MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Test
    void testWelcome () throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/").param("nom","Geeks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("Welcome","Welcome, Geeks of the world of programming!!! "))
                .andExpect(MockMvcResultMatchers.view().name("welcome-page"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void testWelcomeEvent () throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/event").param("participant","participant"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("WelcomeToEvent","WelcomeToEvent, participant You are selected to the contest!!! "))
                .andExpect(MockMvcResultMatchers.view().name("event-page"))
                .andDo(MockMvcResultHandlers.print());
    }

    public void executeSqlFile(String filename) {
        try  {
            Resource resource = resourceLoader.getResource("classpath:" + filename);
            //lire le contenu du fichier
            String sql = resource.getContentAsString(StandardCharsets.UTF_8);
            jdbcTemplate.execute(sql.toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL file", e);
        }
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
