public class print {
    public static void printR(String input) {
        System.out.println(ANSI.RED + input + ANSI.RESET);
    }

    public static void printR(String input, int br) {
        System.out.println(ANSI.RED + input + ANSI.RESET + "\n");
    }

    public static void printG(String input) {
        System.out.println(ANSI.GREEN + input + ANSI.RESET);
    }

    public static void printG(String input, int br) {
        System.out.println(ANSI.GREEN + input + ANSI.RESET + "\n");
    }

    public static void printY(String input) {
        System.out.println(ANSI.YELLOW + input + ANSI.RESET);
    }

    public static void printY(String input, int br) {
        System.out.println(ANSI.YELLOW + input + ANSI.RESET + "\n");
    }
}
