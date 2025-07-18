package kiosk.lv4;

import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start(){
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

                //첫번째 메인 메뉴 선택
                int choose1 = sc.nextInt();

                //메인메뉴에서 종료 선택
                if (choose1 == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                //메인메뉴에서 카테고리 선택
                if (0 < choose1 && choose1 < this.menus.size()+1) {
                    System.out.println("[ "+ menus.get(choose1-1).category +" MENU ]");
                } else {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
