package com.gildedrose;

public class QualityUpdater {

    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";
    private static final String SET = "set";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage";
    private static final String SULFURAS = "Sulfuras,";
    private static final String CONJURED = "Conjured ";

    //Find items with exceptional quality rules
    public void updateQualityForItem(Item item) throws Exception {
        switch(item.name.substring(0,9)) {
            case AGED_BRIE:
                updateNonDegradingItemQuality(item);
                break;
            case SULFURAS:
                updateLegendaryItemQuality(item);
                break;
            case BACKSTAGE_PASS:
                updateBackstagePassQuality(item);
                break;
            case CONJURED:
                updateConjuredItemQuality(item);
                break;
            default:
                updateRegularItemQuality(item);
        }
    }

    //Increase quality of items which cannot degrade
    private void updateNonDegradingItemQuality(Item item) throws Exception {
        if (item.sellIn >= 0) {
            adjustQuality(item, INCREASE,1);
        }
        else {
            adjustQuality(item, INCREASE,2);
        }
        makeSureQualityIsInRange(item, 50,0);
    }

    //Set legendary item quality to 80 as it can never be different
    private void updateLegendaryItemQuality(Item item) {
        makeSureQualityIsInRange(item, 80,80);
    }

    //Increase quality of backstage passes as the concert date nears, after the concert quality drops to 0
    private void updateBackstagePassQuality(Item item) throws Exception {
        if (item.sellIn > 10) {
            adjustQuality(item, INCREASE,1);
        }
        if (item.sellIn <= 10 && item.sellIn > 5) {
            adjustQuality(item, INCREASE,2);
        }
        if (item.sellIn <= 5 && item.sellIn >= 0) {
            adjustQuality(item, INCREASE,3);
        }
        if (item.sellIn < 0) {
            adjustQuality(item, SET,0);
        }
        makeSureQualityIsInRange(item, 50,0);
    }

    //Decrease quality of conjured items fast
    private void updateConjuredItemQuality(Item item) throws Exception {
        if (item.sellIn >= 0) {
            adjustQuality(item, DECREASE,2);
        }
        else {
            adjustQuality(item, DECREASE,4);
        }
        makeSureQualityIsInRange(item, 50,0);
    }

    //Decrease quality of item types not specified above
    private void updateRegularItemQuality(Item item) throws Exception {
        if (item.sellIn >= 0) {
            adjustQuality(item, DECREASE,1);
        }
        else {
            adjustQuality(item, DECREASE,2);
        }
        makeSureQualityIsInRange(item, 50,0);
    }

    //Adjust the item quality with previously specified value
    private void adjustQuality(Item item, String adjustment , int value) throws Exception {
        switch (adjustment) {
            case INCREASE:
                item.quality = item.quality + value;
                break;
            case DECREASE:
                item.quality = item.quality - value;
                break;
            case SET:
                item.quality = value;
                break;
            default:
                throw new Exception("Unknown quality adjustment: "+adjustment);
        }
    }

    //Make sure the quality never goes beyond the set boundaries
    private void makeSureQualityIsInRange(Item item, int qualityIsNeverAbove, int qualityIsNeverBelow) {
        if (item.quality > qualityIsNeverAbove) {
            item.quality = qualityIsNeverAbove;
        }
        if (item.quality < qualityIsNeverBelow) {
            item.quality = qualityIsNeverBelow;
        }
    }
}
