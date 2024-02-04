package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;
import com.devexperts.enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseStorage implements Storage {
    private final Map<Long, Item> fridgeMeat = new HashMap<>();
    private final Map<Long, Item> fridgeCheese = new HashMap<>();
    private final Map<Long, Item> fridge = new HashMap<>();
    private final Map<Long, Item> shelf = new HashMap<>();

    @Override
    public void arrangeItemsFromBag(ShoppingBag bag) {
        bag.getItems().forEach(this::arrangeItem);
        for (Item item : bag.getItems()) {
            arrangeItem(item);
        }
    }

    @Override
    public void arrangeItem(Item item) {
        ItemLocation itemLocation = convert(item.getType());
        switch (itemLocation) {
            case FRIDGE:
                if (item.getType() == ItemType.MEAT) {
                    fridgeMeat.put(item.getId(), item);
                } else {
                    fridgeCheese.put(item.getId(), item);
                }
                fridge.put(item.getId(), item);
                return;
            case SHELF:
                shelf.put(item.getId(), item);
        }
    }

    @Override
    public ItemLocation whereIsItem(Long id) {
        if (fridge.containsKey(id)) {
            return ItemLocation.FRIDGE;
        } else if (shelf.containsKey(id)) {
            return ItemLocation.SHELF;
        }
        return null;
    }

    private ItemLocation convert(ItemType type) {
        if (type == ItemType.OTHER) {
            return ItemLocation.SHELF;
        }
        return ItemLocation.FRIDGE;
    }

    @Override
    public Item getItem(Long id) {
        Item item = fridge.get(id);
        if (item == null) {
            item = shelf.get(id);
        }
        return item;
    }

    @Override
    public List<Item> getItemsByLocation(ItemLocation location) {
        switch (location) {
            case FRIDGE:
                return new ArrayList<>(fridge.values());
            case SHELF:
                return new ArrayList<>(shelf.values());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> getAllMeat() {
        return new ArrayList<>(fridgeMeat.values());
    }

    @Override
    public List<Item> getAllCheese() {
        return new ArrayList<>(fridgeCheese.values());
    }
}
