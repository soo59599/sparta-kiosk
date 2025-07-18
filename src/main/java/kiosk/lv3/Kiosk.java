package kiosk.lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    List<MenuItem> menuItems = new ArrayList<>();

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
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
