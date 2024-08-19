package cl.franciscosolis.inventify.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
@ActiveProfiles("test")
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void indexTest() throws Exception{
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    JsonObject jsonResponse = JsonParser.parseString(mvcResult.getResponse().getContentAsString()).getAsJsonObject();
                    assertEquals("ok", jsonResponse.get("status").getAsString());
                    assertEquals("Te damos la bienvenida a Inventify", jsonResponse.get("message").getAsString());
                });
    }
}