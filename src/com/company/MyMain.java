package com.company;

import java.util.ArrayList;
import java.util.List;

public class MyMain {
    public static void main(String[] args) {
        Item i1 = new Item("TV", 10);
        Item i2 = new Item("Computer", 10);

        List<Item> items = new ArrayList<>();
        items.add(i1);
        items.add(i2);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(i1, 20));
        orderItems.add(new OrderItem(i2, 100));

        Payment payment = new Payment("12345", "Vietcombank");

        Order order = new Order(orderItems, payment);
        OrderFacade facade = new OrderFacade(order);
        facade.order();
    }
}

class Item {
    String name;
    double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    Item item;
    double qty;

    public OrderItem(Item item, double qty) {
        this.item = item;
        this.qty = qty;
    }
}

class Payment {
    String cardId;
    String bankId;

    public Payment(String cardId, String bankId) {
        this.cardId = cardId;
        this.bankId = bankId;
    }
}

class Order {
    List<OrderItem> items;
    Payment payment;

    public Order(List<OrderItem> items, Payment payment) {
        this.items = items;
        this.payment = payment;
    }
}

class OrderFacade {
    private Order order;

    public OrderFacade(Order order) {
        this.order = order;
    }

    public void order() {
        StockManager stockManager = new StockManager();
        PaymentManager paymentManager = new PaymentManager();

        for (OrderItem item : order.items) {
            if (!stockManager.hasStock(item)) {
                System.out.println("Error! item out of stock " + item.item.name);
            } else {
                System.out.println("Valid item " + item.item.name);
            }
        }
        paymentManager.verifyPayment(order.payment);
    }
}

class StockManager {
    public boolean hasStock(OrderItem o) { return o.qty < 100; }
}

class PaymentManager {
    public boolean verifyPayment(Payment p) { return p.bankId.contains("1234"); }
}
