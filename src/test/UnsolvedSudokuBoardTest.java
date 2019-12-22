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
        assertEquals(81, sb.getBoard().size());
        assertTrue(sb.getBoard().contains(0));
        assertFalse(sb.getBoard().contains(1));
        assertFalse(sb.getBoard().contains(2));
        assertFalse(sb.getBoard().contains(3));
        assertFalse(sb.getBoard().contains(4));
        assertFalse(sb.getBoard().contains(5));
        assertFalse(sb.getBoard().contains(6));
        assertFalse(sb.getBoard().contains(7));
        assertFalse(sb.getBoard().contains(8));
        assertFalse(sb.getBoard().contains(9));
    }

    @Test
    public void addNumberTest() {
        sb.setNumber(60, 8);
        sb.setNumber(20, 7);
        assertEquals(8, sb.getBoard().get(60));
        assertEquals(7, sb.getBoard().get(20));
        assertEquals(81, sb.getBoard().size());
    }

    @Test
    public void checkValidIndexValid() {
        assertTrue(sb.checkValidIndex(50));
    }

    @Test
    public void checkValidIndexInvalid() {
        sb.setNumber(50, 5);
        assertFalse(sb.checkValidIndex(50));
    }

    @Test
    public void checkValidRowTestValidSameRow() {
        sb.setNumber(3, 5);
        sb.setNumber(5, 8);
        sb.setNumber(1, 2);
        assertTrue(sb.checkValidRow(4, 6));
    }

    @Test
    public void checkValidRowTestValidDifferentRow() {
        sb.setNumber(3, 5);
        sb.setNumber(5, 8);
        sb.setNumber(1, 2);
        assertTrue(sb.checkValidRow(30, 5));
    }

    @Test
    public void checkValidRowTestInvalid() {
        sb.setNumber(1, 2);
        sb.setNumber(3, 5);
        sb.setNumber(5, 8);
        assertFalse(sb.checkValidRow(8, 8));
    }

    @Test
    public void checkValidRowTestIfCheckingCorrectRow() {
        sb.setNumber(48, 5);
        sb.setNumber(53, 8);
        sb.setNumber(46, 2);
        assertTrue(sb.checkValidRow(22, 8));
    }

    @Test
    public void checkValidColumnValidSameColumn() {
        sb.setNumber(1, 8);
        sb.setNumber(37, 9);
        assertTrue(sb.checkValidColumn(73, 2));
    }

    @Test
    public void checkValidColumnValidDifferentColumn() {
        sb.setNumber(1, 8);
        sb.setNumber(37, 9);
        assertTrue(sb.checkValidColumn(6, 8));
    }

    @Test
    public void checkValidColumnInvalid() {
        sb.setNumber(24, 8);
        sb.setNumber(42, 1);
        assertFalse(sb.checkValidColumn(69, 8));
    }

    @Test
    public void checkValidColumnTestIfCheckingCorrectColumn() {
        sb.setNumber(17, 2);
        sb.setNumber(35, 8);
        assertTrue(sb.checkValidColumn(21, 8));
        assertTrue(sb.checkValidColumn(77, 2));
    }

    @Test
    public void checkValidGridValid() {
        sb.setNumber(32, 8);
        sb.setNumber(44, 2);
        assertTrue(sb.checkValidGrid(42, 5));
    }

    @Test
    public void checkValidGridInvalid() {
        sb.setNumber(57, 1);
        sb.setNumber(76, 5);
        assertFalse(sb.checkValidGrid(66, 5));
    }

    @Test
    public void checkValidGridCheckForRightGrid() {
        sb.setNumber(30, 2);
        sb.setNumber(49, 5);
        assertTrue(sb.checkValidGrid(58, 5));
    }

    @Test
    public void checkValidInsertionValid() {
        sb.setNumber(57, 8);
        sb.setNumber(59, 2);
        assertTrue(sb.checkValidInsertion(3, 5));
    }

    @Test
    public void checkValidInsertionInvalidColumn() {
        sb.setNumber(57, 8);
        sb.setNumber(59, 2);
        assertFalse(sb.checkValidInsertion(3, 8));
    }

    @Test
    public void checkValidInsertionInvalidRow() {
        sb.setNumber(57, 8);
        sb.setNumber(3, 2);
        assertFalse(sb.checkValidInsertion(8, 2));
    }

    @Test
    public void checkValidInsertionInvalidIndex() {
        sb.setNumber(57, 8);
        sb.setNumber(59, 2);
        assertFalse(sb.checkValidInsertion(57, 1));
    }

    @Test
    public void checkValidInsertionInvalidGrid() {
        sb.setNumber(57, 8);
        sb.setNumber(59, 2);
        assertFalse(sb.checkValidInsertion(76, 8));
    }

    @Test
    public void checkValidInsertionInvalidAll() {
        sb.setNumber(27, 5);
        sb.setNumber(31, 2);
        sb.setNumber(50, 1);
        sb.setNumber(80, 2);
        assertFalse(sb.checkValidInsertion(35, 2));
        assertFalse(sb.checkValidInsertion(41, 1));
        assertFalse(sb.checkValidInsertion(70, 2));
        assertFalse(sb.checkValidInsertion(50, 6));
    }

    @Test
    public void checkValidInsertionFarApartColumn() {
        sb.setNumber(6, 9);
        assertFalse(sb.checkValidColumn(78, 9));
    }

    @Test
    public void checkFillBoardCorrectAmount() {
        sb.fillBoard(20);
        int amountOfNumbers = 0;
        for (int i = 0; i < sb.getBoard().size(); i++) {
            if (sb.getBoard().get(i) != 0) {
                amountOfNumbers++;
            }
        }
        assertEquals(20, amountOfNumbers);
    }

    @Test
    public void getNumberAtIndexTest() {
        sb.setNumber(60, 2);
        assertEquals(2, sb.getNumberAtIndex(60));
        assertEquals(0, sb.getNumberAtIndex(0));
        assertEquals(sb.getBoard().get(60), sb.getNumberAtIndex(60));
    }

    @Test
    public void makeIndexEmptyTest() {
        sb.setNumber(3, 8);
        sb.setNumber(20, 5);
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
        sb.setNumber(5, 5);
        sb.incrementNumber(5);
        assertEquals(6, sb.getNumberAtIndex(5));
    }

    @Test
    public void incrementNumberTestEmpty() {
        sb.incrementNumber(5);
        assertEquals(1, sb.getNumberAtIndex(5));
    }
}



