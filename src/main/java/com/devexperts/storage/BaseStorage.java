package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;
import com.devexperts.enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BaseStorage implements Storage {
    HashMap<ItemLocation, HashMap<Long, Item>> storage;

    public BaseStorage() {
        this.storage = new HashMap<>();
        this.storage.put(ItemLocation.FRIDGE, new HashMap<>());
        this.storage.put(ItemLocation.SHELF, new HashMap<>());
    }

    @Override
    public void arrangeItemsFromBag(ShoppingBag bag) {
        List<Item> items = bag.getItems();

        for (Item item : items) {
            arrangeItem(item);
        }
    }

    @Override
    public void arrangeItem(Item item) {
        ItemLocation location = assignLocation(item.getType());

        storage.get(location).put(item.getId(), item);
    }

    private ItemLocation assignLocation(ItemType type) {
        ItemLocation location;

        switch (type){
            case CHEESE:
            case MEAT:
                location = ItemLocation.FRIDGE;
                break;
            case OTHER:
                location = ItemLocation.SHELF;
                break;
            default:
                location = ItemLocation.SHELF;
        }

        return location;
    }

    @Override
    public ItemLocation whereIsItem(Long id) {
        for (ItemLocation location : storage.keySet()) {
            HashMap<Long, Item> itemsInfo = storage.get(location);
            
            if (itemsInfo.containsKey(id)){
                return location;
            }
        }
        
        return null;
    }

    @Override
    public Item getItem(Long id) {
        for (ItemLocation location : storage.keySet()) {
            HashMap<Long, Item> itemsInfo = storage.get(location);
            
            if(itemsInfo.containsKey(id)){
                return this.storage.get(location).get(id);
            }
        }

        return null;
    }

    @Override
    public List<Item> getItemsByLocation(ItemLocation location) {
        return new ArrayList<>(this.storage.get(location).values());
    }

    @Override
    public List<Item> getAllMeat() {
        return getItemsFromFridgeByType(ItemType.MEAT);
    }

    @Override
    public List<Item> getAllCheese() {
        return getItemsFromFridgeByType(ItemType.CHEESE);
    }

    private List<Item> getItemsFromFridgeByType(ItemType type) {
        return this.storage.get(ItemLocation.FRIDGE).values()
                .stream().filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

}
