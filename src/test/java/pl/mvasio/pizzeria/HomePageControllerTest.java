package pl.mvasio.pizzeria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mvasio.pizzeria.controller.HomePageController;

@RunWith(SpringRunner.class)
@WebMvcTest(HomePageController.class)
public class HomePageControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testHomePage() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home.html"));
    }
}
