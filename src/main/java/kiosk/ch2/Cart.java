package kiosk.ch2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {

    private List<CartItem> cartItems;

    //생성자
    public Cart() {
        cartItems = new ArrayList<>();
    }

    //장바구니 목록 보기
    public List<CartItem> getCartItems() {
        return cartItems.stream().collect(Collectors.toList());
    }

    //아이템 몇개 넣기(Id 기준)
    public void increaseItemQuantity(CartItem cartItemToAdd) {
        cartItems.stream().filter(item -> item.getMenuItemId().equals(cartItemToAdd.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem -> foundItem.increaseQuantity(cartItemToAdd.getQuantity()), () -> cartItems.add(cartItemToAdd));
    }

    //아이템 몇개 빼기(Id 기준)
    public void decreaseItemQuantity(CartItem cartItemToDecrease) {
        cartItems.stream()
                .filter(item -> item.getMenuItemId().equals(cartItemToDecrease.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem -> {
                    foundItem.decreaseQuantity(cartItemToDecrease.getQuantity()); //수량 감소

                    if (foundItem.getQuantity() <= 0) {
                        cartItems.remove(foundItem); //수량이 0 이하가 되면 장바구니에서 제거
                    }


                }, () -> System.out.println("해당 아이템이 카트에 없습니다.")); //찾는 항목이 없을 때
    }

    //아이템 전부 빼기(name 기준)
    public void deleteItem(CartItem cartItemToDelete) {
        //필터로 새 리스트를 만들어서 반환
//        cartItems = cartItems.stream()
//                .filter(item -> !item.getMenuItemName().equals(cartItemToDelete.getMenuItemName()))
//                .collect(Collectors.toList());

//        //removeIf활용
//        cartItems.removeIf(item -> item.getMenuItemName().equals(cartItemToDelete.getMenuItemName()));

        // 동일 이름의 아이템은 카트에 하나만 담겨있음->하나만 제거해도 모두 제거됨
        cartItems.stream().filter(item -> item.getMenuItemName().equals(cartItemToDelete.getMenuItemName())).findFirst().ifPresent(cartItems::remove);
    }

    //카트에 담긴 총 금액 확인
    public BigDecimal getCartTotalPrice() {
        return cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //현재 주문취소(카트 비우기)
    public void cartClear() {
        cartItems.clear();
    }
}
