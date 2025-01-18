package com.example.test;

import com.example.repository.WelcomeRepository;
import com.example.service.WelcomeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class WelcomAppTest {
    private static Logger log = LoggerFactory.getLogger(WelcomAppTest.class);

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private WelcomeService welcomeService;
    @Spy
    private WelcomeRepository welcomeRepository;

    private int port;
    @Autowired
    JdbcTemplate jdbcTemplate;
    String baseUrl = "http://localhost:";
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        this.setPort(8080);
        restTemplate = new RestTemplate();
        baseUrl = baseUrl + port;
        log.info(String.format("application started with base URL:%s and port:%s", baseUrl, port));
        log.info("Initializing DB..");
        jdbcTemplate.execute("insert into Message(message_id, welcome,libelle,type) values(1,'Welcome', 'of the world of programming!!! ','Geeks')");
        jdbcTemplate.execute("insert into Message(message_id, welcome,libelle,type) values(2,'WelcomeToEvent', 'You are selected to the contest!!! ','Geeks')");
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

    @AfterEach
    void emptyData(){
        var totalRecords = jdbcTemplate.queryForObject("Select count(*) from Message", Integer.class);
        log.info(String.format("Total Records in DB:%s", totalRecords));
        log.info("Deleting records from table.");
        jdbcTemplate.execute("DELETE FROM Message");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
