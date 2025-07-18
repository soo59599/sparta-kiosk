package kiosk.lv4;

import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {
            try {
                //첫번째 메인 메뉴
                System.out.println("[ MAIN MENU ]");
                for (int i = 0; i < this.menus.size(); i++) {
                    System.out.println((i + 1) + ". " + menus.get(i).category);
                }
                System.out.println("0. 종료");

                //첫번째 메인 메뉴 선택(인덱스 편의를 위해 -1)
                int categoryChoice = sc.nextInt()-1;

                //메인메뉴에서 종료 선택
                if (categoryChoice == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                //메인메뉴에서 카테고리 선택
                if (-1 < categoryChoice && categoryChoice < menus.size()) {
                    Menu chosenMenu = menus.get(categoryChoice);

                    System.out.println("[ " + chosenMenu.category + " MENU ]\n");

                    for(int i = 0; i < chosenMenu.getMenuItems().size(); i++) {
                        MenuItem item = chosenMenu.getMenuItems().get(i);
                        System.out.printf("%d. %-15s | W %6.1f | %s%n",
                                i + 1,
                                item.getName(),
                                item.getPrice(),
                                item.getDescription());
                    }
                } else {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
