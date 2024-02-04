package com.devexperts.entites;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag {

    private final List<Item> items;

    public ShoppingBag() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(List<Item> newItems) {
        items.addAll(newItems);
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ShoppingBag{" +
                "items=" + items +
                '}';
    }
}
