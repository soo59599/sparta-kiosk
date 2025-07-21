package kiosk.ch1;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                //메인 메뉴 출력
                displayMainMenu();

                //첫번째 선택:메인에서 카테고리 고르기 (인덱스 편의를 위해 -1)
                int selectedCategoryIndex = sc.nextInt() - 1;

                //메인메뉴에서 종료 선택
                if (selectedCategoryIndex == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                //선택한 카테고리의 아이템 메뉴 출력
                if (-1 < selectedCategoryIndex && selectedCategoryIndex < menus.size()) {
                    Menu chosenMenu = menus.get(selectedCategoryIndex);
                    displayCategoryMenu(chosenMenu);

                    //두번째 선택: 아이템고르기 (인덱스 편의를 위해 -1)
                    int itemChoice = sc.nextInt() - 1;

                    //카테고리 메뉴에서 뒤로가기 선택
                    if (itemChoice == -1) {
                        continue;
                    }

                    //선택한 아이템 정보 출력
                    if (-1 < itemChoice && itemChoice < chosenMenu.getMenuItems().size()) {
                        MenuItem chosenItem = chosenMenu.getMenuItems().get(itemChoice);
                        System.out.printf("선택한 메뉴: %s | W %.1f | %s%n", chosenItem.getName(), chosenItem.getPrice(), chosenItem.getDescription());
                    } else {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }

                } else {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine(); // 버퍼 비우기

            }

        }

    }

    //메인메뉴 출력 메서드
    public void displayMainMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < this.menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료");

    }

    //카테고리 출력 메서드
    public void displayCategoryMenu(Menu menu) {
        System.out.println("\n[ " + menu.getCategory() + " MENU ]");

        for (int i = 0; i < menu.getMenuItems().size(); i++) {
            MenuItem item = menu.getMenuItems().get(i);
            System.out.printf("%d. %-15s | W %6.1f | %s%n", i + 1, item.getName(), item.getPrice(), item.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }
}
