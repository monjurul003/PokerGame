package iqmetrix.takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-12.
 */
public enum Suit {
    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);

    private final int suit;

    /**
     * Cosruct an instance of Suit
     * @param suit The card suit
     */
    private Suit(int suit){

        this.suit = suit;
    }
}
