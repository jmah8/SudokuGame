package test;

import main.Difficulty;
import main.SudokuBoard;
import main.SudokuChecker;
import main.UnsolvedSudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuCheckerTest {
    //this is only for the test that I added after the delegation

    private SudokuChecker sudokuChecker;
    private SudokuBoard sudokuBoard;

    @BeforeEach
    private void setUp() {
        sudokuBoard = new UnsolvedSudokuBoard(Difficulty.EASY);
        sudokuChecker = new SudokuChecker(sudokuBoard);
    }

    @Test
    public void checkValidRowNoParamValid() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(1, 6);
        assertTrue(sudokuChecker.checkValidRow());
    }

    @Test
    public void checkValidRowNoParamInvalid() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(1, 5);
        assertFalse(sudokuChecker.checkValidRow());
    }

    @Test
    public void checkValidRowNoParamCheckingRightRow() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(10, 5);
        assertTrue(sudokuChecker.checkValidRow());
    }
}
