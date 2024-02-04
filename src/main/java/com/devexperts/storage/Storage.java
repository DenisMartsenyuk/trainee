package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;

import java.util.List;

public interface Storage {

    void arrangeItemsFromBag(ShoppingBag bag);
    void arrangeItem(Item item);
    ItemLocation whereIsItem(Long id);
    Item getItem(Long id);
    List<Item> getItemsByLocation(ItemLocation location);
    List<Item> getAllMeat();
    List<Item> getAllCheese();
}
