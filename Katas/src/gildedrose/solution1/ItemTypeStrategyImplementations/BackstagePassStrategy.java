package gildedrose.solution1.ItemTypeStrategyImplementations;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategy;

public class BackstagePassStrategy extends AgedBrieStrategy implements ItemTypeStrategy {
    @Override
    int getAdjustmentRate(Item item) {
        int rate = +1;
        if (item.sellIn < 11) rate++;
        if (item.sellIn < 6) rate++;
        if (item.sellIn < 0) rate = -item.quality;
        return rate;
    }
}
