package iqmetrix.takehomechallenge.mik.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MIK on 2017-10-13.
 * Calculate metrics and data for a poker hand of cards
 */
public class PokerHandData {
    static final Logger logger = Logger.getLogger(PokerHandData.class);
    //Properties
    /**
     * The ranks in the hand.
     */
    public final Rank[] ranks;
    /**
     * The suits in the hand.
     */
    public final Suit[] suits;
    /**
     * The number of unique ranks.
     */
    public final int totalRanksInHand;
    /**
     * The number of unique suits.
     */
    public final int totalSuitsInhand;
    /**
     * The number of cards in each distinct rank.
     */
    public final int[] rankCounts;


    // Public methods

    /**
     * Construct an instance.
     *
     * @param cards The cards to analyse.
     */
    public PokerHandData(Card[] cards) {

        //get rank and suit information extracted
        HashMap<Rank, Integer> rankMap = new HashMap<>();
        HashMap<Suit, Integer> suitMap = new HashMap<>();
        logger.debug("Sorting cards in rank and suit.");
        for (Card c : cards) {
            rankMap.put(c.rank, rankMap.containsKey(c.rank) ? rankMap.get(c.rank) + 1 : 1);
            suitMap.put(c.suit, suitMap.containsKey(c.suit) ? suitMap.get(c.suit) + 1 : 1);
        }

        // save data
        {
            List<Rank> orderedRanks = new ArrayList<>(rankMap.keySet());
            orderedRanks.sort((a, b) -> a.value - b.value); // sort by ascending value
            this.ranks = orderedRanks.toArray(new Rank[0]);
        }
        {
            List<Suit> orderedSuits = new ArrayList<>(suitMap.keySet());
            orderedSuits.sort((a, b) -> a.suit - b.suit); // sort by ascending value for consistent order
            this.suits = orderedSuits.toArray(new Suit[0]);
        }

        // save total ranks and suit metrics
        this.totalRanksInHand = rankMap.size();
        this.totalSuitsInhand = suitMap.size();


        // save rank counts
        {
            List<Integer> values = new ArrayList<>(rankMap.values());
            values.sort((a, b) -> b - a); // sort in descending order

            int size = values.size();
            this.rankCounts = new int[size];
            for (int i = 0; i < size; i++) {
                this.rankCounts[i] = values.get(i);
            }
        }

    }

}
