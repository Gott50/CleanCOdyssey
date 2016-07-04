package gildedrose.solution1;

import gildedrose.problem.Item;

class ItemBuilder {
    private String name;
    private int quality;
    private int sellIn;

    ItemBuilder() {
        name = "+5 Dexterity Vest";
        sellIn = 15;
        quality = 20;
    }

    ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    ItemBuilder withQuality(int quality) {
        this.quality = quality;
        return this;
    }


    ItemBuilder withSellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    Item build() {
        return new Item(name, sellIn, quality);
    }
}
