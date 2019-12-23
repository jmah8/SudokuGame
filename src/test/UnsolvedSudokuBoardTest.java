package test;

import main.Difficulty;
import main.UnsolvedSudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnsolvedSudokuBoardTest {
    private UnsolvedSudokuBoard sb;

    @BeforeEach
    public void setUp() {
        sb = new UnsolvedSudokuBoard(Difficulty.EASY);
    }

    @Test
    public void sudokuBoardTest() {
        assertEquals(81, sb.getBoard().sizeOfBoard());
        assertTrue(sb.getBoard().containsNumber(0));
        assertFalse(sb.getBoard().containsNumber(1));
        assertFalse(sb.getBoard().containsNumber(2));
        assertFalse(sb.getBoard().containsNumber(3));
        assertFalse(sb.getBoard().containsNumber(4));
        assertFalse(sb.getBoard().containsNumber(5));
        assertFalse(sb.getBoard().containsNumber(6));
        assertFalse(sb.getBoard().containsNumber(7));
        assertFalse(sb.getBoard().containsNumber(8));
        assertFalse(sb.getBoard().containsNumber(9));
    }

    @Test
    public void addNumberTest() {
        sb.addNumber(60, 8);
        sb.addNumber(20, 7);
        assertEquals(8, sb.getBoard().getNumberAtIndex(60));
        assertEquals(7, sb.getBoard().getNumberAtIndex(20));
        assertEquals(81, sb.getBoard().sizeOfBoard());
    }

    @Test
    public void checkValidIndexValid() {
        assertTrue(sb.checkValidIndex(50));
    }

    @Test
    public void checkValidIndexInvalid() {
        sb.addNumber(50, 5);
        assertFalse(sb.checkValidIndex(50));
    }

    @Test
    public void checkValidRowTestValidSameRow() {
        sb.addNumber(3, 5);
        sb.addNumber(5, 8);
        sb.addNumber(1, 2);
        assertTrue(sb.checkValidRow(4, 6));
    }

    @Test
    public void checkValidRowTestValidDifferentRow() {
        sb.addNumber(3, 5);
        sb.addNumber(5, 8);
        sb.addNumber(1, 2);
        assertTrue(sb.checkValidRow(30, 5));
    }

    @Test
    public void checkValidRowTestInvalid() {
        sb.addNumber(1, 2);
        sb.addNumber(3, 5);
        sb.addNumber(5, 8);
        assertFalse(sb.checkValidRow(8, 8));
    }

    @Test
    public void checkValidRowTestIfCheckingCorrectRow() {
        sb.addNumber(48, 5);
        sb.addNumber(53, 8);
        sb.addNumber(46, 2);
        assertTrue(sb.checkValidRow(22, 8));
    }

    @Test
    public void checkValidColumnValidSameColumn() {
        sb.addNumber(1, 8);
        sb.addNumber(37, 9);
        assertTrue(sb.checkValidColumn(73, 2));
    }

    @Test
    public void checkValidColumnValidDifferentColumn() {
        sb.addNumber(1, 8);
        sb.addNumber(37, 9);
        assertTrue(sb.checkValidColumn(6, 8));
    }

    @Test
    public void checkValidColumnInvalid() {
        sb.addNumber(24, 8);
        sb.addNumber(42, 1);
        assertFalse(sb.checkValidColumn(69, 8));
    }

    @Test
    public void checkValidColumnTestIfCheckingCorrectColumn() {
        sb.addNumber(17, 2);
        sb.addNumber(35, 8);
        assertTrue(sb.checkValidColumn(21, 8));
        assertTrue(sb.checkValidColumn(77, 2));
    }

    @Test
    public void checkValidGridValid() {
        sb.addNumber(32, 8);
        sb.addNumber(44, 2);
        assertTrue(sb.checkValidGrid(42, 5));
    }

    @Test
    public void checkValidGridInvalid() {
        sb.addNumber(57, 1);
        sb.addNumber(76, 5);
        assertFalse(sb.checkValidGrid(66, 5));
    }

    @Test
    public void checkValidGridCheckForRightGrid() {
        sb.addNumber(30, 2);
        sb.addNumber(49, 5);
        assertTrue(sb.checkValidGrid(58, 5));
    }

    @Test
    public void checkValidInsertionValid() {
        sb.addNumber(57, 8);
        sb.addNumber(59, 2);
        assertTrue(sb.checkValidInsertion(3, 5));
    }

    @Test
    public void checkValidInsertionInvalidColumn() {
        sb.addNumber(57, 8);
        sb.addNumber(59, 2);
        assertFalse(sb.checkValidInsertion(3, 8));
    }

    @Test
    public void checkValidInsertionInvalidRow() {
        sb.addNumber(57, 8);
        sb.addNumber(3, 2);
        assertFalse(sb.checkValidInsertion(8, 2));
    }

    @Test
    public void checkValidInsertionInvalidIndex() {
        sb.addNumber(57, 8);
        sb.addNumber(59, 2);
        assertFalse(sb.checkValidInsertion(57, 1));
    }

    @Test
    public void checkValidInsertionInvalidGrid() {
        sb.addNumber(57, 8);
        sb.addNumber(59, 2);
        assertFalse(sb.checkValidInsertion(76, 8));
    }

    @Test
    public void checkValidInsertionInvalidAll() {
        sb.addNumber(27, 5);
        sb.addNumber(31, 2);
        sb.addNumber(50, 1);
        sb.addNumber(80, 2);
        assertFalse(sb.checkValidInsertion(35, 2));
        assertFalse(sb.checkValidInsertion(41, 1));
        assertFalse(sb.checkValidInsertion(70, 2));
        assertFalse(sb.checkValidInsertion(50, 6));
    }

    @Test
    public void checkValidInsertionFarApartColumn() {
        sb.addNumber(6, 9);
        assertFalse(sb.checkValidColumn(78, 9));
    }

    @Test
    public void checkFillBoardCorrectAmount() {
        sb.fillBoard(20);
        int amountOfNumbers = 0;
        for (int i = 0; i < sb.getBoard().sizeOfBoard(); i++) {
            if (sb.getBoard().getNumberAtIndex(i) != 0) {
                amountOfNumbers++;
            }
        }
        assertEquals(20, amountOfNumbers);
    }

    @Test
    public void getNumberAtIndexTest() {
        sb.addNumber(60, 2);
        assertEquals(2, sb.getNumberAtIndex(60));
        assertEquals(0, sb.getNumberAtIndex(0));
        assertEquals(sb.getBoard().getNumberAtIndex(60), sb.getNumberAtIndex(60));
    }

    @Test
    public void makeIndexEmptyTest() {
        sb.addNumber(3, 8);
        sb.addNumber(20, 5);
        sb.makeIndexEmpty(3);
        assertEquals(0, sb.getNumberAtIndex(3));
        assertEquals(5, sb.getNumberAtIndex(20));
    }

    @Test
    public void selectDifficultyTestReturnEasy() {
        sb.selectDifficulty(Difficulty.EASY);
        Integer difficultyLevel = sb.selectDifficultyLevel();
        boolean withinRange = (difficultyLevel >= 15 && difficultyLevel <= 20);
        assertTrue(withinRange);
    }

    @Test
    public void selectDifficultyTestReturnMedium() {
        sb.selectDifficulty(Difficulty.MEDIUM);
        Integer difficultyLevel = sb.selectDifficultyLevel();
        boolean withinRange = (difficultyLevel >= 25 && difficultyLevel <= 30);
        assertTrue(withinRange);
    }

    @Test
    public void selectDifficultyTestReturnHard() {
        sb.selectDifficulty(Difficulty.HARD);
        Integer difficultyLevel = sb.selectDifficultyLevel();
        boolean withinRange = (difficultyLevel >= 35 && difficultyLevel <= 40);
        assertTrue(withinRange);
    }

    @Test
    public void fillBoardRandomlyCorrectAmountEasy() {
        sb = new UnsolvedSudokuBoard(Difficulty.EASY);
        sb.fillBoardRandomly();
        int countNotZero = 0;
        for (int i = 0; i < 81; i++) {
            if (sb.getNumberAtIndex(i) != 0) {
                countNotZero++;
            }
        }
        boolean enoughNumbersPlaced = (countNotZero >= 15 && countNotZero <= 20);
        assertTrue(enoughNumbersPlaced);
    }

    @Test
    public void fillBoardRandomlyCorrectAmountMedium() {
        sb = new UnsolvedSudokuBoard(Difficulty.MEDIUM);
        sb.fillBoardRandomly();
        int countNotZero = 0;
        for (int i = 0; i < 81; i++) {
            if (sb.getNumberAtIndex(i) != 0) {
                countNotZero++;
            }
        }
        boolean enoughNumbersPlaced = (countNotZero >= 25 && countNotZero <= 30);
        assertTrue(enoughNumbersPlaced);
    }

    @Test
    public void fillBoardRandomlyCorrectAmountHard() {
        sb = new UnsolvedSudokuBoard(Difficulty.HARD);
        sb.fillBoardRandomly();
        int countNotZero = 0;
        for (int i = 0; i < 81; i++) {
            if (sb.getNumberAtIndex(i) != 0) {
                countNotZero++;
            }
        }
        boolean enoughNumbersPlaced = (countNotZero >= 35 && countNotZero <= 40);
        assertTrue(enoughNumbersPlaced);
    }

    @Test
    public void incrementNumberTestNotEmpty() {
        sb.addNumber(5, 5);
        sb.incrementNumber(5);
        assertEquals(6, sb.getNumberAtIndex(5));
    }

    @Test
    public void incrementNumberTestEmpty() {
        sb.incrementNumber(5);
        assertEquals(1, sb.getNumberAtIndex(5));
    }
}



