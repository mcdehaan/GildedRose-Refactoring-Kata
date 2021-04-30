package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    GildedRoseTestHelper gildedRoseTestHelper = new GildedRoseTestHelper();

    @Test
    public void lowers_sellIn_date_of_regular_items_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("+3 Strength Belt", 3, 10),
                new Item("Boots of Speed", 3, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -1
        );
    }

    @Test
    public void lowers_quality_of_regular_items_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("+4 Constitution Boots", 1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -1
        );
    }

    @Test
    public void lowers_quality_of_conjured_items_faster_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Conjured HP Pie", 1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -2
        );
    }

    @Test
    public void increases_quality_of_non_degrading_items_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Aged Brie", 1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +1
        );
    }

    @Test
    public void decreases_quality_of_regular_items_twice_as_fast_after_sellIn_date_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Bramble Cloak", -1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -2
        );
    }

    @Test
    public void decreases_quality_of_conjured_items_twice_as_fast_after_sellIn_date_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Conjured Tenacity Cookie", -1, 10),
                new Item("Conjured Agile Tea", -1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -4
        );
    }

    @Test
    public void increases_quality_of_non_degrading_items_twice_as_fast_after_sellIn_date_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Aged Brie", -1, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +2
        );
    }

    @Test
    public void increases_quality_of_backstage_passes_each_day() throws Exception {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +1
        );
    }

    @Test
    public void increases_quality_of_backstage_passes_by_2_from_10_days_before_concert() throws Exception {
        Item[] items = new Item[] {
                new Item("Backstage passes to a Royal Jelly concert", 10, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +2
        );
    }

    @Test
    public void increases_quality_of_backstage_passes_by_3_from_5_days_before_concert() throws Exception {
        Item[] items = new Item[] {
                new Item("Backstage passes to a Medieval Tree concert", 5, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +3
        );
    }

    @Test
    public void quality_cannot_be_negative() throws Exception {
        Item[] items = new Item[] {
                new Item("Necklace of Fireballs", 10, 0)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                0
        );
    }

    @Test
    public void quality_cannot_be_above_50_for_non_legendary_and_non_degradable_items() throws Exception {
        Item[] items = new Item[] {
                new Item("Aged Brie", 10, 50)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                0
        );
    }

    @Test
    public void quality_cannot_be_above_50_for_backstage_passes() throws Exception {
        Item[] items = new Item[] {
                new Item("Backstage passes to a Fine Adventurer concert", 10, 50)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                0
        );
    }

    @Test
    public void quality_cannot_be_above_50_for_non_legendary_and_non_degradable_items_past_inSell_date() throws Exception {
        Item[] items = new Item[] {
                new Item("Aged Brie", -1, 49)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                +1
        );
    }

    @Test
    public void quality_is_0_for_backstage_passes_past_sellIn_date() throws Exception {
        Item[] items = new Item[] {
                new Item("Backstage passes to a Royal Innkeeper concert", 0, 50)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                -1,
                -50
        );
    }

    @Test
    public void sellIn_date_does_not_change_for_legendary_items() throws Exception {
        Item[] items = new Item[] {
                new Item("Sulfuras, Hand of Ragnaros", 1, 80)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                0,
                0
        );
    }

    @Test
    public void quality_is_always_80_for_legendary_items() throws Exception {
        Item[] items = new Item[] {
                new Item("Sulfuras, Eye of", 3, 80)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                0,
                0
        );
    }

    @Test
    public void quality_is_always_80_for_legendary_items_past_sellIn_date() throws Exception {
        Item[] items = new Item[] {
                new Item("Sulfuras, Hammer", -3, 80)
        };
        gildedRoseTestHelper.testAssertion(
                items,
                0,
                0
        );
    }
}
