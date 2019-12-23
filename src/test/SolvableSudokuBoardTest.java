package test;

import main.Difficulty;
import main.SolvableSudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolvableSudokuBoardTest {
    private SolvableSudokuBoard ssb;

    @BeforeEach
    public void setUp() {
        ssb = new SolvableSudokuBoard(Difficulty.EASY);
    }

    @Test
    public void selectDifficultyTestEasy() {
        ssb.selectDifficulty(Difficulty.EASY);
        Integer range = ssb.selectDifficultyLevel();
        boolean withinRange = (range >= 34 && range <= 38);
        assertTrue(withinRange);
    }

    @Test
    public void selectDifficultyTestMedium() {
        ssb.selectDifficulty(Difficulty.MEDIUM);
        Integer range = ssb.selectDifficultyLevel();
        boolean withinRange = (range >= 52 && range <= 56);
        assertTrue(withinRange);
    }

    @Test
    public void selectDifficultyTestHard() {
        ssb.selectDifficulty(Difficulty.HARD);
        Integer range = ssb.selectDifficultyLevel();
        boolean withinRange = (range >= 70 && range <= 74);
        assertTrue(withinRange);
    }

    @Test
    public void selectDifficultyTestFail() {
        ssb.selectDifficulty(Difficulty.MEDIUM);
        Integer range = ssb.selectDifficultyLevel();
        boolean withinRange = (range >= 70 && range <= 74);
        assertFalse(withinRange);
    }

    @Test
    public void generateNewSolvableBoardTest() {
        ssb.generateNewSolvableBoard();
        assertTrue(ssb.getSudokuSolver().isFilled());
    }

    @Test
    public void removeRandomNumberEasy() {
        ssb.selectDifficulty(Difficulty.EASY);
        generateAndRemoveNumbersFromBoard();
        int emptyIndexes = 0;
        for (int i = 0; i < 81; i++) {
            if (ssb.getSudokuBoard().getNumberAtIndex(i) == 0) {
                emptyIndexes++;
            }
        }
        boolean withinRange = (emptyIndexes >= 34 && emptyIndexes <= 38);
        assertTrue(withinRange);
    }

    @Test
    public void removeRandomNumberMedium() {
        ssb.selectDifficulty(Difficulty.MEDIUM);
        generateAndRemoveNumbersFromBoard();
        int emptyIndexes = 0;
        for (int i = 0; i < 81; i++) {
            if (ssb.getSudokuBoard().getNumberAtIndex(i) == 0) {
                emptyIndexes++;
            }
        }
        boolean withinRange = (emptyIndexes >= 52 && emptyIndexes <= 56);
        assertTrue(withinRange);
    }

    @Test
    public void removeRandomNumberHard() {
        ssb.selectDifficulty(Difficulty.HARD);
        generateAndRemoveNumbersFromBoard();
        int emptyIndexes = 0;
        for (int i = 0; i < 81; i++) {
            if (ssb.getSudokuBoard().getNumberAtIndex(i) == 0) {
                emptyIndexes++;
            }
        }
        boolean withinRange = (emptyIndexes >= 70 && emptyIndexes <= 74);
        assertTrue(withinRange);
    }

    private void generateAndRemoveNumbersFromBoard() {
        ssb.generateNewSolvableBoard();
        ssb.removeRandomNumbers();
    }
}
