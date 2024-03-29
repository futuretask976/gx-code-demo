public class DynamicClass2 {
    static {
        System.out.println("static block is executed 112");
    }

    public static String str = "abcdefg112";

    public void printMessage() {
        System.out.println("Hello World 112");
    }
}
