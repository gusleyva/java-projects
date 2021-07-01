package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        System.out.println(isValidPhone("70000000000"));
        System.out.println(isValidPhone("90000000000"));

        System.out.println(isValidPhonePredicate.test("70000000000"));
        System.out.println(isValidPhonePredicate.test("90000000000"));

        System.out.println(isValidPhonePredicate.and(containsNumber3Predicate).test("70000000000"));
        System.out.println(isValidPhonePredicate.and(containsNumber3Predicate).test("70000000003"));

    }

    static boolean isValidPhone(String phone){
        return phone.startsWith("7") && phone.length() == 11;
    }

    static Predicate<String> isValidPhonePredicate =
            phone -> phone.startsWith("7") && phone.length() == 11;

    static Predicate<String> containsNumber3Predicate =
            phone -> phone.contains("3");
}
