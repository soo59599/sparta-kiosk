package kiosk.lv3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    List<MenuItem> menuItems = new ArrayList<>();

    public Kiosk() {
        menuItems.add(new MenuItem("ShackBurger", BigDecimal.valueOf(6.9), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", BigDecimal.valueOf(8.9), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", BigDecimal.valueOf(6.9), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", BigDecimal.valueOf(5.4), "비프패티를 기반으로 야채가 들어간 기본버거"));

    }

    void start(){
        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {

            System.out.println("[ SHAKESHACK MENU ]");
            for (MenuItem menuItem : menuItems) {
                System.out.println(menuItem.name + "| w " + menuItem.price + "|" + menuItem.description);
            }

            try {
                int choose = sc.nextInt();

                switch (choose) {
                    case 1:
                        System.out.println("ShackBurger 주문이 되었습니다.");
                        break;
                    case 2:
                        System.out.println("SmokeShack 주문이 되었습니다.");
                        break;
                    case 3:
                        System.out.println("Cheeseburger 주문이 되었습니다.");
                        break;
                    case 4:
                        System.out.println("Hamburger 주문이 되었습니다.");
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        break outer;

                    default:
                        throw new IllegalArgumentException("0~4번중에 입력하세요");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        sc.close();
    }
}
