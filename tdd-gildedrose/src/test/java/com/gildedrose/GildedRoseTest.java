package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    public void conjuredManaCake_degrades_by_2_per_day() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 4, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    @Test
    public void sulfurasHandOfRagnaros_never_degrades() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 4, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

}
