package kiosk.ch2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {

    private List<CartItem> cartItems;

    //생성자
    public Cart(){
        this.cartItems.stream().collect(Collectors.toList());
    }

    //장바구니 목록 보기
    public List<CartItem> getCartItems() {
        return cartItems.stream().collect(Collectors.toList());
    }

    //카트에서 특정 이름을 가진 아이템 제거
    public void removeCartItem(CartItem cartItem) {
        cartItems = cartItems.stream()
                .filter(item -> !item.getMenuItemName().equals(cartItem.getMenuItemName()))
                .collect(Collectors.toList());
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
    public void increaseItemQuantity(CartItem cartItemToAdd) {
        Optional<CartItem> existingItem = findCartItem(cartItemToAdd.getMenuItemId());
        if(existingItem.isPresent()) {
            existingItem.get().increaseQuantity(cartItemToAdd.getQuantity());
        }else{
            this.cartItems.add(cartItemToAdd);
        }
    }

    //아이템 빼기
    public void decreaseItemQuantity(CartItem cartItemToDecrease) {
        cartItems.stream()
                .filter(item -> item.getMenuItemId().equals(cartItemToDecrease.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem->{
                    foundItem.decreaseQuantity(cartItemToDecrease.getQuantity());
                    if(foundItem.getQuantity()<=0){
                        this.cartItems.remove(foundItem);
                    }
                }, ()-> System.out.println("해당 아이템이 카트에 없습니다."));
    }

    //카트에 담긴 총 금액 확인
    public BigDecimal getCartTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(int i = 0 ; i < cartItems.size() ; i++){
            totalPrice = cartItems.get(i).getTotalPrice().add(totalPrice);
        }
        return totalPrice;
    }

    //현재 주문취소(카트 비우기)
    public void cartClear() {
        this.cartItems.clear();
    }
}
