package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;
import com.devexperts.enums.ItemType;

import java.util.*;
import java.util.stream.Collectors;

public class BaseStorage implements Storage {

    private final Map<ItemLocation, Map<Long, Item>> storage = new HashMap<>();

    public BaseStorage() {
        this.storage.put(ItemLocation.SHELF, new HashMap<>());
        this.storage.put(ItemLocation.FRIDGE, new HashMap<>());
    }

    @Override
    public void arrangeItemsFromBag(ShoppingBag bag) {
        List<Item> bagList = bag.getItems();
        for (Item item : bagList) {
           arrangeItem(item);
        }
    }

    @Override
    public void arrangeItem(Item item) {
        if (item.getType().equals(ItemType.MEAT) || item.getType().equals(ItemType.CHEESE)) {
            Map<Long, Item> itemHashMap = storage.get(ItemLocation.FRIDGE);
            itemHashMap.put(item.getId(), item);
        } else {
            Map<Long, Item> itemHashMap = storage.get(ItemLocation.SHELF);
            itemHashMap.put(item.getId(), item);
        }
    }

    @Override
    public ItemLocation whereIsItem(Long id) {
        Map<Long, Item> fridge = storage.get(ItemLocation.FRIDGE);
        if (fridge.containsKey(id)) {
            return ItemLocation.FRIDGE;
        } else {
            return ItemLocation.SHELF;
        }
    }

    @Override
    public Item getItem(Long id) {
        Map<Long, Item> fridge = storage.get(ItemLocation.FRIDGE);
        if (fridge.containsKey(id)) {
            return fridge.get(id);
        } else {
            return storage.get(ItemLocation.SHELF).get(id);
        }
    }

    @Override
    public List<Item> getItemsByLocation(ItemLocation location) {
        return new ArrayList<>(storage.get(location).values());
    }

    @Override
    public List<Item> getAllMeat() {
        return storage.get(ItemLocation.FRIDGE).values().stream()
                .filter(item -> item.getType().equals(ItemType.MEAT))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllCheese() {
        return storage.get(ItemLocation.FRIDGE).values().stream()
                .filter(item -> item.getType().equals(ItemType.CHEESE))
                .collect(Collectors.toList());
    }
}
