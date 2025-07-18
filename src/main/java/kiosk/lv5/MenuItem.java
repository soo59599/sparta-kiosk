package kiosk.lv5;

import java.math.BigDecimal;

public class MenuItem {
    private String name;
    private BigDecimal price;
    private String description;

    public  MenuItem(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Getter
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
