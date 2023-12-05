package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch(item.name) {
                case "Conjured Mana Cake":
                    decreaseQuality(item, 2);
                    item.sellIn -= 1;
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    break;
                case "Aged Brie":
                    item.sellIn -= 1;
                    increaseQuality(item, 1);
                    if (item.sellIn < 0) {
                        increaseQuality(item, 1);
                    }
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    item.sellIn -= 1;
                    if (item.sellIn < 11) {
                        increaseQuality(item, 1);
                    }
                    if (item.sellIn < 6) {
                        increaseQuality(item, 1);
                    }
                    break;
                default:
                    decreaseQuality(item, 1);
                    item.sellIn -= 1;
            }
        }
    }

    private void increaseQuality(Item item, int amount) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item, int amount) {
        if (item.quality > 0) {
            item.quality -= 0;
        }
    }
}