package com.bankinc.bankinc.common.utils;

public class IdValidator {

    private IdValidator(){}

    public static final int CARD_ID_SIZE = 16;
    public static final int PRODUCT_ID_SIZE = 6;

    public static boolean idSizeValidator(String cardId, int cardIdSize) {
        return cardId.matches("\\d{" + cardIdSize + "}");
    }

}
