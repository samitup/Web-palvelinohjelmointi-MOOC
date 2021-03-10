package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private Map<Item, Long> itemsInCart = new HashMap<>();
    private double sum = 0;

    public Map<Item, Long> getItems() {
        return itemsInCart;
    }

    public void addToCart(Item item) {
        if (itemsInCart.containsKey(item)) {
            Long itemCount = itemsInCart.get(item);
            itemsInCart.put(item, itemCount + 1);
            sum = sum + item.getPrice();

        }else {
            itemsInCart.put(item, 1L);
            sum = sum + item.getPrice();
        }

    }

    public void clearCart() {
        itemsInCart.clear();
    }

    public double getSum() {
        return sum;
    }

    public void clearSum() {
        sum = 0;
    }

}
