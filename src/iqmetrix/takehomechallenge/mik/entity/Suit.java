package iqmetrix.takehomechallenge.mik.entity;

import java.util.HashMap;

/**
 * Created by MIK on 2017-10-12.
 * Possible suits of cards
 */
public enum Suit {
    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);

    public final int suit;
    private static HashMap<Integer, Suit> hMap = new HashMap<>();

    /**
     * Cosruct an instance of Suit
     * @param suit The card suit
     */
    private Suit(int suit){
        this.suit = suit;
    }

    static {

        for (Suit s: Suit.values()) {
            hMap.put(s.suit, s);
        }
    }

    public static Suit valueOf(int suit){
        return hMap.get(suit);
    }
}
