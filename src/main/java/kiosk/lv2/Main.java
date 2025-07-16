package kiosk.lv2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        outer:
        while (true) {
            List<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(new MenuItem("ShackBurger", BigDecimal.valueOf(6.9), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
            menuItems.add(new MenuItem("SmokeShack", BigDecimal.valueOf(8.9), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
            menuItems.add(new MenuItem("Cheeseburger", BigDecimal.valueOf(6.9), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
            menuItems.add(new MenuItem("Hamburger", BigDecimal.valueOf(5.4), "비프패티를 기반으로 야채가 들어간 기본버거"));

            Scanner sc = new Scanner(System.in);

            System.out.println("[ SHAKESHACK MENU ]");
            for (MenuItem menuItem : menuItems) {
                System.out.println(menuItem.name + "| w " + menuItem.price + "|" + menuItem.description);
            }

        }
    }
}
