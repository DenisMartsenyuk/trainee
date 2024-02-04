package com.devexperts;

import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemType;

import java.util.Arrays;

public class Main {

    public static ShoppingBag createShoppingBag() {
        Item beefGoodwill = new Item(1L, ItemType.MEAT, 10L, "Beef from Goodwill");
        Item beefMetro = new Item(2L, ItemType.MEAT, 11L, "Beef from Metro");
        Item porkMetro = new Item(3L, ItemType.MEAT, 8L, "Pork from Metro");

        Item parmesanGoodwill = new Item(4L, ItemType.CHEESE, 15L, "Parmesan from Goodwill");
        Item goudaGoodwill = new Item(5L, ItemType.CHEESE, 18L, "Gouda from Goodwill");
        Item parmesanMetro = new Item(6L, ItemType.CHEESE, 20L, "Parmesan from Metro");

        Item toothbrush = new Item(7L, ItemType.OTHER, 1L, "Toothbrush");
        Item napkins = new Item(8L, ItemType.OTHER, 2L, "Napkins");
        Item towel = new Item(9L, ItemType.OTHER, 7L, "Towel");
        Item shampoo = new Item(10L, ItemType.OTHER, 3L, "Shampoo");

        ShoppingBag shoppingBag = new ShoppingBag();
        shoppingBag.addItems(Arrays.asList(beefGoodwill, beefMetro, porkMetro, parmesanGoodwill,
                goudaGoodwill, parmesanMetro, toothbrush, napkins, towel, shampoo));

        return shoppingBag;
    }

    public static void main(String[] args) {

        ShoppingBag shoppingBag = createShoppingBag();

        System.out.println(shoppingBag);
    }
}