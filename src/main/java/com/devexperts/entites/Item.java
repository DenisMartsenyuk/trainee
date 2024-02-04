package com.devexperts.entites;

import com.devexperts.enums.ItemType;

import java.util.Objects;

public class Item {

    private final Long id;
    private final ItemType type;
    private final long price;
    private final String name;

    public Item(Long id, ItemType type, long price, String name) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price && Objects.equals(id, item.id) && type == item.type && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, name);
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
