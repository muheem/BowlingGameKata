package com.techreturners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    @Test
    public void CheckBowlingGameScoreNoSpares() {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(62, bowlingGame.totalScore("53 45 80 02 11 62 43 44 27 10"));
    }

    @Test
    public void CheckBowlingGameScoreWithOneSpare() {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(27, bowlingGame.totalScore("11 11 11 11 11 11 11 01 01 4/ 1"));
    }

    @Test
    public void CheckBowlingGameScoreAllStrikes() {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(300, bowlingGame.totalScore("X X X X X X X X X X X X"));
    }


    @Test
    public void CheckBowlingGameScoreAGoodScore() {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(161, bowlingGame.totalScore("5/ 45 8/ X 0/ X 62 X 4/ X 1 1"));
    }

    @Test
    public void CheckBowlingGameScoreAllSpares() {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(155, bowlingGame.totalScore("1/ 2/ 3/ 4/ 5/ 6/ 7/ 8/ 9/ 1/ X"));
    }



    @ParameterizedTest
    @CsvFileSource(resources="/BowlingGameTest.csv", numLinesToSkip = 1)
    public void CheckBowlingGameUsingCSVFile(String bowlingScore, int expected) {
        BowlingGame bowlingGame = new BowlingGame();
        assertEquals(expected, bowlingGame.totalScore(bowlingScore));
    }


}