package takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-12.
 * The card class
 */
public class Card {
    //Properties

    /**
     * Suit of the card
     */
    public final Suit suit;
    /**
     * Rank of the card
     */
    public final Rank rank;


    //Public methods

    /**
     * Construct an instance.
     *
     * @param suit The card suit
     * @param rank The card rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
}
