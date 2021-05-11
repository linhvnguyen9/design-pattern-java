package com.company;

public class Main {
    public static void main(String[] args) {
        Book testBook = new Reference("Head First Design Patterns", 45, 8, 9.25, 1.4);
        System.out.println(testBook.getPrice());

        BookCoverDecorator bookCoverDecorator = new BookCoverDecorator(testBook);
        System.out.println(bookCoverDecorator.getPrice());

        GiftWrapDecorator giftWrapDecorator = new GiftWrapDecorator(bookCoverDecorator);
        System.out.println(giftWrapDecorator.getPrice());
    }
}

interface Book {
    double getPrice();
    double getArea();
}

class Reference implements Book {
    private String title;
    private double price;
    private double width;
    private double length;
    private double height;

    public Reference(String title, double price, double width, double length, double height) {
        this.title = title;
        this.price = price;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getArea() {
        return width*length*2 + width*height*2 + length*height*2;
    }
}

class AddOnDecorator implements Book {
    Book book;

    public AddOnDecorator(Book book) {
        this.book = book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public double getArea() {
        return book.getArea();
    }

    @Override
    public double getPrice() {
        return book.getPrice();
    }
}

class BookCoverDecorator extends AddOnDecorator {
    public BookCoverDecorator(Book book) {
        super(book);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + book.getArea() * .05;
    }
}

class GiftWrapDecorator extends AddOnDecorator {
    public GiftWrapDecorator(Book book) {
        super(book);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + book.getArea() * .01;
    }
}
