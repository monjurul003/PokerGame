package iqmetrix.takehomechallenge.mik.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by MIK on 2017-10-13.
 * Player's hand of card
 */
public class PokerHands {
    //Private properties
    /**
     * The cards in the hand.
     */
    private final ArrayList<Card> cardsInHand = new ArrayList<>();

    /**
     * The cached hand data.
     */
    private PokerHandData data;


    /**
     * Add a card to the player's hand.
     *
     * @param card The card to add.
     */
    public void add(Card card) {
        this.cardsInHand.add(new Card(card.suit, card.rank));
        this.sort();
        this.data = null;
    }

    /**
     * Get a list of cards in the hand.
     */
    public Card[] peek() {
        return cardsInHand.toArray(new Card[0]);
    }


    /**
     * Get the type of the player's hand (e.g. royal flush).
     */
    public PokerHandsType getHandType() {
        // none possible
        if (this.cardsInHand.size() < 5) {
            return PokerHandsType.HIGHCARD;
        }
        // get data
        PokerHandData data = this.getMetadata();
        HashSet<Rank> ranks = new HashSet<>(Arrays.asList(data.ranks));

        // get hand type

        if (data.totalSuitsInhand == 1) {
            return PokerHandsType.FLUSH;
        }
        if (data.rankCounts[0] == 3) {
            return PokerHandsType.THREEOFAKIND;
        }
        if (data.rankCounts[0] == 2) {
            return PokerHandsType.ONEPAIR;
        } else {
            return PokerHandsType.HIGHCARD;
        }
    }

    // Private methods
    //******************************

    /**
     * Sort the cards in the hand.
     */
    private void sort() {
        this.cardsInHand.sort((a, b) -> b.rank.value - a.rank.value); // sort cards by higher to lower value
    }

    /**
     * Get metadata about the hand.
     */
    private PokerHandData getMetadata() {
        if (this.data == null) {
            this.data = new PokerHandData(this.cardsInHand.toArray(new Card[0]));
        }
        return this.data;
    }
}
