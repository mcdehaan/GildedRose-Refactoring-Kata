package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTestHelper {

    public void testAssertion(Item[] items, int expectedSellInChange, int expectedQualityChange) throws Exception {
        //Assert each item in list of items
        for (Item itemUnderTest : items) {
            //Prepare app under test
            GildedRose appUnderTest = new GildedRose(items);

            //Define original and expected sellIn values of item under test
            int originalSellIn = itemUnderTest.sellIn;
            int expectedSellIn = originalSellIn + expectedSellInChange;

            //Define original and expected quality values of item under test
            int originalQuality = itemUnderTest.quality;
            int expectedQuality = originalQuality + expectedQualityChange;

            //Build expected string output to compare with output of app under test
            String expectedResult = itemUnderTest.name+", "+expectedSellIn+", "+expectedQuality;

            //Run app under test
            appUnderTest.updateQuality();

            //Compare expected output to output of app under test
            assertEquals(expectedResult, itemUnderTest.toString());
        }

    }
}
