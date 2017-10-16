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
//        logger.debug("Sample debug message");
//        logger.info("Sample info message");
//        logger.warn("Sample warn message");
//        logger.error("Sample error message");
//        logger.fatal("Sample fatal message");

        Game game = new Game();
        game.startGame();

    }


}

