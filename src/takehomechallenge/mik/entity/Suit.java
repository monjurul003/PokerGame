package takehomechallenge.mik.entity;

import java.util.HashMap;

/**
 * Created by MIK on 2017-10-12.
 * Possible suits of cards
 */
public enum Suit {
    C(1),
    D(2),
    H(3),
    S(4);

    public final int suit;
    //using hash map to map int to enum
    private static HashMap<Integer, Suit> hMap = new HashMap<>();

    /**
     * Cosruct an instance of Suit
     * @param suit The card suit
     */
    Suit(int suit) {
        this.suit = suit;
    }

    static {
        for (Suit s : Suit.values()) {
            hMap.put(s.suit, s);
        }
    }

    public static Suit valueOf(int suit) {
        return hMap.get(suit);
    }
}
