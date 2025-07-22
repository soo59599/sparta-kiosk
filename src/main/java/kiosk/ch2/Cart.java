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
        cartItems = new ArrayList<>();
    }

    //장바구니 목록 보기
    public List<CartItem> getCartItems() {
        return cartItems.stream().collect(Collectors.toList());
    }

    //아이템 넣기
    public void increaseItemQuantity(CartItem cartItemToAdd) {
        cartItems.stream().filter(item -> item.getMenuItemId().equals(cartItemToAdd.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem -> foundItem.increaseQuantity(cartItemToAdd.getQuantity()),()-> this.cartItems.add(cartItemToAdd));
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
