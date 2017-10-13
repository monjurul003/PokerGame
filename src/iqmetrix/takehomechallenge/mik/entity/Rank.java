package iqmetrix.takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-12.
 * Possible card ranks
 */
public enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(22),
    KING(13),
    ACE(14);

    //Properties
    /**
     * The value of card
     */
    public final int value;

    //Public methods
    /**
     * Construct an instance of a Rank
     * @param value The value of a card when choosing a high card.
     */
    Rank(int value){
        this.value = value;
    }


    /**
     * Get whether given Ranks are consecutive in the given order or not
     * @param ranks The ranks to check
     */

    public static boolean areConsecutive(Rank[] ranks){

        for (int i = 0; i < ranks.length-1; i++) {
            if (Math.abs(ranks[i].value - ranks[i+1].value) !=1){
                return false;
            }
        }
        return true;
    }


}
