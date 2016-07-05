package gildedrose.solution1.ItemTypeStrategyImplementations;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategy;

public class NormalItemStrategy implements ItemTypeStrategy {

    @Override
    public void update(Item item) {
        updateSellIn(item);
        updateQuality(item);
    }

    private void updateQuality(Item item) {
        adjustQuality(item);

        if (isPassedSellInDate(item))
            adjustQuality(item);
    }

    private void adjustQuality(Item item) {
        if (isInQualityLimit(item)) item.quality += getAdjustmentRate(item);
    }

    private boolean isPassedSellInDate(Item item) {
        return item.sellIn < 0;
    }

    void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    int getAdjustmentRate(Item item) {
        return -1;
    }

    boolean isInQualityLimit(Item item) {
        return item.quality > 0;
    }

}
