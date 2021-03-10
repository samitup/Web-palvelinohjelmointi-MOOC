package hellorequestparams;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.stream.Collectors;

@Controller
public class HelloRequestParamsController {

    @GetMapping("/hello")
    @ResponseBody
    public String hellurei(@RequestParam String param) {
        return "Hello " + param;
    }

    @GetMapping("/params")
    @ResponseBody
    public String parametrit(@RequestParam Map<String, String> param) {
        String mapAsString;
        mapAsString = param.keySet().stream()
                .map(key -> key + "=" + param.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
