package iqmetrix.takehomechallenge.mik.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by MIK on 2017-10-12.
 * Deck of cards
 */
public class Deck {
    //Properties
    /**
     * Cards in the deck
     * */
    private final Stack<Card> cards;

    //Public methods

    /**
     * Construct an instance of Deck of card and shuffle it
     *
     * */
    public Deck(){
        //construct standard deck of cards
        this.cards = new Stack<>();

        for(Rank r: Rank.values()){
            for(Suit s: Suit.values()){
                this.cards.add(new Card(s, r));
            }
        }
        //shuffle the deck
        Collections.shuffle(this.cards);
    }
    /**
     * Get the number of cards in the deck.
     */
    public int size()
    {
        return this.cards.size();
    }

    /**
     * Get whether the deck is empty.
     */
    public boolean empty()
    {
        return this.cards.empty();
    }

    /**
     * Draw the top card from the deck.
     */
    public Card drawTopCardFromDeck()
    {
        return this.cards.pop();
    }
}
