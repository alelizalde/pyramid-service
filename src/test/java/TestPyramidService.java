import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPyramidService {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void TestPyramidWordSuccess() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String word = "banana";

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(word),
                HttpMethod.GET, entity, String.class);

        assert(Boolean.parseBoolean(response.getBody()));
    }

    @Test
    public void TestPyramidWordFail() {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String word = "notPyramid";

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(word),
                HttpMethod.GET, entity, String.class);

        assert(!Boolean.parseBoolean(response.getBody()));
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/isPyramid?word=" + uri;
    }
}
