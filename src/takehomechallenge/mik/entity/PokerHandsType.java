package takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-12.
 * Different poker hands (arbitrary value used)
 */
public enum PokerHandsType {
    //constants

    /**
     * Five cards of the same suit (if not consecutive).
     */
    FLUSH(50, "flush"),

    /**
     * Three cards of the same rank.
     */
    THREEOFAKIND(40, "three of a kind"),
    /**
     * Two cards of the same rank.
     */
    ONEPAIR(30, "one pair"),

    /**
     * Highest card value.
     */
    HIGHCARD(20, "high card");
    //Private properties
    /**
     * An arbitrary value used to compare hands, where higher values win.
     */
    public final int value;

    /**
     * The hand's display name.
     */
    public final String name;

    // Public methods
    //******************************

    /**
     * Construct an instance.
     *
     * @param value An arbitrary value used to compare hands, where higher values win.
     * @param name  The hand's display name.
     */
    PokerHandsType(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
