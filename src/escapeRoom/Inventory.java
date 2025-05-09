package escapeRoom;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private HashSet<String> items;

    public Inventory() {
        items = new HashSet<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public boolean hasItem(String item) {
        return items.contains(item);
    }

    public Set<String> getItems() { // New method to get items
        return items;
    }

    public void displayItems() {
        System.out.println("Inventory: " + items);
    }
}
