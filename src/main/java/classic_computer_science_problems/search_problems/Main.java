package classic_computer_science_problems.search_problems;

public class Main {
    public static void main(String[] args) {
        System.out.print("begin,");
        var firstCall = SeasonWithVisitors.SUMMER;   // Prints 4 times
        System.out.print("middle,");
        var secondCall = SeasonWithVisitors.SUMMER;  // Doesn't print anything
        System.out.print("end");
    }
}
