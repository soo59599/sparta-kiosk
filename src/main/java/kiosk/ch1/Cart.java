package kiosk.ch1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> cartItems;

    //생성자
    public Cart (List<CartItem> cartItems){
        this.cartItems = cartItems;
    }

    //장바구니 목록 보기
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    //아이템 넣기
    public void addCartItems(CartItem cartItems) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getMenuItemId().equals(cartItems.getMenuItemId())) {
                cartItem.increaseQuantity(cartItems.getQuantity());
                return;
            }
        }
        this.cartItems.add(cartItems);
    }

    //아이템 빼기
    public void removeCartItems(CartItem cartItems) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getMenuItemId().equals(cartItems.getMenuItemId())) {
                cartItem.decreaseQuantity(cartItems.getQuantity());
                return;
            }
        }
        this.cartItems.remove(cartItems);
    }

    //카트에 담긴 총 금액 확인
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(int i = 0 ; i < cartItems.size() ; i++){
            totalPrice = cartItems.get(i).getTotalPrice().add(totalPrice);
        }
        return totalPrice;
    }
}
