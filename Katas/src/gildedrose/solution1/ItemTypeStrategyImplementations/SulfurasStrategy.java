package gildedrose.solution1.ItemTypeStrategyImplementations;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategy;

public class SulfurasStrategy extends NormalItemStrategy implements ItemTypeStrategy {
    @Override
    int getAdjustmentRate(Item item) {
        return 0;
    }

    @Override
    void updateSellIn(Item item) {
        item.sellIn = 80;
    }
}
