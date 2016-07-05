package gildedrose.solution1;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategyImplementations.*;


public class ItemWrapper {

    private final Item item;
    private final ItemTypeStrategy typeStrategy;

    ItemWrapper(Item item) {
        this.item = item;
        this.typeStrategy = generateItemTypeStrategy(item);
    }

    private ItemTypeStrategy generateItemTypeStrategy(Item item) {
        if (item.name.equals("Aged Brie")) return new AgedBrieStrategy();
        else if (item.name.equals("Sulfuras, Hand of Ragnaros")) return new SulfurasStrategy();
        else if (item.name.contains("Backstage pass")) return new BackstagePassStrategy();
        else if (item.name.contains("Conjured")) return new ConjuredItemStrategy();
        return new NormalItemStrategy();
    }

    public String getName() {
        return item.name;
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public int getQuality() {
        return item.quality;
    }

    @Override
    public String toString() {
        return item.toString();
    }

    void update() {
        typeStrategy.update(item);
    }
}
