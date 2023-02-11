package com.techreturners;



public class BowlingGame {


    // Store X - / as static chars
    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char MISS = '-';

    private static final int STRIKE_SCORE = 10;
    private static final int TOTAL_ROUND = 10;

    private String[] scoreCard;
    private int[] scoreCardSpares = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] scoreValues = {0,0,0,0,0,0,0,0,0,0,0,0};


    private int GetSparesScore(int index) {
        int value1 = 0;
        int value2 = 0;
        switch ( scoreCardSpares[index] ) {
            case 2:
                value2 = scoreValues[index + 2];
                if ( value2 >= STRIKE_SCORE)
                    value2 = STRIKE_SCORE;
            // fall into case 1
            case 1:
                value1 = scoreValues[index + 2];
                if ( value1 >= STRIKE_SCORE)
                    value1 = STRIKE_SCORE;
        }
        return value1 + value2;
    }

    private int calculateScore(String substring, int index) {
        int score = 0;

        for (char c : substring.toCharArray()) {
            switch (c) {
                case STRIKE:
                    if (index < TOTAL_ROUND)
                        scoreCardSpares[index]  = 2;
                    return STRIKE_SCORE;
                case SPARE:
                    if (index < TOTAL_ROUND)
                        scoreCardSpares[index]  = 1;
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

        scoreCard = scorecard.split(" ");

        for(int i = scoreCard.length - 1 ; i >= 0; i--) {
            scoreValues[i] = calculateScore(scoreCard[i], i);
            scoreValues[i] += GetSparesScore(i);

            // Ignore the spares at the end
            if (i < TOTAL_ROUND)
                totalScore += scoreValues[i];
            System.out.println("totalScore=" + totalScore);
        }

        return totalScore;
    }

}
