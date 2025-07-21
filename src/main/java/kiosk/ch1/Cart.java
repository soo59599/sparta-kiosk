package kiosk.ch1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private final List<CartItem> cartItems;

    //생성자
    public Cart(){
        this.cartItems = new ArrayList<>();
    }

    //장바구니 목록 보기
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    //카트안에 아이템 있는지 찾기
    private Optional<CartItem> findCartItem(String menuItemId) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getMenuItemId().equals(menuItemId)) {
                return Optional.of(cartItem);
            }
        }
        return Optional.empty();
    }


    //아이템 넣기
    public void addCartItems(CartItem cartItemToAdd) {
        Optional<CartItem> existingItem = findCartItem(cartItemToAdd.getMenuItemId());
        if(existingItem.isPresent()) {
            existingItem.get().increaseQuantity(cartItemToAdd.getQuantity());
        }else{
            this.cartItems.add(cartItemToAdd);
        }
    }

    //아이템 빼기
    public void decreaseItemQuantity(CartItem itemToRemove) {
        Optional<CartItem> existingItem = findCartItem(itemToRemove.getMenuItemId());

        if(existingItem.isEmpty()) {
            System.out.println("해당 아이템이 카트에 없습니다.");
            return;
        }

        CartItem item = existingItem.get();
        item.decreaseQuantity(item.getQuantity());

        if(item.getQuantity() <= 0) {
            this.cartItems.remove(item);
        }
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
