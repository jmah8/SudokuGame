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
        assertEquals(5, sudokuBoard.getNumberAtIndex(0));
        assertEquals(6, sudokuBoard.getNumberAtIndex(1));
    }

    @Test
    public void checkValidRowNoParamInvalid() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(1, 5);
        assertFalse(sudokuChecker.checkValidRow());
        assertEquals(5, sudokuBoard.getNumberAtIndex(0));
        assertEquals(5, sudokuBoard.getNumberAtIndex(1));
    }

    @Test
    public void checkValidRowNoParamCheckingRightRow() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(10, 5);
        assertTrue(sudokuChecker.checkValidRow());
        assertEquals(5, sudokuBoard.getNumberAtIndex(0));
        assertEquals(5, sudokuBoard.getNumberAtIndex(10));
    }

    @Test
    public void checkValidColumnNoParamValid() {
        sudokuBoard.addNumber(1,3);
        sudokuBoard.addNumber(64, 5);
        assertTrue(sudokuChecker.checkValidColumn());
        assertEquals(3, sudokuBoard.getNumberAtIndex(1));
        assertEquals(5, sudokuBoard.getNumberAtIndex(64));
    }

    @Test
    public void checkValidColumnNoParamInvalid() {
        sudokuBoard.addNumber(1,3);
        sudokuBoard.addNumber(64, 3);
        assertFalse(sudokuChecker.checkValidColumn());
        assertEquals(3, sudokuBoard.getNumberAtIndex(1));
        assertEquals(3, sudokuBoard.getNumberAtIndex(64));
    }

    @Test
    public void checkValidColumnNoParamCheckingRightColumn() {
        sudokuBoard.addNumber(1,3);
        sudokuBoard.addNumber(20, 3);
        assertTrue(sudokuChecker.checkValidColumn());
        assertEquals(3, sudokuBoard.getNumberAtIndex(1));
        assertEquals(3, sudokuBoard.getNumberAtIndex(20));
    }

    @Test
    public void checkValidGridNoParamValid() {
        sudokuBoard.addNumber(33, 5);
        sudokuBoard.addNumber(43, 6);
        sudokuBoard.addNumber(51, 7);
        assertTrue(sudokuChecker.checkValidGrid());
        assertEquals(5, sudokuBoard.getNumberAtIndex(33));
        assertEquals(6, sudokuBoard.getNumberAtIndex(43));
        assertEquals(7, sudokuBoard.getNumberAtIndex(51));
    }

    @Test
    public void checkValidGridNoParamInvalid() {
        sudokuBoard.addNumber(33, 5);
        sudokuBoard.addNumber(43, 5);
        sudokuBoard.addNumber(51, 7);
        assertFalse(sudokuChecker.checkValidGrid());
        assertEquals(5, sudokuBoard.getNumberAtIndex(33));
        assertEquals(5, sudokuBoard.getNumberAtIndex(43));
        assertEquals(7, sudokuBoard.getNumberAtIndex(51));
    }

    @Test
    public void checkValidGridNoParamCheckingRightGrid() {
        sudokuBoard.addNumber(33, 5);
        sudokuBoard.addNumber(43, 6);
        sudokuBoard.addNumber(61, 5);
        assertTrue(sudokuChecker.checkValidGrid());
        assertEquals(5, sudokuBoard.getNumberAtIndex(33));
        assertEquals(6, sudokuBoard.getNumberAtIndex(43));
        assertEquals(5, sudokuBoard.getNumberAtIndex(61));
    }
}
