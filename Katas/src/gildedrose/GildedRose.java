package gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            updateSellIn(item);
            updateQuality(item);
        }
    }

    private void updateQuality(Item item) {
        if (isAgedBrie(item)
                || isBackstagePass(item)) updateAgedBrie(item);
        else if (item.quality > 0) decreeseQuality(item);

        updatePassedSellInDate(item);
    }

    private void updatePassedSellInDate(Item item) {
        if (item.sellIn < 0)
            if (isAgedBrie(item)) updateAgedBrie(item);
            else if (item.quality > 0) decreeseQuality(item);
    }


    private void decreeseQuality(Item item) {
        if (!isSulfuras(item)) {
            item.quality = item.quality - 1;
            if (isConjuredItem(item)) item.quality = item.quality - 1;
        }
    }


    private void increaseQuality(Item item) {
        if (isQualityLassMax(item)) item.quality = item.quality + 1;
    }


    private void updateAgedBrie(Item item) {
        increaseQuality(item);
        if (isQualityLassMax(item) && isBackstagePass(item)) updateBackstagePass(item);
    }

    private boolean isQualityLassMax(Item item) {
        return item.quality < 50;
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn < 11) increaseQuality(item);
        if (item.sellIn < 6) increaseQuality(item);
        if (item.sellIn < 0) item.quality = 0;
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) item.sellIn = item.sellIn - 1;
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isConjuredItem(Item item) {
        return item.name.contains("Conjured");
    }
}
