package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;

import java.util.List;

public class BaseStorage implements Storage {

    @Override
    public void arrangeItemsFromBag(ShoppingBag bag) {

    }

    @Override
    public void arrangeItem(Item item) {

    }

    @Override
    public ItemLocation whereIsItem(Long id) {
        return null;
    }

    @Override
    public Item getItem(Long id) {
        return null;
    }

    @Override
    public List<Item> getItemsByLocation(ItemLocation location) {
        return null;
    }

    @Override
    public List<Item> getAllMeat() {
        return null;
    }

    @Override
    public List<Item> getAllCheese() {
        return null;
    }
}
