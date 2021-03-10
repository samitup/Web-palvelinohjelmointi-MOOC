package euroshopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCart shoppingCart;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/cart")
    public String getItemsInCart(Model model) {
        

        model.addAttribute("items", shoppingCart.getItems());
        model.addAttribute("sum", shoppingCart.getSum());

        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String addItemToCart(@PathVariable Long id) {
        shoppingCart.addToCart(itemRepository.getOne(id));
        return "redirect:/cart";
    }
}
