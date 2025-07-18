package kiosk.lv4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private Menu menu;

    public Kiosk(Menu menu) {
        this.menu = menu;
    }

    public void start(){
        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {
            System.out.println("[ MAIN MENU ]");

            System.out.println("[ SHAKESHACK MENU ]");
            List<MenuItem> menuItems = menu.getMenuItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                System.out.println((i + 1) + ". " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
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
