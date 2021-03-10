package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        String username = (String) session.getAttribute("name");
        ReloadStatus status = reloadStatusRepository.findByName(username);

        if (username == null) {
            status = new ReloadStatus();
            UUID uuid = UUID.randomUUID();
            username = uuid.toString();
            session.setAttribute("name", username);
            status.setName(username);
            status.setReloads(1);
            reloadStatusRepository.save(status);
        } else {
            status.setReloads(status.getReloads() + 1);
            reloadStatusRepository.save(status);
        }

        model.addAttribute("status", status);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());

        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        return "index";
    }
}
