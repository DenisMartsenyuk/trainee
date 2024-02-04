import com.devexperts.entites.Item;
import com.devexperts.entites.ShoppingBag;
import com.devexperts.enums.ItemLocation;
import com.devexperts.enums.ItemType;
import com.devexperts.storage.BaseStorage;
import com.devexperts.storage.Storage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseStorageTest {

    public static ShoppingBag createBigShoppingBag() {
        Item beefGoodwill = new Item(1L, ItemType.MEAT, 10L, "Beef from Goodwill");
        Item beefMetro = new Item(2L, ItemType.MEAT, 11L, "Beef from Metro");
        Item porkMetro = new Item(3L, ItemType.MEAT, 8L, "Pork from Metro");
        Item lambGoodwill = new Item(4L, ItemType.MEAT, 8L, "Lamb from Goodwill");
        Item chickenMetro = new Item(5L, ItemType.MEAT, 8L, "Chicken from Metro");

        Item parmesanGoodwill = new Item(6L, ItemType.CHEESE, 15L, "Parmesan from Goodwill");
        Item goudaGoodwill = new Item(7L, ItemType.CHEESE, 18L, "Gouda from Goodwill");
        Item parmesanMetro = new Item(8L, ItemType.CHEESE, 20L, "Parmesan from Metro");
        Item mozzarellaGoodwill = new Item(9L, ItemType.CHEESE, 15L, "Mozzarella from Goodwill");
        Item cheddarMetro = new Item(10L, ItemType.CHEESE, 20L, "Cheddar from Metro");

        Item toothbrush = new Item(11L, ItemType.OTHER, 1L, "Toothbrush");
        Item napkins = new Item(12L, ItemType.OTHER, 2L, "Napkins");
        Item towel = new Item(13L, ItemType.OTHER, 7L, "Towel");
        Item shampoo = new Item(14L, ItemType.OTHER, 3L, "Shampoo");
        Item showerGel =  new Item(15L, ItemType.OTHER, 3L, "ShowerGel");
        Item toothpaste = new Item(16L, ItemType.OTHER, 1L, "Toothpaste");

        ShoppingBag shoppingBag = new ShoppingBag();
        List<Item> items = Arrays.asList(
                beefGoodwill,
                beefMetro,
                porkMetro,
                lambGoodwill,
                chickenMetro,
                parmesanGoodwill,
                mozzarellaGoodwill,
                cheddarMetro,
                goudaGoodwill,
                parmesanMetro,
                toothbrush,
                napkins,
                towel,
                shampoo,
                showerGel,
                toothpaste
        );
        shoppingBag.addItems(items);

        return shoppingBag;
    }

    @Test
    void emptyStorage() {
        Storage baseStorage = new BaseStorage();

        assertAll(
                () -> assertEquals(baseStorage.getAllMeat().size(), 0),
                () -> assertEquals(baseStorage.getAllCheese().size(), 0),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.FRIDGE).size(), 0),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.SHELF).size(), 0)
        );
    }

    @Test
    void oneMeatStorage() {
        Long id = 1L;
        Item item = new Item(id, ItemType.MEAT, 10L, "Beef from Goodwill");
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItem(item);

        assertAll(
                () -> assertEquals(baseStorage.getAllMeat().size(), 1),
                () -> assertEquals(baseStorage.getItem(id), item),
                () -> assertEquals(baseStorage.whereIsItem(id), ItemLocation.FRIDGE),
                () -> assertEquals(baseStorage.getAllCheese().size(), 0),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.FRIDGE).size(), 1),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.SHELF).size(), 0)
        );
    }

    @Test
    void oneCheeseStorage() {
        Long id = 1L;
        Item item = new Item(id, ItemType.CHEESE, 15L, "Parmesan from Goodwill");
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItem(item);

        assertAll(
                () -> assertEquals(baseStorage.getAllCheese().size(), 1),
                () -> assertEquals(baseStorage.getItem(id), item),
                () -> assertEquals(baseStorage.whereIsItem(id), ItemLocation.FRIDGE),
                () -> assertEquals(baseStorage.getAllMeat().size(), 0),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.FRIDGE).size(), 1),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.SHELF).size(), 0)
        );
    }

    @Test
    void oneShelfStorage() {
        Long id = 1L;
        Item item = new Item(id, ItemType.OTHER, 1L, "Toothbrush");
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItem(item);

        assertAll(
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.SHELF).size(), 1),
                () -> assertEquals(baseStorage.getItem(id), item),
                () -> assertEquals(baseStorage.whereIsItem(id), ItemLocation.SHELF),
                () -> assertEquals(baseStorage.getAllMeat().size(), 0),
                () -> assertEquals(baseStorage.getAllCheese().size(), 0),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.FRIDGE).size(), 0)
        );
    }

    @Test
    void sizeStorage() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        assertAll(
                () -> assertEquals(baseStorage.getAllMeat().size(), 5),
                () -> assertEquals(baseStorage.getAllCheese().size(), 5),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.FRIDGE).size(), 10),
                () -> assertEquals(baseStorage.getItemsByLocation(ItemLocation.SHELF).size(), 6)
        );
    }

    @Test
    void meatStorage() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        Set<Item> actualIds = bag.getItems().stream()
                .filter(item -> item.getType() == ItemType.MEAT)
                .collect(Collectors.toSet());

        Set<Item> expectedIds = new HashSet<>(baseStorage.getAllMeat());

        assertEquals(expectedIds, actualIds);
    }

    @Test
    void cheeseStorage() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        Set<Item> actualIds = bag.getItems().stream()
                .filter(item -> item.getType() == ItemType.CHEESE)
                .collect(Collectors.toSet());

        Set<Item> expectedIds = new HashSet<>(baseStorage.getAllCheese());

        assertEquals(expectedIds, actualIds);
    }

    @Test
    void fridgeStorage() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        Set<Item> actualIds = bag.getItems().stream()
                .filter(item -> item.getType() == ItemType.MEAT || item.getType() == ItemType.CHEESE)
                .collect(Collectors.toSet());

        Set<Item> expectedIds = new HashSet<>(baseStorage.getItemsByLocation(ItemLocation.FRIDGE));

        assertEquals(expectedIds, actualIds);
    }

    @Test
    void shelfStorage() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        Set<Item> actualIds = bag.getItems().stream()
                .filter(item -> item.getType() == ItemType.OTHER)
                .collect(Collectors.toSet());

        Set<Item> expectedIds = new HashSet<>(baseStorage.getItemsByLocation(ItemLocation.SHELF));

        assertEquals(expectedIds, actualIds);
    }

    @Test
    void checkSpoiledItems() {
        ShoppingBag bag = createBigShoppingBag();
        Storage baseStorage = new BaseStorage();
        baseStorage.arrangeItemsFromBag(bag);

        List<Item> onlyMeatAndCheese = baseStorage.getItemsByLocation(ItemLocation.FRIDGE);
        assertEquals(0, onlyMeatAndCheese.stream()
                .filter(it -> it.getType() != ItemType.CHEESE && it.getType() != ItemType.MEAT)
                .count()
        );

        List<Item> onlyOther = baseStorage.getItemsByLocation(ItemLocation.SHELF);
        assertEquals(0, onlyOther.stream()
                .filter(it -> it.getType() != ItemType.OTHER)
                .count()
        );
    }
}
