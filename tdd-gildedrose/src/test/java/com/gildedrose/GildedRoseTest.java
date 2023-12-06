package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
    @Test
    public void agedBrieIncreasesInQualityBy1UntilSellInDate() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    @Test
    public void agedBrieNeverExceedsQuality50() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    @ParameterizedTest
    @MethodSource("provideValuesForBackstagePasses")
    public void itHandlesBackstagePasses(
            int expectedSellIn, int expectedQuality, int initialSellIn, int initialQuality, String message) {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert",
                        initialSellIn, initialQuality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality, message);
    }

    private static Stream<Arguments> provideValuesForBackstagePasses() {
        return Stream.of(
                Arguments.of(11, 6, 12, 5, "More than 10 days out"),
                Arguments.of(9, 7, 10, 5, "10 days out"),
                Arguments.of(4, 8, 5, 5, "5 days out"),
                Arguments.of(0, 0, 0, 9, "After the concert")
        );
    }

}
