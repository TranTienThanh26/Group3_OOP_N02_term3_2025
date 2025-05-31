interface Icon {}

public class testMenu {

    static class Menu {
        private Icon icon;
        private String menuName;
        private String[] subMenus;

        public Menu(Icon icon, String menuName, String... subMenus) {
            this.icon = icon;
            this.menuName = menuName;
            this.subMenus = subMenus;
        }

        public String getMenuName() {
            return menuName;
        }

        public String[] getSubMenu() {
            return subMenus;
        }

        public Icon getIcon() {
            return icon;
        }
    }

    public static void test() {
        // Dung null neu khong co icon that
        Icon icon = null;

        Menu menu = new Menu(icon, "Thuc don", "Khai vi", "Mon chinh", "Trang mieng");

        System.out.println("Ten menu: " + menu.getMenuName());

        System.out.println("Danh sach muc con:");
        String[] subMenus = menu.getSubMenu();
        if (subMenus != null) {
            for (String item : subMenus) {
                System.out.println("- " + item);
            }
        } else {
            System.out.println("Khong co muc con nao.");
        }

        if (menu.getIcon() != null) {
            System.out.println("Da co bieu tuong.");
        } else {
            System.out.println("Chua co bieu tuong.");
        }
    }

    public static void main(String[] args) {
        test();
    }
}
