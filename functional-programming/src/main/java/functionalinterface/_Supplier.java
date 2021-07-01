package functionalinterface;

import java.util.function.Supplier;

public class _Supplier {

    public static void main(String[] args) {

    }

    static String getDBConnection() {
        return "jdbc://localhost:9999/users";
    }

    static Supplier<String> getDBConnectionSupplier =
            () -> "jdbc://localhost:9999/users";
}
