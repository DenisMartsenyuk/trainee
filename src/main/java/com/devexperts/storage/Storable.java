package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.enums.ItemLocation;

import java.util.List;

public interface Storable {
    void arrangeItems(List<Item> items);
    void arrangeItem(Item item);
    ItemLocation whereIsItem(Long id);
    Item getItem(Long id);
    List<Item> getAllMeat();
    List<Item> getAllCheese();
}
