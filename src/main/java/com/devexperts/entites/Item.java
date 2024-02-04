package com.devexperts.entites;

import com.devexperts.enums.ItemType;

public class Item {

    private final Long id;
    private final ItemType type;
    private final long price;
    private final String name;

    public Item(long id, ItemType type, Long price, String name) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
