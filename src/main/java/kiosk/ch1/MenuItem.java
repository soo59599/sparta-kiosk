package kiosk.ch1;

import java.math.BigDecimal;
import java.util.UUID;

public class MenuItem {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;

    public MenuItem(String name, BigDecimal price, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Getter
    public String getId() {return id;}

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
