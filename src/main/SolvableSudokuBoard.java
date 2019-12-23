package main;

import java.util.Random;

public class SolvableSudokuBoard extends SudokuBoard {

    private UnsolvedSudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;
//    private Random random = new Random();

//    public Integer NUMBERS_TO_REMOVE_EASY_DIFFICULTY = random.nextInt(5) + 34;
//    public Integer NUMBERS_TO_REMOVE_MEDIUM_DIFFICULTY = random.nextInt(5) + 52;
//    public Integer NUMBERS_TO_REMOVE_HARD_DIFFICULTY = random.nextInt(5) + 70;

    // TODO: remove unneccessary methods
    public SolvableSudokuBoard(Difficulty difficulty) {
        super(difficulty);
//        selectDifficulty(difficulty);
        setUpSudokuBoard();

        sudokuSolver.printBoard();
        System.out.println(sudokuSolver.isComplete());


        removeRandomNumbers();

        sudokuSolver.printBoard();
    }

    public void setUpSudokuBoard() {
        sudokuBoard = new UnsolvedSudokuBoard(this.difficulty);
        sudokuBoard.fillBoardRandomly();
        sudokuSolver = new SudokuSolver(sudokuBoard);
        generateNewSolvableBoard();
    }

    public Integer selectDifficultyLevel() {
        switch (difficulty) {
            case EASY:
                difficultyLevelValue = NUMBERS_TO_REMOVE_EASY_DIFFICULTY;
//                this.difficulty = Difficulty.EASY;
                break;
            case MEDIUM:
                difficultyLevelValue = NUMBERS_TO_REMOVE_MEDIUM_DIFFICULTY;
//                this.difficulty = Difficulty.MEDIUM;
                break;
            case HARD:
                difficultyLevelValue = NUMBERS_TO_REMOVE_HARD_DIFFICULTY;
//                this.difficulty = Difficulty.HARD;
                break;
        }
        return difficultyLevelValue;
    }

    public void generateNewSolvableBoard() {
        boolean solvable = sudokuSolver.fillSudokuBoard();
        while (!solvable) {
            sudokuBoard = new UnsolvedSudokuBoard(this.difficulty);
            sudokuBoard.fillBoardRandomly();
            sudokuSolver.changeSudokuBoard(sudokuBoard);
            solvable = sudokuSolver.fillSudokuBoard();
        }
    }

    public void removeRandomNumbers() {
        int removeNumber = difficultyLevelValue;
        for (int i = 0; i < removeNumber;) {
            int indexToRemove = random.nextInt(81);
            if (!sudokuBoard.checkValidIndex(indexToRemove)) {
                sudokuBoard.makeIndexEmpty(indexToRemove);
                i++;
            }
        }
    }

    public UnsolvedSudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public SudokuSolver getSudokuSolver() {
        return sudokuSolver;
    }
}
