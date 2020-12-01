package labs.lab08;

import labs.lab07.Item;
import labs.lab07.ProductType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Bag {
    private static final int minCapacity = 1;
    private ArrayList<Item> items = new ArrayList<>();
    private int totalCapacity, usedCapacity = 0;

    public Bag(int totalCapacity) throws IllegalArgumentException {
        if (totalCapacity < minCapacity) throw new IllegalArgumentException("Invalid totalCapacity: " + totalCapacity);
        this.totalCapacity = totalCapacity;
    }

    public void removeAllItems() {
        items.clear();
    }

    public boolean remove(ProductType type) {
        boolean removed = false;
        Iterator<Item> i = items.iterator();
        while (i.hasNext()) {
            Item item = i.next();
            if (item.getType() == type) {
                usedCapacity -= item.getQuantity();
                i.remove();
                removed = true;
            }
        }
        return removed;
    }

    public boolean remove(ProductType type, int n) {
        boolean removed = false;
        Iterator<Item> i = items.iterator();
        while (i.hasNext() && n > 0) {
            Item item = i.next();
            if (item.getType() == type) {
                if (item.getQuantity() > n) {
                    item.removeItems(n);
                    return true;
                }
                n -= item.getQuantity();
                i.remove();
                removed = true;
            }
        }
        return removed;
    }

    public boolean putIn(Item item) {
        if (usedCapacity + item.getQuantity() > totalCapacity) return false;
        items.add(item);
        usedCapacity += item.getQuantity();
        return true;
    }
}