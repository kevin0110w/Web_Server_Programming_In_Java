package airports;

import airports.*;
import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

    @Before
    public void clearDatabase() {
        this.airportRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testList() {
        
        Airport a = new Airport();
        Airport b = new Airport();

        this.airportRepository.save(a);
        this.airportRepository.save(b);

        List<Airport> airports = this.airportService.list();
        assertTrue(airports.size() == 2);
        assertTrue(airportRepository.findAll().contains(b) && airportRepository.findAll().contains(a));
    }
    
    @Test
    @Transactional
    public void testCreateNewAirport() {

        Airport a = new Airport();
        a.setIdentifier("1");
        a.setName("A");

        assertTrue(!airportRepository.findAll().contains(a));

        this.airportService.create("1", "A");
        assertTrue(airportRepository.findAll().contains(a));
    }

    @Test
    @Transactional
    public void testCreateExistingAirport() {
        Airport a = new Airport();
        a.setIdentifier("1");
        a.setName("A");

        assertTrue(!airportRepository.findAll().contains(a));
        assertTrue(airportRepository.findAll().size() == 0);
        this.airportService.create("1", "A");
        
        assertTrue(airportRepository.findAll().contains(a));
        assertTrue(airportRepository.findAll().size() == 1);
        
        this.airportService.create("1", "A");
        assertTrue(airportRepository.findAll().contains(a));
        assertFalse(airportRepository.findAll().size() == 2);
    }
}
