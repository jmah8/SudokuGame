package main;

import java.util.Random;

public abstract class SudokuBoard {

    protected Board board;
    protected Random random = new Random();
    protected Integer difficultyLevelValue = 0;
    protected Difficulty difficulty;
    protected final SudokuChecker sudokuChecker = new SudokuChecker(this);

    protected Integer NUMBERS_TO_ADD_EASY_DIFFICULTY = random.nextInt(6) + 15;
    protected Integer NUMBERS_TO_ADD_MEDIUM_DIFFICULTY = random.nextInt(6) + 25;
    protected Integer NUMBERS_TO_ADD_HARD_DIFFICULTY = random.nextInt(6) + 35;

    protected Integer NUMBERS_TO_REMOVE_EASY_DIFFICULTY = random.nextInt(5) + 34;
    protected Integer NUMBERS_TO_REMOVE_MEDIUM_DIFFICULTY = random.nextInt(5) + 52;
    protected Integer NUMBERS_TO_REMOVE_HARD_DIFFICULTY = random.nextInt(5) + 70;

    public SudokuBoard(Difficulty difficulty) {
        board = new Board();
        selectDifficulty(difficulty);
    }

    // TODO: could also use method dispatching to make it so it will
    //  use actual type's selectDifficulty method and make it so I dont need abstract method
    public void selectDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                this.difficulty = Difficulty.EASY;
                break;
            case MEDIUM:
                this.difficulty = Difficulty.MEDIUM;
                break;
            case HARD:
                this.difficulty = Difficulty.HARD;
                break;
        }
        selectDifficultyLevel();
    }

    public abstract Integer selectDifficultyLevel();

    // EFFECT: returns true if index is empty, and if column and row don't have same number,
    //         and if grid doesn't contain same number, else false otherwise
    public boolean checkValidInsertion(Integer index, Integer number) {
        return sudokuChecker.checkValidInsertion(index, number);
    }

    // EFFECT: returns true if row doesn't have same number, false otherwise
    public boolean checkValidRow(Integer index, Integer number) {
        return sudokuChecker.checkValidRow(index, number);
    }

    // EFFECT: returns true if all rows doesn't have same number, false otherwise
    public boolean checkValidRow() {
        return sudokuChecker.checkValidRow();
    }

    // EFFECT: returns true if column doesn't have same number, false otherwise
    public boolean checkValidColumn(Integer index, Integer number) {
        return sudokuChecker.checkValidColumn(index, number);
    }

    // EFFECT: returns true if all column doesn't have same number, false otherwise
    public boolean checkValidColumn() {
        return sudokuChecker.checkValidColumn();
    }

    // EFFECT: returns true if number is not in grid (3x3), return false
    public boolean checkValidGrid(Integer index, Integer number) {
        // Finds which part of the grid is it on (top = 0, middle = 1, bottom = 2)
        // Makes number always on the top of grid
        // Makes number always on the top and to the left of grid
        return sudokuChecker.checkValidGrid(index, number);
    }

    // EFFECT: returns true if index is 0 (signifies empty)
    public boolean checkValidIndex(Integer index) {
        return sudokuChecker.checkValidIndex(index);
    }

    // EFFECT: returns true if board is complete and valid, false otherwise
    // TODO: fix bug where since i need the numbers to psuedo insert,
    //  it will always return false since the same number already exist
    public boolean checkIfValidAndCompleteBoard() {
        return sudokuChecker.checkIfValidAndCompleteBoard();
    }

    public Board getBoard() {
        return board;
    }

    // MODIFIES: this
    // EFFECT: puts number into position index if checkValidInsertion is true, else do nothing
    // Don't let user use this
    public void setNumber(Integer index, Integer number) {
        //if (checkValidInsertion(index, number)) {
        board.setNumber(index, number);
        //}
    }

    // MODIFIES : this
    // EFFECT: increments number in index by one. if number > 9, loop back to 0
    // This is the one user will use
    public void incrementNumber(Integer index) {
        board.incrementNumber(index);
    }

    // EFFECT: prints board
    public void printBoard() {
        board.printBoard();
    }

    // MODIFIES : this
    // EFFECT: sets the value at index to 0
    public void makeIndexEmpty(Integer index) {
        board.makeIndexEmpty(index);
    }

    public Integer getNumberAtIndex(Integer index) {
        return board.getNumberAtIndex(index);
    }

    // MODIFIES: this
    // EFFECT: randomly fills board with random amount of numbers
    public void fillBoard(Integer numbersToPlace) {
        this.difficultyLevelValue = numbersToPlace;
        fillBoardRandomly();
    }

    // MODIFIES: this
    // EFFECT: randomly fills board with random amount of numbers
    public void fillBoardRandomly() {
        int numbersToPlace = difficultyLevelValue;
        for (int i = 0; i < numbersToPlace;) {
            int indexToPlace = random.nextInt(81);
            int numberToPlace = random.nextInt(9) + 1;
            boolean validInsertion = sudokuChecker.checkValidInsertion(indexToPlace, numberToPlace);
            if (validInsertion) {
                board.setNumber(indexToPlace, numberToPlace);
                i++;
            }
        }
    }

}
