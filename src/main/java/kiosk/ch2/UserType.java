package kiosk.ch2;

import java.math.BigDecimal;

public enum UserType {
    VETERAN("국가유공자",BigDecimal.valueOf(0.10)),     // 국가유공자(10%)
    SOLDIER("군인",BigDecimal.valueOf(0.05)),      // 군인(5%)
    STUDENT("학생",BigDecimal.valueOf(0.03)),      // 학생(3%)
    GENERAL("일반인",BigDecimal.valueOf(0));     // 일반인(0%)

    private final String displayName;
    private final BigDecimal discountRate;

    UserType(String displayName ,BigDecimal salePercent) {
        this.displayName = displayName;
        this.discountRate = salePercent;
    }

    //UserType에 맞춰 할인된 값 구하기
    public BigDecimal getDiscountedPrice(BigDecimal totalPrice) {
        return totalPrice.multiply(BigDecimal.ONE.subtract(discountRate));
    }

    //이름 getter
    public String getDisplayName() {
        return displayName;
    }

    //백분율로 변환
    public BigDecimal getDiscountPercent() {
        return discountRate.multiply(BigDecimal.valueOf(100));
    }
}
