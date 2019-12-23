package main;

import java.util.LinkedList;
import java.util.List;

public class UnsolvedSudokuBoard extends SudokuBoard {

//    public Integer NUMBERS_TO_ADD_EASY_DIFFICULTY = random.nextInt(6) + 15;
//    public Integer NUMBERS_TO_ADD_MEDIUM_DIFFICULTY = random.nextInt(6) + 25;
//    public Integer NUMBERS_TO_ADD_HARD_DIFFICULTY = random.nextInt(6) + 35;

    public UnsolvedSudokuBoard(Difficulty difficulty) {
        super(difficulty);
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

//    // MODIFIES: this
//    // EFFECT: randomly fills board with random amount of numbers
//    public void fillBoard(Integer numbersToPlace) {
//        this.difficultyLevelValue = numbersToPlace;
//        fillBoardRandomly();
//    }
//
//    // MODIFIES: this
//    // EFFECT: randomly fills board with random amount of numbers
//    public void fillBoardRandomly() {
//        int numbersToPlace = difficultyLevelValue;
//        for (int i = 0; i < numbersToPlace;) {
//            int indexToPlace = random.nextInt(81);
//            int numberToPlace = random.nextInt(9) + 1;
//            boolean validInsertion = sudokuChecker.checkValidInsertion(indexToPlace, numberToPlace);
//            if (validInsertion) {
//                board.setNumber(indexToPlace, numberToPlace);
//                i++;
//            }
//        }
//    }
}
