/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author woohoo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Before
    public void clearDatabase() throws Exception {
        this.aircraftRepository.deleteAll();
    }

    @Test
    @Transactional
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk());
    }
        
    @Test
    @Transactional
    public void responseContainsParametersAircraft() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                       .andExpect(model().attributeExists("aircrafts"));
    }
    
    @Test
    @Transactional
     public void responseContainsParametersAirports() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                       .andExpect(model().attributeExists("airports"));
    }
     
     @Test
     public void testAddingAircraftReturnsRedirect() throws Exception {
         mockMvc.perform(post("/aircrafts").param("name", "HA-LOL"))
                 .andExpect(redirectedUrl("/aircrafts")).andExpect(status().is3xxRedirection());
     }
    @Test
    public void testAddedAircraftIsSaved() throws Exception {
        assertTrue(this.aircraftRepository.findAll().stream().filter(aircraft -> aircraft.getName().contains("HA-LOL")).count() == 0);

        MvcResult res = mockMvc.perform(post("/aircrafts").param("name", "HA-LOL"))
                .andReturn();

        assertTrue(this.aircraftRepository.findAll().stream().filter(aircraft -> aircraft.getName().contains("HA-LOL")).count() == 1);
    }

    @Test
    @Transactional
    public void testAddedAircraftIsIncludedInAllAircraftsPage() throws Exception {
        mockMvc.perform(post("/aircrafts").param("name", "XP-55"))
                        .andExpect(redirectedUrl("/aircrafts")).andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/aircrafts")).andExpect(model().attributeExists("aircrafts"));
        MvcResult res = mockMvc.perform(get("/aircrafts")).andReturn();
        List<Aircraft> aircrafts = (List) res.getModelAndView().getModel().get("aircrafts");
        assertTrue(aircrafts.stream().filter(aircraft -> aircraft.getName().equals("XP-55")).count() == 1);
    }
}
