package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        Customer maria = new Customer("Maria", "66666");
        greetingsCustomer(maria);

        grettingsCustomerConsumer.accept(maria);

        grettingsCustomerConsumerV2.accept(maria, false);
    }

    static Consumer<Customer> grettingsCustomerConsumer =
            customer -> System.out.println("Greetings " + customer.name + ", thank you for registering phone " + customer.phone);

    static BiConsumer<Customer, Boolean> grettingsCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println("Greetings " + customer.name
                    + ", thank you for registering phone " + (showPhoneNumber ? customer.phone : "****"));

    static void greetingsCustomer(Customer customer) {
        System.out.println("Greetings " + customer.name + ", thank you for registering phone " + customer.phone);
    }

    static class Customer {
        private final String name;
        private final String phone;

        public Customer(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
    }
}
