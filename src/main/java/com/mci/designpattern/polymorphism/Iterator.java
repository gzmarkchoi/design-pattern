package com.mci.designpattern.polymorphism;


public interface Iterator {
    boolean hasNext();
    String next();
    String remove();
}

class Array implements Iterator {
    private String[] data;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        //...
        return "Array next";
    }

    @Override
    public String remove() {
        //...
        return "Array remove";
    }
    //...
}

class LinkedList implements Iterator {
    private LinkedListNode head;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        //...
        return "LinkedList next";
    }

    @Override
    public String remove() {
        //...
        return "LinkedList remove";
    }
    //...
}

class Demo {
    private static void print(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);

        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }
}
