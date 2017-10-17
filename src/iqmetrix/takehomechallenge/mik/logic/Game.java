package iqmetrix.takehomechallenge.mik.logic;

import iqmetrix.takehomechallenge.mik.entity.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MIK on 2017-10-14.
 */
public class Game {
    //Private properties
    static final Logger logger = Logger.getLogger(Game.class);

    private ArrayList<Player> playerList;

    /**
     * Read a file as a String array
     *
     * @param fileNameWithPath is a string containing the file name and path
     */
    private String[] getFileAsStringArray(String fileNameWithPath) {
        logger.debug("Reading file - " + fileNameWithPath);
        //read input from the input folder as string
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileNameWithPath))) {
            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //convert ArrayList to a string array
        String[] inputString = list.toArray(new String[0]);
        logger.debug("Reading file done. ");
        return inputString;
    }

    /**
     * Get rank from input string with suit
     *
     * @param s is String containing the rank with suit
     */
    private Rank getRank(String s) {
        if (s == null || s.length() == 0) {
            logger.debug("Input string is null! Rank can not be null.");
            System.exit(0);
        }
        char ch = s.charAt(0);
        int i = 0;
        if (ch == 'A' || ch == 'a') {
            i = 14;
        } else if (ch == 'K' || ch == 'k') {
            i = 13;
        } else if (ch == 'Q' || ch == 'q') {
            i = 12;
        } else if (ch == 'J' || ch == 'j') {
            i = 11;
        } else {
            i = Integer.parseInt(s.substring(0, s.length() - 1));
        }
        return Rank.valueOf(i);
    }

    /**
     * Get suit from input string with suit
     *
     * @param s is String containing the rank with suit
     */
    private Suit getSuit(String s) {
        if (s == null || s.length() == 0) {
            logger.debug("Input string is null!");
            System.exit(0);
        }
        char ch = s.charAt(s.length() - 1);
        int i = 0;
        if (ch == 'C' || ch == 'c') {
            i = 1;
        } else if (ch == 'D' || ch == 'd') {
            i = 2;
        } else if (ch == 'H' || ch == 'h') {
            i = 3;
        } else if (ch == 'S' || ch == 's') {
            i = 4;
        } else {
            System.out.println("Invalid suit- " + ch);
        }
        return Suit.valueOf(i);
    }

    /**
     * Find whether input contains duplicate card or player (catch ill formatted input)
     *
     * @param inputArray is a string array of input
     */
    private boolean findDuplicateEntry(String[] inputArray) {
        List<String> list = new ArrayList<>();
        for (String str : inputArray) {
            String[] temp = str.split(",");
            for (String s : temp) {
                list.add(s);
            }
        }
        HashSet<String> hSet = new HashSet<>();
        for (String str : list) {
            if (hSet.add(str) == false) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create and populate player List
     *
     * @param inputStringArray is the input file as string array
     */

    private void populatePlayers(String[] inputStringArray) {
        for (String inputString : inputStringArray) {
            String[] temp = inputString.split(",");
            if (temp.length != 6) {
                System.out.println("Player should have 5 card in his hand! Check log for details. Quitting game... ");
                logger.fatal("Ill formatted input. Input string should contain player name and 5 cards. ");
                System.exit(0);
            }
            PokerHands hands = new PokerHands();
            for (int i = 1; i < temp.length; i++) {
                Card card = new Card(this.getSuit(temp[i].replaceAll("[\\s]", "")), this.getRank(temp[i].replaceAll("[\\s]", "")));
                hands.add(card);
            }
            Player newPlayer = new Player(temp[0], hands);

            this.playerList.add(newPlayer);
            logger.info("Player " + temp[0] + " added ");
        }
        logger.debug("Populating player done.");

    }

    /**
     * Initialize the game.
     */
    //initialize game
    private void init() {
        logger.debug("Initializing the game.");
        //get application directory
        String appDir = System.getProperty("user.dir");
        //File name and path in the input directory
        String fileNameWithPath = appDir + File.separator + "input" + File.separator + "input.txt";
        logger.debug("File path and name-- " + fileNameWithPath);
        String[] inputStringArray = this.getFileAsStringArray(fileNameWithPath);
        //check maximum player in the game
        if (inputStringArray.length > 10 || inputStringArray.length < 2) {
            System.out.println("Two to ten players can play the game at a time! Quitting game...");
            logger.fatal("Application stopped as input contains more than 10 players information or less than two players information.");
            System.exit(0);
        }
        //check duplicate card and player in input file
        if (findDuplicateEntry(inputStringArray)) {
            System.out.println("Two player name can not be same or two player can not have same card! Quitting game..");
            logger.fatal("Input string contains duplicate entry or player name is same!");
            System.exit(0);
        }
        this.populatePlayers(inputStringArray);
        logger.debug("Initialization done.");
    }

    /**
     * Break tie between two player using high card rule
     *
     * @param firstPlayerIndex  index of player 1
     * @param secondPlayerIndex index of player 2
     */
    private int breakTie(int firstPlayerIndex, int secondPlayerIndex) {
        Card[] firstHand = this.playerList.get(firstPlayerIndex).hand.peek();
        Card[] secondHand = this.playerList.get(secondPlayerIndex).hand.peek();

        for (int i = 0; i < firstHand.length; i++) {
            logger.info("compare between - " + firstHand[i].rank.value + " and " + secondHand[i].rank.value);
            if (firstHand[i].rank.value > secondHand[i].rank.value) {
                logger.info("first hand have high rank " + firstHand[i].rank.value);
                return firstPlayerIndex;
            } else if (firstHand[i].rank.value < secondHand[i].rank.value) {
                logger.info("second hand have high rank " + secondHand[i].rank.value);
                return secondPlayerIndex;
            } else {
                logger.info("Both have same rank");
            }
        }
        return -1;
    }

    /**
     * Find the winner
     */

    private void findWinner() {
        ArrayList<String> tieList = new ArrayList<>();
        HashSet<String> playerNameInTieList = new HashSet<>();
        int max = 0;
        for (int i = 1; i < this.playerList.size(); i++) {
            logger.info("Compare between " + this.playerList.get(max).name + " and " + this.playerList.get(i).name);
            if (this.playerList.get(max).hand.getHandType().value < this.playerList.get(i).hand.getHandType().value) {
                max = i;
            }
            if (this.playerList.get(max).hand.getHandType().value == this.playerList.get(i).hand.getHandType().value) {

                if (playerNameInTieList.contains(this.playerList.get(max).name) == false) {
                    tieList.add(this.playerList.get(max).name);
                    playerNameInTieList.add(this.playerList.get(max).name);
                }
                if (playerNameInTieList.contains(this.playerList.get(i).name) == false) {
                    tieList.add(this.playerList.get(i).name);
                    playerNameInTieList.add(this.playerList.get(i).name);
                }
                max = this.breakTie(max, i);
            }
        }
        if (tieList.size() > 1) {
            System.out.print("There was a tie! Among ");
            for (int i = 0; i < tieList.size(); i++) {
                System.out.print(tieList.get(i));
                if (i < tieList.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
            System.out.print("But using tie breaking rule, ");
        }
        System.out.println("The winner is " + this.playerList.get(max).name);
    }


    //Public methods
    public Game() {
        this.playerList = new ArrayList<>();
    }

    /**
     * Start the game.
     */
    public void startGame() {
        this.init();
        this.findWinner();
    }

}
