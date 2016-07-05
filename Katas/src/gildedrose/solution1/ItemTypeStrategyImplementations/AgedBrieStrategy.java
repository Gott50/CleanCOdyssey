package gildedrose.solution1.ItemTypeStrategyImplementations;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategy;

public class AgedBrieStrategy extends NormalItemStrategy implements ItemTypeStrategy {
    @Override
    int getAdjustmentRate(Item item) {
        return +1;
    }

    @Override
    boolean isInQualityLimit(Item item) {
        return item.quality < 50;
    }

}

