package gildedrose.solution1;

import gildedrose.problem.Item;

public class GildedRose {

    private final ItemWrapper[] items;

    public GildedRose(Item[] items) {
        ItemWrapper[] out = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) out[i] = new ItemWrapper(items[i]);
        this.items = out;
    }

    public ItemWrapper[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (ItemWrapper item : items) item.update();
    }

}
