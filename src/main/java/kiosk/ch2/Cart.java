package kiosk.ch2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart<T extends MenuItem> {

    private List<CartItem<T>> cartItems;

    //생성자
    public Cart() {
        cartItems = new ArrayList<>();
    }

    //장바구니 목록 보기
    public List<CartItem<T>> getCartItems() {
        return cartItems.stream().collect(Collectors.toList());
    }

    //아이템 몇개 넣기(Id 기준)
    public void increaseItemQuantity(CartItem<T> cartItemToAdd) {
        cartItems.stream()
                .filter(item -> item.getMenuItemId()
                        .equals(cartItemToAdd.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem -> foundItem.increaseQuantity(cartItemToAdd.getQuantity()), () -> cartItems.add(cartItemToAdd));
    }

    //아이템 몇개 빼기(Id 기준)
    public void decreaseItemQuantity(CartItem<T> cartItemToDecrease) {
        cartItems.stream()
                .filter(item -> item.getMenuItemId().equals(cartItemToDecrease.getMenuItemId()))
                .findFirst()
                .ifPresentOrElse(foundItem -> {
                    foundItem.decreaseQuantity(cartItemToDecrease.getQuantity()); //수량 감소

                    //아이템 갯수가 0이거나 음수일 때 장바구니에서 제거
                    if (foundItem.getQuantity() <= 0) {
                        cartItems.remove(foundItem);
                        System.out.println("수량이 0개 이하로 변경되어 장바구니에서 제거되었습니다.");
                    }


                }, () -> System.out.println("해당 아이템이 장바구니에 없습니다.")); //찾는 항목이 없을 때
    }

    //아이템 전부 빼기(name 기준)
    public void deleteItem(CartItem<T> cartItemToDelete) {
        //필터로 새 리스트를 만들어서 반환
//        cartItems = cartItems.stream()
//                .filter(item -> !item.getMenuItemName().equals(cartItemToDelete.getMenuItemName()))
//                .collect(Collectors.toList());

//        //removeIf활용
//        cartItems.removeIf(item -> item.getMenuItemName().equals(cartItemToDelete.getMenuItemName()));

        // 동일 이름의 아이템은 장바구니에 하나만 담겨있음->하나만 제거해도 모두 제거됨
        cartItems.stream().filter(item -> item.getMenuItemName().equals(cartItemToDelete.getMenuItemName())).findFirst().ifPresent(cartItems::remove);
    }

    //장바구니에 담긴 총 금액 확인
    public BigDecimal getCartTotalPrice() {
        return cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //현재 주문취소(장바구니 비우기)
    public void cartClear() {
        cartItems.clear();
    }
}
