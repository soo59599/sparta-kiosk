package kiosk.lv5;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final String category;

    List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String category, List<MenuItem> menuItems) {
        this.category = category;
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems.stream().toList();
    }

    public String getCategory(){
        return this.category;
    }
}
