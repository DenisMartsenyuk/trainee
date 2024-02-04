# Description
**There is an interview task with a Java intern**

You need to implement the [Storage](./src/main/java/com/devexperts/storage/Storage.java) interface 
which is used to store certain products such as meat, cheese and others.
There are two possible ways to keep items. The first one is in the fridge, the second one is on the shelf.
See [ItemLocation](./src/main/java/com/devexperts/enums/ItemLocation.java) class
And there are some item types, see [ItemType](./src/main/java/com/devexperts/enums/ItemType.java) class.
Meat and cheese must be kept in a fridge.
Other items must be kept on a shelf.

You should find a way to implement the function ``arrangeItemsFromBag()`` to arrange the items from your shopping bag.

_!Important note: You should focus on the speed of getting and analyzing items rather than memory consumption_

The picture below illustrates this process.
![Description diagram of the task.](./docs/description_diagram.png)

To test your solution you could use the [BaseStorageTest](./src/test/java/BaseStorageTest.java)
Also there is some initialization methods in the [Main](./src/main/java/com/devexperts/Main.java) class.

_As a reminder, meat may spoil on the shelf, and there is no need to store napkins in the fridge._  

# Getting Started

## Prerequisites
- Java 11+
- Maven
- Git 

## Installation
Clone git repository
```
git clone https://github.com/your_username_/Project-Name.git
```
Create branch with your first name and last name
```
git branch master/firstname_secondname
```
Commit your solution to your new branch :)
