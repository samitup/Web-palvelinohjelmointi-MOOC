package hellosession;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {

    @RequestMapping("*")
    @ResponseBody
    public String sayHello() {
        return "Hello there!";
    }

    @GetMapping("/")
    @ResponseBody
    public String sayHelloAgain(HttpSession sessio) {
        int visit = 0;
        if (sessio.getAttribute("seen-before") != null) {
            return "Hello again!";

        }
        sessio.setAttribute("seen-before", "yes");
        return sayHello();

    }
}
