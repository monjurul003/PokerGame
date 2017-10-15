package iqmetrix.takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-12.
 * Different poker hands
 */
public enum PokerHandsType {
    //constants

    /**
     * Five cards of the same suit (if not consecutive).
     */
    FLUSH("flush"),

    /**
     * Three cards of the same rank.
     */
    THREEOFAKIND("three of a kind"),
    /**
     * Two cards of the same rank.
     */
    ONEPAIR("one pair"),

    /**
     * Highest card value.
     */
    HIGHCARD("high card");
    /**
     * The hand's display name.
     */
    public final String name;

    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param name The hand's display name.
     */
    PokerHandsType(String name) {
        this.name = name;
    }
}
