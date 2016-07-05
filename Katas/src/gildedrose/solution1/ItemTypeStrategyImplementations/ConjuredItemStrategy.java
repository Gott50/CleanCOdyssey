package gildedrose.solution1.ItemTypeStrategyImplementations;

import gildedrose.problem.Item;
import gildedrose.solution1.ItemTypeStrategy;

public class ConjuredItemStrategy extends NormalItemStrategy implements ItemTypeStrategy {
    @Override
    int getAdjustmentRate(Item item) {
        return -2;
    }
}
