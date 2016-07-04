package gildedrose.solution1;

import gildedrose.problem.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    @Test
    public void testItemName() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("foo").build()});
        gildedRose.updateQuality();
        assertEquals("foo", gildedRose.getItems()[0].getName());
    }

    @Test
    public void testItemToString() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("+5 Dexterity Vest").withSellIn(10).withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals("+5 Dexterity Vest, 9, 19", gildedRose.getItems()[0].toString());
    }

    @Test
    public void testItemUpdateSellIn() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withSellIn(10).build()});
        gildedRose.updateQuality();
        assertEquals(9, gildedRose.getItems()[0].getSellIn());
    }

    @Test
    public void testItemUpdateQuality() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals(19, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testItemPassedSellIn() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withSellIn(0).withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals(18, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testItemQualityAlwaysPositive() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withSellIn(0).withQuality(0).build()});
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testAgedBrieUpdateQuality() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Aged Brie").withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals(21, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testAgedBrieUpdateQualityWithSellInLessThan0() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Aged Brie").withSellIn(-1).withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals(22, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testAgedBrieUpdateQualityLess51() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Aged Brie").withQuality(50).build()});
        gildedRose.updateQuality();
        assertTrue(gildedRose.getItems()[0].getQuality() < 51);
    }

    @Test
    public void testSulfurasUpdateQuality() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Sulfuras, Hand of Ragnaros").withQuality(80).build()});
        gildedRose.updateQuality();
        assertEquals(80, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testBackstagePassUpdateQuality() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).build()});
        gildedRose.updateQuality();
        assertEquals(11, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testBackstagePassUpdateQualityWithSellInLessThan10() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(9).build()});
        gildedRose.updateQuality();
        assertEquals(12, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testBackstagePassUpdateQualityWithSellInLessThan5() {

        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(4).build()});
        gildedRose.updateQuality();
        assertEquals(13, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testBackstagePassUpdateQualityAfterConcert() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Backstage passes to a TAFKAL80ETC concert").withQuality(10).withSellIn(0).build()});
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.getItems()[0].getQuality());
    }

    @Test
    public void testConjuredItemUpdateQuality() {
        GildedRose gildedRose = new GildedRose(new Item[]{new ItemBuilder().withName("Conjured").withQuality(20).build()});
        gildedRose.updateQuality();
        assertEquals(18, gildedRose.getItems()[0].getQuality());
    }

}
