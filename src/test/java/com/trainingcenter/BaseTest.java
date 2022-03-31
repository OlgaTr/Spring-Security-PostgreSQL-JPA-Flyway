package com.trainingcenter;

import com.trainingcenter.services.ContributorService;
import com.trainingcenter.services.GroupService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = BaseTest.class)
public class BaseTest implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Autowired
    private ContributorService contributorService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:alpine");

    public ContributorService contributorService() {
        return contributorService;
    }

    public GroupService groupService() {
        return groupService;
    }

    public MockMvc mockMvc() {
        return mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    public static Map<String, String> getProperties() {
        return Map.of(
                "spring.datasource.url", container.getJdbcUrl(),
                "spring.datasource.username", container.getUsername(),
                "spring.datasource.password", container.getPassword()
        );
    }

    @BeforeAll
    public static void setup() {
        container.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        TestPropertyValues.of(getProperties())
                .applyTo(context.getEnvironment());
    }
}
