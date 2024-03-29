public class DynamicClass {
    static {
        System.out.println("static block is executed 111");
    }

    public static String str = "abcdefg111";

    public void printMessage() {
        System.out.println("Hello World 111");
    }
}
