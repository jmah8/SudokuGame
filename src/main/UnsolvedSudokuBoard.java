package main;

import java.util.LinkedList;
import java.util.List;

public class UnsolvedSudokuBoard extends SudokuBoard {

    private final SudokuChecker sudokuChecker = new SudokuChecker(this);
//    private Random random = new Random();
//    private Integer difficultyLevelValue = 0;
//    private Difficulty difficulty;

    public Integer NUMBERS_TO_ADD_EASY_DIFFICULTY = random.nextInt(6) + 15;
    public Integer NUMBERS_TO_ADD_MEDIUM_DIFFICULTY = random.nextInt(6) + 25;
    public Integer NUMBERS_TO_ADD_HARD_DIFFICULTY = random.nextInt(6) + 35;

    public UnsolvedSudokuBoard(Difficulty difficulty) {
        board = new Board();
        selectDifficulty(difficulty);
    }

    // MODIFIES: this
    // EFFECT: selects a difficultyLevel (numbers to place on board) based on difficulty
    public void setUpBoard(Difficulty difficulty) {
        selectDifficulty(difficulty);
        fillBoardRandomly();
    }

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

//    public Board getBoard() {
//        return board;
//    }

//    // MODIFIES: this
//    // EFFECT: puts number into position index if checkValidInsertion is true, else do nothing
//    // Don't let user use this
//    public void setNumber(Integer index, Integer number) {
//        //if (checkValidInsertion(index, number)) {
//            board.setNumber(index, number);
//        //}
//    }

//    // MODIFIES : this
//    // EFFECT: increments number in index by one. if number > 9, loop back to 0
//    // This is the one user will use
//    public void incrementNumber(Integer index) {
//        int number = board.getNumberAtIndex(index);
//        number++;
//        if (number > 9) {
//            number = 0;
//        }
//        board.setNumber(index, number);
//    }


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

//    // MODIFIES : this
//    // EFFECT: sets the value at index to 0
//    public void makeIndexEmpty(Integer index) {
//        board.makeIndexEmpty(index);
//    }
//
//    public Integer getNumberAtIndex(Integer index) {
//        return board.getNumberAtIndex(index);
//    }

//    // EFFECT: prints board
//    public void printBoard() {
//        board.printBoard();
//    }
}
