package iqmetrix.takehomechallenge.mik.main;

import iqmetrix.takehomechallenge.mik.logic.Game;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by MIK on 2017-10-14.
 */
public class PokerGameMain {

    static final Logger logger = Logger.getLogger(PokerGameMain.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        logger.info("Game started.");
        //Creating game instance
        Game game = new Game();
        game.startGame();
        logger.info("Game finished.");
    }


}

