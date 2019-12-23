package main;

import java.util.LinkedList;
import java.util.List;

public class UnsolvedSudokuBoard extends SudokuBoard {

    private final SudokuChecker sudokuChecker = new SudokuChecker(this);
    private List<Integer> board;
//    private Random random = new Random();
//    private Integer difficultyLevelValue = 0;
//    private Difficulty difficulty;

    public Integer NUMBERS_TO_ADD_EASY_DIFFICULTY = random.nextInt(6) + 15;
    public Integer NUMBERS_TO_ADD_MEDIUM_DIFFICULTY = random.nextInt(6) + 25;
    public Integer NUMBERS_TO_ADD_HARD_DIFFICULTY = random.nextInt(6) + 35;

    public UnsolvedSudokuBoard(Difficulty difficulty) {
        board = new LinkedList<>();
        selectDifficulty(difficulty);
        fillWithZeros();
    }

//    // MODIFIES: this
//    // EFFECT: selects a difficultyLevel (numbers to place on board) based on difficulty
//    public void setUpBoard(Difficulty difficulty) {
//        selectDifficulty(difficulty);
//        fillBoardRandomly(difficultyLevelValue);
//    }

    // EFFECT: returns integer based on difficulty
    public Integer selectDifficultyLevel() {
        switch (difficulty) {
            case EASY:
                difficultyLevelValue = NUMBERS_TO_ADD_EASY_DIFFICULTY;
//                this.difficulty = Difficulty.EASY;
                break;
            case MEDIUM:
                difficultyLevelValue = NUMBERS_TO_ADD_MEDIUM_DIFFICULTY;
//                this.difficulty = Difficulty.MEDIUM;
                break;
            case HARD:
                difficultyLevelValue = NUMBERS_TO_ADD_HARD_DIFFICULTY;
//                this.difficulty = Difficulty.HARD;
                break;
        }
        return difficultyLevelValue;
    }

    public List<Integer> getBoard() {
        return board;
    }

    // MODIFIES: this
    // EFFECT: puts number into position index if checkValidInsertion is true, else do nothing
    // Don't let user use this
    public void setNumber(Integer index, Integer number) {
        //if (checkValidInsertion(index, number)) {
            board.set(index, number);
        //}
    }

    // MODIFIES : this
    // EFFECT: increments number in index by one. if number > 9, loop back to 0
    // This is the one user will use
    public void incrementNumber(Integer index) {
        int number = board.get(index);
        number++;
        if (number > 9) {
            number = 0;
        }
        board.set(index, number);
    }

    // EFFECT: Fill board with 81 0s
    public void fillWithZeros() {
        for (int i = 0; i < 81; i++) {
            board.add(0);
        }
    }

    // TODO: make this correct or dont use this implementation and use fillBoardRandomly
//    // MODIFIES: this
//    // EFFECT: fills board with random number of numbers
//    public void fillBoardBroken() {
//        Random random = new Random();
//        int columnGridNumber = 0;
//        // For columns
//        for (int x = 0; x < 3; x++) {
//            // For rows
//            int rowGridNumber = 0;
//            for (int y = 0; y < 3; y++) {
//                int numbersToFill = (random.nextInt(3) + 2);
//                // For grids
//                for (int z = 0; z < numbersToFill;) {
//                    int numberToPlace = random.nextInt(9) + 1;
//                    int indexToPlace = random.nextInt(9);
//                    boolean validInsertion = checkValidInsertion(indexToPlace, numberToPlace);
//                        // Bottom row
//                    if (5 < indexToPlace) {
//                        if (validInsertion) {
//                            indexToPlace += (12 + rowGridNumber + columnGridNumber);
//                            board.set(indexToPlace, numberToPlace);
//                            z++;
//                        }
//                        // Middle row
//                    } else if (2 < indexToPlace) {
//                        if (validInsertion) {
//                            indexToPlace += (6 + rowGridNumber + columnGridNumber);
//                            board.set((indexToPlace), numberToPlace);
//                            z++;
//                        }
//                        // First row
//                    } else {
//                        if (validInsertion) {
//                            indexToPlace += (rowGridNumber + columnGridNumber);
//                            board.set((indexToPlace), numberToPlace);
//                            z++;
//                        }
//                    }
//                }
//                rowGridNumber += 3;
//            }
//            columnGridNumber += 27;
//        }
//    }

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
                //random.nextInt(19) + 18;
        for (int i = 0; i < numbersToPlace;) {
            int indexToPlace = random.nextInt(81);
            int numberToPlace = random.nextInt(9) + 1;
            boolean validInsertion = sudokuChecker.checkValidInsertion(indexToPlace, numberToPlace);
            if (validInsertion) {
                board.set(indexToPlace, numberToPlace);
                i++;
            }
        }
    }

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

    // MODIFIES : this
    // EFFECT: sets the value at index to 0
    public void makeIndexEmpty(Integer index) {
        board.set(index, 0);
    }

    public Integer getNumberAtIndex(Integer index) {
        return board.get(index);
    }

    // EFFECT: prints board
    public void printBoard() {
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0 && !(i == 0)) {
                System.out.println("");
            }
            System.out.print(board.get(i) + " ");
        }
        System.out.println("");
    }
}
