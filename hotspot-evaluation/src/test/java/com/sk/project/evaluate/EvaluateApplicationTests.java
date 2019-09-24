package com.sk.project.evaluate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.project.evaluate.domain.review.model.dto.ReviewDto;
import com.sk.project.evaluate.domain.review.model.entity.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EvaluateApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    ObjectMapper mapper = new ObjectMapper();

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void categoryApiTest() throws Exception {
        mockMvc.perform(get("/category"))
                .andExpect(status().isOk());
    }

    @Test
    public void reviewApitest() throws Exception {
        mockMvc.perform(get("/review/store/{storeId}", 99L))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void scoreApitesst() throws Exception {
        mockMvc.perform(get("/score/store/{storeId}", 299L))
                .andExpect(status().isOk())
                .andDo(print());
    }

}