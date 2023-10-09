package leapyear;

public class LeapYear {
    public static void main(String[] args) {
        int year = 1999;
        String message;

        boolean leapYear = (year % 4 == 0) && (!(year % 100 == 0) || (year % 400 == 0));

        if (leapYear) {
            message = "Das Jahr " + year + " ist ein Schaltjahr.";
        } else {
            message = "Das Jahr " + year + " ist kein Schaltjahr.";
        }

        System.out.println(message);
    }
}
