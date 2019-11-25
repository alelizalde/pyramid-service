package pyramid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PyramidController {

    @GetMapping({"/status"})
    public String status() {
        return "online";
    }

    @GetMapping(value = {"/isPyramid"})
    public boolean isPyramid(HttpServletResponse response, HttpServletRequest request,
                             @RequestParam String word) {
        Map<Character, Integer> letterCounter = new HashMap<>();
        for (char letter : word.toCharArray()) {
            letterCounter.put(letter, letterCounter.getOrDefault(letter, 0) + 1);
        }

        int counter;
        for (counter = 1; counter <= letterCounter.size(); counter++) {
            if (!letterCounter.containsValue(counter)) {
                break;
            }

        }

        return counter - 1 == letterCounter.size();
    }
}
