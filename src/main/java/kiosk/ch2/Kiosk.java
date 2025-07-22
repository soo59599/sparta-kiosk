package kiosk.ch2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final List<Menu> menus;
    private final Cart cart = new Cart();


    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                //메인 메뉴 출력
                displayMainMenu();

                //장바구니에 아이템이 있을 때 오더 메뉴 출력
                if (!cart.getCartItems().isEmpty()) {
                    displayOrderMenu();
                }

                //첫번째 선택:메인에서 카테고리 고르기 (인덱스 편의를 위해 -1)
                //메인 메뉴에서 카테고리 선택 (1~3: 카테고리, 4: 주문, 5: 장바구니 비우기, 0: 종료)
                int selectedCategoryIndex = sc.nextInt() - 1;

                //메인메뉴에서 종료(0) 선택
                if (selectedCategoryIndex == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                //선택한 카테고리의 아이템 메뉴 출력
                //메인메뉴에서 카테고리(1~3) 선택
                if (-1 < selectedCategoryIndex && selectedCategoryIndex < 3) {
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
                        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인        2. 취소");

                        //세번째 선택: 장바구니 담기
                        int cartItemChoice = sc.nextInt();

                        //선택한 아이템 장바구니 추가
                        if (cartItemChoice == 1) {
                            System.out.println(chosenItem.getName() + " 이 장바구니에 추가되었습니다.");
                            cart.increaseItemQuantity(new CartItem(chosenItem, 1));

                        } else if (cartItemChoice == 2) {
                            //장바구니 담기에서 뒤로가기 선택
                            System.out.println("메인 메뉴로 돌아갑니다.");

                        } else {
                            throw new IllegalArgumentException("잘못된 입력입니다.");
                        }

                    } else {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }

                //메인메뉴에서 주문(4) 선택
                } else if (!cart.getCartItems().isEmpty() && selectedCategoryIndex == 3) {
                    displayCart();
                    System.out.println("1. 주문      2. 메뉴판");

                    //네번째 선택: 주문하기
                    int orderChoice = sc.nextInt();
                    sc.nextLine(); //버퍼 지우기

                    //주문하기
                    if (orderChoice == 1) {
                        displayDiscountRateMenu();

                        //다섯번째 선택: UserType 선택
                        int userTypeChoice = sc.nextInt();
                        sc.nextLine(); //버퍼 지우기

                        //주문 완료
                        UserType userType = UserType.values()[userTypeChoice - 1];
                        System.out.println("주문이 완료되었습니다. 금액은 W " + userType.getDiscountedPrice(cart.getCartTotalPrice()) + " 입니다.");
                        cart.cartClear();

                    //주문하기에서 뒤로가기
                    } else if (orderChoice == 2) {
                        System.out.println("메뉴판으로 돌아갑니다.");
                    }

                //메인메뉴에서 장바구니 비우기(5) 선택
                } else if (!cart.getCartItems().isEmpty() && selectedCategoryIndex == 4) {
                    cart.cartClear();
                    System.out.println("장바구니를 비웠습니다.");

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
        sc.close();

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

    //오더메뉴 출력 메서드
    public void displayOrderMenu() {
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
    }

    //장바구니 출력
    public void displayCart() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        for (int i = 0; i < cart.getCartItems().size(); i++) {
            CartItem cartItem = cart.getCartItems().get(i);
            System.out.printf("%d. %-15s | W %6.1f | %d개 | %s%n", i + 1, cartItem.getMenuItemName(), cartItem.getMenuItemPrice(), cartItem.getQuantity(), cartItem.getMenuItemDescription());
        }
        System.out.println("[ Total ]");
        System.out.println("W " + cart.getCartTotalPrice());
    }

    //할인정보 출력
    public void displayDiscountRateMenu() {
        System.out.println("할인 정보를 입력해주세요.");
        for (int i = 0; i < UserType.values().length; i++) {
            System.out.println((i + 1) + ". " + UserType.values()[i].getDisplayName() + " : " + UserType.values()[i].getDiscountPercent() + "%");
        }
    }
}
