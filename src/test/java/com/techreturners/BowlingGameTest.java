package com.techreturners;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

/*
    @Test
    public void CheckBowlingGameScoreAllStrikes() {
        BowlingGame bowlingGame = new BowlingGame();

        assertEquals( 300, bowlingGame.totalScore("X X X X X X X X X X X X") );
    }

    @Test
    public void CheckBowlingGameScoreAGoodScore() {
        BowlingGame bowlingGame = new BowlingGame();

        assertEquals( 169, bowlingGame.totalScore("5/ 45  8/  X 0/ X 62 X 4/ X X") );
    }
*/


    @Test
    public void CheckBowlingGameScoreNoSpares() {
        BowlingGame bowlingGame = new BowlingGame();

        assertEquals( 62, bowlingGame.totalScore("53 45  80  02 11 62 43 44 27 10") );
    }

    @Test
    public void CheckBowlingGameScoreWithOneSpare() {
        BowlingGame bowlingGame = new BowlingGame();

        assertEquals( 27, bowlingGame.totalScore("11 11 11 11 11 11 11 01 01 4/ 01") );
    }
}
