package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {

    @Autowired
    GifRepository gifRepo;

    @GetMapping("/gifs")
    public String direct() {

        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String directToId(Model model, @PathVariable Long id) {
        model.addAttribute("count", gifRepo.count());
        Long next = null;
        Long previous = null;

        if ((id + 1) <= gifRepo.count()) {
            next = gifRepo.getOne(id + 1).getId();
            System.out.println("NEXT id= " + next);
        }
        if ((id - 1) >= 0) {
            previous = gifRepo.getOne(id - 1).getId();
            System.out.println("PREVIOUS id= " + previous);
        }
        System.out.println("CURRENT id= " + id);

        model.addAttribute("next", next);
        model.addAttribute("previous", previous);
        model.addAttribute("current", id);

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] getGif(@PathVariable Long id) {
        return gifRepo.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("image/gif")) {
            GifObject obj = new GifObject();
            obj.setContent(file.getBytes());
            System.out.println(file.getContentType());
            gifRepo.save(obj);
        }
        return "redirect:/gifs";
    }
}
