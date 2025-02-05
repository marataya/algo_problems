package core_java;

import java.time.LocalDate;
import java.time.Period;

public class Calendar {
    static LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        Period period = Period.ofMonths(5);
        date = date.plus(period);
        System.out.println(date);
    }

}
