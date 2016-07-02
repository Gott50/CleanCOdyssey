package gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {
    private class ItemBuilder {
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

    @Test
    public void testItemName() {
        Item[] items = new Item[]{new ItemBuilder().withName("foo").build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    public void testItemToString() {
        Item[] items = new Item[]{new ItemBuilder().withName("+5 Dexterity Vest").withSellIn(10).withQuality(20).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("+5 Dexterity Vest, 9, 19", app.items[0].toString());
    }

    @Test
    public void testItemUpdateSellIn() {
        Item[] items = new Item[]{new ItemBuilder().withSellIn(10).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void testItemUpdateQuality() {
        Item[] items = new Item[]{new ItemBuilder().withQuality(20).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
    }

    @Test
    public void testItemPassedSellIn() {
        Item[] items = new Item[]{new ItemBuilder().withSellIn(0).withQuality(20).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
    }

    @Test
    public void testItemQualityAlwaysPositive() {
        Item[] items = new Item[]{new ItemBuilder().withSellIn(0).withQuality(0).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testAgedBrieUpdateQuality() {
        Item[] items = new Item[]{new ItemBuilder().withName("Aged Brie").withQuality(20).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    public void testAgedBrieUpdateQualityWithSellInLessThan0() {
        Item[] items = new Item[]{new ItemBuilder().withName("Aged Brie").withSellIn(-1).withQuality(20).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    public void testAgedBrieUpdateQualityLess51() {
        Item[] items = new Item[]{new ItemBuilder().withName("Aged Brie").withQuality(50).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue(app.items[0].quality < 51);
    }

    @Test
    public void testSulfurasUpdateQuality() {
        Item[] items = new Item[]{new ItemBuilder().withName("Sulfuras, Hand of Ragnaros").withQuality(80).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void testBackstagePassUpdateQuality() {
        Item[] items = new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void testBackstagePassUpdateQualityWithSellInLessThan10() {
        Item[] items = new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(9).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void testBackstagePassUpdateQualityWithSellInLessThan5() {
        Item[] items = new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(4).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    @Test
    public void testBackstagePassUpdateQualityAfterConcert() {
        Item[] items = new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(0).build()};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

}
