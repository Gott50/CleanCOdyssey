package gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);

            updateSellIn(item);

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePass(item)) {
                        if (item.quality > 0) {
                            if (!isSulfuras(item)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    updateQualityAgedBrie(item);
                }
            }
        }
    }

    private void updateQualityAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void updateQuality(Item item) {
        if (!isAgedBrie(item)
                && !isBackstagePass(item)) {
            if (item.quality > 0) {
                if (!isSulfuras(item)) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            updateAgedBrie(item);
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (isBackstagePass(item)) {
                updateBackstagePass(item);
            }
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn < 11) {
            updateQualityAgedBrie(item);
        }

        if (item.sellIn < 6) {
            updateQualityAgedBrie(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }
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
}
