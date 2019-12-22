package main;

import java.util.Random;

public abstract class SudokuBoard {

    protected Random random = new Random();
    protected Integer difficultyLevelValue = 0;
    protected Difficulty difficulty;

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
}
