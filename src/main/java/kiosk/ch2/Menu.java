package kiosk.ch2;

import java.util.List;

public class Menu {

    private String category;

    private List<MenuItem> menuItems;

    public Menu(String category, List<MenuItem> menuItems) {
        this.category = category;
        this.menuItems = menuItems;
    }

    // Getter
    public String getCategory() {
        return this.category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems.stream().toList();
    }

    // Setter
    public void setCategory(String category) {
        this.category = category;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
