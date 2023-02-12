package com.techreturners;



public class BowlingGame {


    // Store X - / as static chars
    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final char MISS = '-';

    private static final int STRIKE_SCORE = 10;
    private static final int TOTAL_ROUND = 10;
    private static final int TOTAL_ROUND_WITH_MAX_SPARES = 12;

    // The input scores separated into an array of 10 rounds of a Bowling game.
    private String[] scoreCard;


    // ScoreValues : Stores data parsed from input string Scorecard.
    static class ScoreValues {
        int numberOfSpares = 0;
        private int score1;
        private int score2;
        private int spareScore;
        ScoreValues(){
            this.score1=-1;
            this.score2=0;
            this.spareScore=0;
        }

        public int Value1() { return score1; }
        public int Value2() { return score2; }

        public void SetSpare() { score2 = STRIKE_SCORE - score1; numberOfSpares = 1; };
        public void SetStrike() {
            if (numberOfSpares == 1)
                spareScore = STRIKE_SCORE;
            else
                score1 = STRIKE_SCORE;
            numberOfSpares = 2;
        };
        public void SetValue(int value) {
            if (numberOfSpares == 1)
                spareScore = value;
            else if (score1 < 0)
                score1 = value;
            else
                score2 = value;
        }
        public void SetSpareScore(int value) {
            if (spareScore == 0)
                spareScore = value;
        }
        public int totalScore() { return score1+score2+spareScore; }
    }

    private ScoreValues[] scoreValues;

    // Get the extra spare score due to a strike or knocking all the pins down (leading to a spare).
    private int calculateSparesScore(int index) {
        int value = 0;
        switch ( scoreValues[index].numberOfSpares ) {
            case 1:
                value = scoreValues[index + 1].Value1();
                break;
            case 2:
                value = scoreValues[index + 1].Value1();

                if (index == STRIKE_SCORE-1) // last bowl of the 10, 2 spares after
                    value += scoreValues[index + 2].Value1();
                else if ( value == STRIKE_SCORE)
                    value += scoreValues[index + 2].Value1();
                else
                    value += scoreValues[index + 1].Value2();
                break;
        }
        return value;
    }

    private void calculateScore(String substring, int index) {

        for (char c : substring.toCharArray()) {
            switch (c) {
                case STRIKE:
                    scoreValues[index].SetStrike();
                    break;
                case SPARE:
                    scoreValues[index].SetSpare();
                    break;
                case MISS:
                    break;
                default: // A number less than 10 (i.e. not a strike)
                    scoreValues[index].SetValue (c - '0');
            }
        }
    }

    public int totalScore(String scorecard) {
        int totalScore = 0;

        // Split input string and store in String Array ScoreCard.
        scoreCard = scorecard.split(" ", -1);

        // Initialise ScoreValues
        scoreValues = new ScoreValues[TOTAL_ROUND_WITH_MAX_SPARES];
        for(int i = 0; i < scoreValues.length; i++) {
            scoreValues[i]=new ScoreValues();
        }

        // Add up the scores backwards, so any spare scores "ahead" have already been calculated.
        for(int i = scoreCard.length - 1 ; i >= 0; i--) {
            calculateScore(scoreCard[i], i);

            // Ignore the spares at the end (for the total score)
            if (i < TOTAL_ROUND) {
                scoreValues[i].SetSpareScore( calculateSparesScore(i) );
                totalScore += scoreValues[i].totalScore();
            }
        }
        return totalScore;
    }
}
