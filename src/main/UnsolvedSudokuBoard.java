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
}
