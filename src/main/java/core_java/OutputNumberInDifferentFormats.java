package core_java;

public class OutputNumberInDifferentFormats {

    public static void main(String[] args) {
        int number = 789;
        // output number in binary format
        System.out.printf("In binary form: %4s\n", Integer.toBinaryString(number));
        System.out.printf("In octal form: %4s\n", Integer.toOctalString(number));
        System.out.printf("In hex form: %4s\n", Integer.toHexString(number));
    }
}
