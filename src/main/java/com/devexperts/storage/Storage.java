package com.devexperts.storage;

import com.devexperts.entites.Item;
import com.devexperts.enums.ItemType;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;

import java.util.List;

/**
 * interface Storage allows keeping items with ItemType type into different locations
 * also provide the opportunity to arrange items from shopping bag to keep them correctly
 * <p/>
 * {@link ItemType#MEAT} and {@link ItemType#CHEESE} should be kept in the {@link ItemLocation#FRIDGE}
 * {@link ItemType#OTHER} should be kept on the {@link ItemLocation#SHELF}
 *
 * @see ItemType as an item type
 * @see ItemLocation as a location type
 * @see ShoppingBag as a bag entity
 */
public interface Storage {
    /**
     * Arrange items from the bag, which could consist a certain item types
     * @param bag - bag with items which you need to keep in the storage
     */
    void arrangeItemsFromBag(ShoppingBag bag);

    /**
     * Add one item from the bag to the storage
     * @param item - item to add
     */
    void arrangeItem(Item item);

    /**
     * Calculate the item location
     * @param id - item id
     * @return item location
     */
    ItemLocation whereIsItem(Long id);

    /**
     * Getting the item by id
     * @param id - item id
     * @return item
     */
    Item getItem(Long id);

    /**
     * Getting the list of items by location
     * @param location - location
     * @return the list with items or empty list
     */
    List<Item> getItemsByLocation(ItemLocation location);

    /**
     * Getting the list of items with {@link ItemType#MEAT} type
     * @return the list of items or empty list
     */
    List<Item> getAllMeat();

    /**
     * Getting the list of items with {@link ItemType#CHEESE} type
     * @return the list of items or empty list
     */
    List<Item> getAllCheese();
}
