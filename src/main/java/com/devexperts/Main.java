package com.devexperts;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemType;
import com.devexperts.storage.BaseStorage;
import com.devexperts.storage.Storage;

import java.util.Arrays;

public class Main {

    public static ShoppingBag exampleShoppingBag() {
        Item beefGoodwill = new Item(1L, ItemType.MEAT, 10L, "Beef from Goodwill");

        Item parmesanGoodwill = new Item(4L, ItemType.CHEESE, 15L, "Parmesan from Goodwill");
        Item goudaGoodwill = new Item(5L, ItemType.CHEESE, 18L, "Gouda from Goodwill");

        Item napkins = new Item(8L, ItemType.OTHER, 2L, "Napkins");
        Item towel = new Item(9L, ItemType.OTHER, 7L, "Towel");

        ShoppingBag shoppingBag = new ShoppingBag();
        shoppingBag.addItems(Arrays.asList(beefGoodwill, parmesanGoodwill, goudaGoodwill, napkins, towel));

        return shoppingBag;
    }

    public static void main(String[] args) {

        ShoppingBag shoppingBag = exampleShoppingBag();
        Storage storage = new BaseStorage();
        storage.arrangeItemsFromBag(shoppingBag);
    }
}