package todoapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class TodoApplicationController {

    @Autowired
    private ItemRepository itemRepo;

    @GetMapping("/")
    public String haeKaikki(Model model) {
        model.addAttribute("items", itemRepo.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String haeYksi(Model model, @PathVariable Long id) {
        Item item = itemRepo.getOne(id);
        item.setChecked(item.getChecked() + 1);
        itemRepo.save(item);
        model.addAttribute("item", item);
        return "todo";
    }

    @PostMapping("/")
    public String luoUusi(@RequestParam String name) {
        itemRepo.save(new Item(name, 0));
        return "redirect:/";
    }
}
