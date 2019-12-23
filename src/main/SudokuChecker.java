package main;

import exception.NotFilledBoardException;

public class SudokuChecker {

    private SudokuBoard sudokuBoard;

    public SudokuChecker(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    // EFFECT: returns true if index is empty, and if column and row don't have same number,
    //         and if grid doesn't contain same number, else false otherwise
    public boolean checkValidInsertion(Integer index, Integer number) {
        return (checkValidColumn(index, number) && checkValidRow(index, number)
                && checkValidIndex(index) && checkValidGrid(index, number));
    }

    // EFFECT: returns true if row doesn't have same number, false otherwise
    public boolean checkValidRow(Integer index, Integer number) {
        int rowNumber = (index / 9) * 9;
        for (int i = 0; i < 9; i++) {
            if (number == sudokuBoard.getBoard().getNumberAtIndex(rowNumber)) {
                return false;
            }
            rowNumber++;
        }
        return true;
    }

    // EFFECT: returns true if column doesn't have same number, false otherwise
    public boolean checkValidColumn(Integer index, Integer number) {
        int columnNumber = index % 9;
        for (int i = 0; i < 9; i++) {
            if (number == sudokuBoard.getBoard().getNumberAtIndex(columnNumber)) {
                return false;
            }
            columnNumber = columnNumber + 9;
        }
        return true;
    }

    // EFFECT: returns true if number is not in grid (3x3), else return false
    public boolean checkValidGrid(Integer index, Integer number) {
        // Finds which part of the grid is it on (top = 0, middle = 1, bottom = 2)
        int columnNumber = (index / 9) % 3;
        // Makes number always on the top of grid
        int indexNumberTopPartOfGrid = index - (9 * columnNumber);
        // Makes number always on the top and to the left of grid
        int indexNumberTopLeftGrid = (indexNumberTopPartOfGrid / 3) * 3;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (number == sudokuBoard.getBoard().getNumberAtIndex(indexNumberTopLeftGrid)) {
                    return false;
                }
                indexNumberTopLeftGrid++;
            }
            indexNumberTopLeftGrid += 6;
        }
        return true;
    }

    // EFFECT: returns true if index is 0 (signifies empty)
    public boolean checkValidIndex(Integer index) {
        return 0 == sudokuBoard.getBoard().getNumberAtIndex(index);
    }

    // EFFECT: returns true if all rows doesn't have same number, false otherwise.
    public boolean checkValidRow() {
        for (int x = 0; x < 81; x++) {
            int index = x;
            int numberAtIndex = sudokuBoard.getNumberAtIndex(x);
            if (numberAtIndex == 0) {
                // Do nothing
            } else {
                sudokuBoard.addNumber(index, 0);
                boolean validRow = checkValidRow(index, numberAtIndex);
                if (!validRow) {
                    sudokuBoard.addNumber(index, numberAtIndex);
                    return false;
                }
            }
            sudokuBoard.addNumber(index, numberAtIndex);
        }
        return true;
    }

    // EFFECT: returns true if all column doesn't have same number, false otherwise
    public boolean checkValidColumn() {
        for (int x = 0; x < 81; x++) {
            int index = x;
            int numberAtIndex = sudokuBoard.getNumberAtIndex(x);
            if (numberAtIndex == 0) {
                // Do nothing
            } else {
                sudokuBoard.addNumber(index, 0);
                boolean validColumn = checkValidColumn(index, numberAtIndex);
                if (!validColumn) {
                    sudokuBoard.addNumber(index, numberAtIndex);
                    return false;
                }
            }
            sudokuBoard.addNumber(index, numberAtIndex);
        }
        return true;
    }

    // EFFECT: returns true if all numbers in grid (3x3) are different, else return false
    public boolean checkValidGrid() {
        for (int x = 0; x < 81; x++) {
            int index = x;
            int numberAtIndex = sudokuBoard.getNumberAtIndex(x);
            if (numberAtIndex == 0) {
                // Do nothing
            } else {
                sudokuBoard.addNumber(index, 0);
                boolean validGrid = checkValidGrid(index, numberAtIndex);
                if (!validGrid) {
                    sudokuBoard.addNumber(index, numberAtIndex);
                    return false;
                }
            }
            sudokuBoard.addNumber(index, numberAtIndex);
        }
        return true;
    }

    public boolean checkValidBoard() {
        for (int i = 0; i < 81; i++) {
            int index = i;
            int numberAtIndex = sudokuBoard.getNumberAtIndex(i);
            if (numberAtIndex == 0) {
                // Do nothing
            } else {
                boolean validIndex = !(sudokuBoard.checkValidIndex(index));
                if (!validIndex) {
                    return false;
                }
                sudokuBoard.addNumber(index, 0);
                boolean validRow = checkValidRow(index, numberAtIndex);
                boolean validColumn = checkValidColumn(index, numberAtIndex);
                boolean validGrid = checkValidGrid(index, numberAtIndex);
                if (!validRow || !validColumn || !validGrid) {
                    sudokuBoard.addNumber(index, numberAtIndex);
                    return false;
                }
            }
            sudokuBoard.addNumber(index, numberAtIndex);
        }
        return true;
    }

    // EFFECT: returns true if board is complete and valid, false otherwise
    // TODO: fix bug where since i need the numbers to psuedo insert,
    //  it will always return false since the same number already exist
    public boolean checkIfValidAndCompleteBoard() {
        boolean solved = true;
        for (int i = 0; i < 81; i++) {
            Integer numberAtIndex = sudokuBoard.getBoard().getNumberAtIndex(i);
            Integer indexNumber = i;
            if (checkValidColumn(indexNumber, numberAtIndex) && checkValidRow(indexNumber,
                    numberAtIndex) && checkValidGrid(indexNumber, numberAtIndex) && !checkValidIndex(indexNumber)) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }
}