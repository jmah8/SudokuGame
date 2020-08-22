package main;

public class SolvableSudokuBoard extends SudokuBoard {

    private UnsolvedSudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;

    public SolvableSudokuBoard(Difficulty difficulty) {
        super(difficulty);

        setUpSudokuBoard();

        sudokuSolver.printBoard();
        System.out.println(sudokuSolver.isFilled());


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
                break;
            case MEDIUM:
                difficultyLevelValue = NUMBERS_TO_REMOVE_MEDIUM_DIFFICULTY;
                break;
            case HARD:
                difficultyLevelValue = NUMBERS_TO_REMOVE_HARD_DIFFICULTY;
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
