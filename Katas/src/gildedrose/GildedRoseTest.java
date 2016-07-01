package gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {
	 class ItemBuilder{
		private String name;
		private int quality;
		private int sellIn;

		public ItemBuilder() {
			name = "+5 Dexterity Vest";
			sellIn = 10;
			quality = 20;
		}
		public ItemBuilder withName(String name) {
			this.name = name;
			return this;
		}
		public ItemBuilder withQuality(int quality) {
			this.quality = quality;
			return this;
		}
		public ItemBuilder withSellIn(int sellIn) {
			this.sellIn = sellIn;
			return this;
		}
		public Item build(){
			return new Item(name, sellIn, quality);
		}
	}

	@Test
	public void testNormalItemName() {
		Item[] items = new Item[] { new ItemBuilder().withName("foo").build()};
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
	}

	@Test
	public void testNormalItemUpdateSellIn() {
		Item[] items = new Item[] { new ItemBuilder().withSellIn(10).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(9, app.items[0].sellIn);
	}

	@Test
	public void testNormalItemUpdateQuality() {
		Item[] items = new Item[] { new ItemBuilder().withQuality(20).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(19, app.items[0].quality);
	}
	
	@Test
	public void testNormalItemPassedSellIn() {
		Item[] items = new Item[] { new ItemBuilder().withSellIn(0).withQuality(20).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(18, app.items[0].quality);
	}
	@Test
	public void testNormalItemQualityAlwaysPositive() {
		Item[] items = new Item[] { new ItemBuilder().withSellIn(0).withQuality(0).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(0, app.items[0].quality);
	}
	@Test
	public void testAgedBrieUpdateQuality() {
		Item[] items = new Item[] { new ItemBuilder().withName("Aged Brie").withQuality(20).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(21, app.items[0].quality);
	}
	@Test
	public void testAgedBrieUpdateQualityLess51() {
		Item[] items = new Item[] { new ItemBuilder().withName("Aged Brie").withQuality(50).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertTrue(app.items[0].quality < 51);
	}
	@Test
	public void testSulfurasUpdateQuality() {
		Item[] items = new Item[] { new ItemBuilder().withName("Sulfuras, Hand of Ragnaros").withQuality(80).build() };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(80, app.items[0].quality);
	}

}
