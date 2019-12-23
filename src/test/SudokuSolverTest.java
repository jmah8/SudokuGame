package test;

import main.Difficulty;
import main.UnsolvedSudokuBoard;
import main.SudokuSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {
    private SudokuSolver sudokuSolver;
    private UnsolvedSudokuBoard sudokuBoard;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new UnsolvedSudokuBoard(Difficulty.EASY);
        sudokuSolver = new SudokuSolver(sudokuBoard);
    }

    @Test
    public void checkChangeInSudokuBoardSaved() {
        sudokuBoard.addNumber(0, 5);
        assertEquals(1, sudokuSolver.findNextEmptyIndex());
    }

    @Test
    public void findNextEmptyIndexAtBeginning() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(1, 5);
        sudokuBoard.addNumber(2, 5);
        sudokuBoard.addNumber(3, 5);
        assertEquals(4, sudokuSolver.findNextEmptyIndex());
    }

    @Test
    public void findNextEmptyIndexInbetween() {
        sudokuBoard.addNumber(0, 5);
        sudokuBoard.addNumber(1, 5);
        sudokuBoard.addNumber(2, 5);
        sudokuBoard.addNumber(4, 5);
        assertEquals(3, sudokuSolver.findNextEmptyIndex());
    }

    @Test
    public void isCompleteCompleteTest() {
        for (int i = 0; i < 81; i++) {
            sudokuBoard.addNumber(i, 1);
        }
        assertTrue(sudokuSolver.isFilled());
    }

    @Test
    public void isCompleteIncompleteTest() {
        for (int i = 0; i < 80; i++) {
            sudokuBoard.addNumber(i, 1);
        }
        assertFalse(sudokuSolver.isFilled());
    }

    @Test
    public void fillSudokuBoardTestFailSinceFillNotCorrect() {
        sudokuSolver.fillSudokuBoard();
        boolean filled = true;
        for (int i = 0; i < 81; i++) {
            if (sudokuBoard.getNumberAtIndex(i) == 0) {
                filled = false;
                break;
            }
        }
        assertFalse(filled);
    }

    @Test
    public void changeSudokuBoardTest() {
        UnsolvedSudokuBoard sudokuBoard1 = new UnsolvedSudokuBoard(Difficulty.EASY);
        sudokuBoard1.addNumber(0, 5);
        sudokuSolver.changeSudokuBoard(sudokuBoard1);
        assertEquals(1, sudokuSolver.findNextEmptyIndex());
    }
}
