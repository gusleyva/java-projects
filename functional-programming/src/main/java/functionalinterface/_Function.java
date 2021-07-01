package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {
        int result = incrementByOne(1);
        System.out.println(result);

        int resultFunction = incrementByOneFunction.apply(1);
        System.out.println(resultFunction);

        int multiply = multiplyBy10Function.apply(resultFunction);
        System.out.println(multiply);

        System.out.println(incrementByOneFunction.andThen(multiplyBy10Function).apply(4));
    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;

    static int incrementByOne(int number) {
        return number + 1;
    }

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrement, multiplyBy) -> (numberToIncrement + 1) * multiplyBy;

    static int incrementByOneAndMultiply(int numberToIncrement, int multiplyBy) {
        return (numberToIncrement + 1) * multiplyBy;
    }
}
