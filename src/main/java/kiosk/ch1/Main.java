package kiosk.ch1;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //햄버거 메뉴
        List<MenuItem> bugerItems = Arrays.asList(
        new MenuItem("ShackBurger", BigDecimal.valueOf(6.9), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
        new MenuItem("SmokeShack", BigDecimal.valueOf(8.9), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
        new MenuItem("Cheeseburger", BigDecimal.valueOf(6.9), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
        new MenuItem("Hamburger", BigDecimal.valueOf(5.4), "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        Menu bugerMenu = new Menu("BURGER", bugerItems);

        //음료 메뉴
        List<MenuItem> drinkItems = Arrays.asList(
                new MenuItem("Cola", BigDecimal.valueOf(1.9), "시원한 콜라"),
                new MenuItem("Lemonade", BigDecimal.valueOf(2.5), "상큼한 레몬에이드"),
                new MenuItem("Iced Tea", BigDecimal.valueOf(2.3), "향긋한 아이스티"),
                new MenuItem("Water", BigDecimal.valueOf(1.5), "생수"),
                new MenuItem("Shake", BigDecimal.valueOf(4.9), "바닐라")
        );

        Menu dinkMenu = new Menu("DRINKS", drinkItems);

        // 디저트 메뉴
        List<MenuItem> dessertItems = Arrays.asList(
                new MenuItem("Frozen Custard", BigDecimal.valueOf(4.5), "쉐이크쉑 시그니처 바닐라 커스터드"),
                new MenuItem("Chocolate Custard", BigDecimal.valueOf(4.5), "진한 초콜릿 커스터드"),
                new MenuItem("Shack Attack", BigDecimal.valueOf(5.9), "초콜릿 푸딩, 브라우니, 초코칩이 토핑된 디저트"),
                new MenuItem("Cookie Dough Scoop", BigDecimal.valueOf(3.9), "달콤한 쿠키 도우 한 스쿱"),
                new MenuItem("Brownie Bite", BigDecimal.valueOf(2.9), "진한 초콜릿 브라우니 한 입")
        );

        Menu dessertMenu = new Menu("DESSERT", dessertItems);

        List<Menu> menus = Arrays.asList(
                bugerMenu,
                dinkMenu,
                dessertMenu
        );

        Kiosk kiosk = new Kiosk(menus);

        kiosk.start();
    }
}

