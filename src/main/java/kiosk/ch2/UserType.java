package kiosk.ch2;

import java.math.BigDecimal;

public enum UserType {
    VETERAN(BigDecimal.valueOf(0.10)),     // 국가유공자(10%)
    SOLDIER(BigDecimal.valueOf(0.05)),      // 군인(5%)
    STUDENT(BigDecimal.valueOf(0.03)),      // 학생(3%)
    GENERAL(BigDecimal.valueOf(0));     // 일반인(0%)

    private BigDecimal discountRate;

    UserType(BigDecimal salePercent) {
        this.discountRate = salePercent;
    }

    public BigDecimal getDiscountedPrice(BigDecimal totalPrice, int num) {
        int index = num-1;
        UserType[] users = UserType.values();

        if(index <0 || index >= users.length) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        BigDecimal chosenRate = users[index].discountRate;

        return totalPrice.multiply(chosenRate);

    }
}
