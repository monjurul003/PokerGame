package iqmetrix.takehomechallenge.mik.entity;

/**
 * Created by MIK on 2017-10-13.
 */
public class Player{
// Properties

        /**
         * The player's display name.
         */
        public final String name;

        /**
         * The player's hand of cards.
         */
        public final PokerHands hand;

        // Public methods
        //******************************
        /**
         * Construct an instance
         * @param name The player's display name.
         */
        public Player(String name, PokerHands hand) {
            this.name = name;
            this.hand = hand;
        }
}
