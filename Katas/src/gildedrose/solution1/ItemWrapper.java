package gildedrose.solution1;

import gildedrose.problem.Item;

class ItemWrapper {
    private Item item;

    private ItemWrapper(Item item) {
        this.item = item;
    }

    static ItemWrapper[] generateArray(Item[] items) {
        ItemWrapper[] out = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) {
            out[i] = new ItemWrapper(items[i]);
        }
        return out;
    }

    String getName() {
        return item.name;
    }

    int getSellIn() {
        return item.sellIn;
    }

    int getQuality() {
        return item.quality;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
