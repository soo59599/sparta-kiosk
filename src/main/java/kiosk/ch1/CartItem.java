package kiosk.ch1;


import java.math.BigDecimal;

public class CartItem {

    private final MenuItem menuItem;

    private int quantity;

    //생성자
    public CartItem(MenuItem menuItem, int quantity) {
        if(menuItem == null) {
            throw new IllegalArgumentException("장바구니에 물건이 담기지 않았습니다.");
        }
        if(quantity <= 0) {
            throw new IllegalArgumentException("0보다 적은 수를 입력할 수 없습니다.");
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public String getMenuItemId() {
        return menuItem.getId();
    }

    public String getMenuItemName() {
        return menuItem.getName();
    }

    public BigDecimal getMenuItemPrice() {
        return menuItem.getPrice();
    }

    public String getMenuItemDescription() {
        return menuItem.getDescription();
    }

    public int getQuantity() {return quantity;}

    //총 금액 계산
    public BigDecimal getTotalPrice() {
        return menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    // + 갯수 계산
    public void increaseQuantity(int quantity) {
        if(quantity > 0) {this.quantity += quantity;}
    }

    // - 갯수 계산
    public void decreaseQuantity(int quantity) {
        if(quantity > 0) {this.quantity -= quantity;}

    }

}
