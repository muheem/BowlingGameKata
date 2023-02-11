package com.techreturners;



public class BowlingGame {


    // Store X - / as static chars
    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char MISS = '-';

    private static final int STRIKE_SCORE = 10;
    private static final int TOTAL_ROUND = 10;

    private String[] scoreCard;
    private int[] scoreValues = {0,0,0,0,0,0,0,0,0,0,0,0};

    int calculateScore(String substring, int spares) {
        int score = 0;

        for (char c : substring.toCharArray()) {
            switch (c) {
                case STRIKE:
                    //score = STRIKE_SCORE;
                    spares = 2;
                    return STRIKE_SCORE;
                case SPARE:
                    spares = 1;
                    return STRIKE_SCORE;
                case MISS:
                    break;
                default: // A number less than 10 (which would be a strike)
                    score += c - '0';
            }

        }
        return score;
    }

    public int totalScore(String scorecard) {

        int totalScore = 0;
        int spares = 0;

        scoreCard = scorecard.split(" ");
        System.out.println("len=" + scoreCard.length);

        int currentScore = 0;
        //for (String val: scoreCard) {

        for(int i = scoreCard.length - 1 ; i >= 0; i--) {

            scoreValues[i] = calculateScore(scoreCard[i], spares);

            // if spares = 1
            if (spares == 2) {
                scoreValues[i] += scoreValues[i] + scoreValues[i + 2];
            }
            if (spares > 0) {
                scoreValues[i] += scoreValues[i] + scoreValues[i + 1];
            }
            totalScore += scoreValues[i];
        }
            //System.out.println(val);


        // Calculate score

        // PROBLEM OF THE SPARES



        // Add up scores and return value

        // Convert

        return totalScore;
    }

}
