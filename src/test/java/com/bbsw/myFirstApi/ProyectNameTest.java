package com.bbsw.myFirstApi;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = "test")
public abstract class ProyectNameTest {

    private static final String START_CONF_MSG = "******** START TEST CONFIGURATION DATA *********";
    private static final String END_CONF_MSG = "******** END TEST CONFIGURATION DATA ********";

    @Autowired
    protected TestUtils testUtils;

    public final Logger log = LoggerFactory.getLogger(getClass());

    @BeforeEach
    public void before(TestInfo testInfo) throws IOException {
        testUtils.initializeMockWebServer();
        testUtils.printInfo(testInfo.getDisplayName(), true);
    }

    @AfterEach
    public void after(TestInfo testInfo) throws IOException {
        testUtils.shutDownMockWebServer();
        testUtils.printInfo(testInfo.getDisplayName(), false);
    }

    @BeforeAll
    public void initialConfig() throws IOException {
        log.info(START_CONF_MSG);
        config();
        log.info(END_CONF_MSG);
    }

    /**
     * Method with initial configuration for all test
     */
    public abstract void config() throws IOException;

}
