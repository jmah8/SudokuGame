package ui;

import main.Difficulty;
import main.SolvableSudokuBoard;
import main.SudokuSolver;
import main.UnsolvedSudokuBoard;
import main.SudokuChecker;
import main.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private SolvableSudokuBoard solvableSudokuBoard;
    private SudokuSolver sudokuSolver;
    private SudokuBoard unsolvedSudokuBoard;
    private UnsolvedSudokuBoard solvedBoard;
    private SudokuChecker sudokuChecker;

    private JPanel cardPanel;
    private JPanel mainMenu;
    private JPanel sudokuPanel;
    private JButton startButton;
    private JButton quitButton;

    private JPanel sudokuButtons;
    private JButton checkValidButton;
    private JButton showSolutionButton;
    private JButton backButton;

    public MainGUI() {
        setUpPanels();

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Really Quit?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpSudokuBoard();
            }
        });

        checkValidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuChecker = new SudokuChecker(unsolvedSudokuBoard);
                boolean solved = sudokuChecker.checkValidBoard();
                if (solved) {
                    JOptionPane.showMessageDialog(null, "Looks like this is a valid board!",
                            "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Looks like you made some mistakes." +
                            " Keep trying!", "Sorry!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Really show the solution?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    sudokuSolver = new SudokuSolver(unsolvedSudokuBoard);
                    boolean solvable = sudokuSolver.fillSudokuBoard();
                    if (solvable) {
                        refreshSudokuButtons();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "This board isn't solvable like this. Please change some of the numbers!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // TODO: this requires a deep copy to pull off. But could use checkValidInsertion to not
                    //  find whole solution, but give hint that could potentially lead to failure
//                    solvedBoard = sudokuSolver.getSudokuBoard();
//                    int emptyIndex = 0;
//                    for (int i = 0; i < 81; i++) {
//                        if (unsolvedSudokuBoard.getNumberAtIndex(i) == 0) {
//                            emptyIndex = i;
//                            break;
//                        }
//                    }
//                    updateBoardWithHint(emptyIndex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(mainMenu, "MainMenu");
            }
        });
    }

    private void setUpPanels() {
        JFrame frame = new JFrame("Sudoku");
        frame.setVisible(true);
        frame.setContentPane(cardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 800);
        cardPanel.add(mainMenu, "MainMenu");
        cardPanel.add(sudokuPanel, "SudokuBoard");
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, "MainMenu");
    }


    private void setUpSudokuBoard() {
        Object[] possibleValues = {"Easy", "Medium", "Hard"};
        int n = JOptionPane.showOptionDialog(null, "Choose difficulty (Harder difficulties require longer to load)",
                "Difficulty Level", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, possibleValues, possibleValues[2]);
        switch (n) {
            case JOptionPane.YES_OPTION:
                solvableSudokuBoard = new SolvableSudokuBoard(Difficulty.EASY);
                break;
            case JOptionPane.NO_OPTION:
                solvableSudokuBoard = new SolvableSudokuBoard(Difficulty.MEDIUM);
                break;
            case JOptionPane.CANCEL_OPTION:
                solvableSudokuBoard = new SolvableSudokuBoard(Difficulty.HARD);
                break;
        }
        unsolvedSudokuBoard = solvableSudokuBoard.getSudokuBoard();

        //sudokuButtons.setLayout(new GridLayout(0, 9));

        // This is for Jseparators added
        sudokuButtons.setLayout(new GridLayout(0, 11));


        refreshSudokuButtons();
        cardPanel.add(sudokuPanel, "SudokuBoard");
        showPanel(sudokuPanel,"SudokuBoard");
    }

    private void makeSudokuButtons() {
        JLabel heading = new JLabel("Try to solve this sudoku board!", SwingConstants.CENTER);
        // TODO: need to fix the gridlayout so that i can have one thing that spans the whole 9 columns
//        sudokuPanel.add(heading);
        for (int i = 0; i < 81; i++) {
            JButton button = new JButton(String.valueOf(solvableSudokuBoard.getSudokuBoard().getNumberAtIndex(i)));
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    unsolvedSudokuBoard.incrementNumber(finalI);
                    refreshSudokuButtons();
                }
            });
            sudokuButtons.add(button);
            if (i % 3 == 2 && !(i % 9 == 8)) {
                JSeparator verticalLine = new JSeparator(JSeparator.VERTICAL);
                sudokuButtons.add(verticalLine);
            }
            if (i == 26 || i == 53) {
                for (int x = 0; x < 11; x++) {
                    JSeparator horizontalLine = new JSeparator(JSeparator.HORIZONTAL);
                    sudokuButtons.add(horizontalLine);
                }
            }
        }
//        sudokuPanel.add(sudokuButtons);
//        setUpBottomButtons();
    }

    private void setUpBottomButtons() {
        JPanel controlButtons = new JPanel();
        controlButtons.setLayout(new GridLayout(0, 1));
        sudokuPanel.add(new JSeparator());
//        makeCheckSolutionButton(controlButtons);
//        makeShowSolutionButton(controlButtons);
//        makeBackButton(controlButtons);
        sudokuPanel.add(controlButtons);
    }

//    private void makeBackButton(JPanel buttonPanel) {
////        JPanel backAndHint = new JPanel();
////        backAndHint.setLayout(new GridLayout(1, 0));
////        backAndHint.add(new Label(" "));
////        JButton hintButton = new JButton("Hint?");
////        JButton backButton = new JButton("Back");
////        backAndHint.add(hintButton);
////        backAndHint.add(backButton);
////        sudokuPanel.add(backAndHint);
//
//        JButton backButton = new JButton("Back");
//        buttonPanel.add(backButton);
//
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showPanel(mainMenu, "MainMenu");
//            }
//        });
//    }

//    private void makeShowSolutionButton(JPanel buttonPanel) {
//        JButton hintButton = new JButton("Show solution?");
//        buttonPanel.add(hintButton);
//
//        hintButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int n = JOptionPane.showConfirmDialog(null, "Really show the solution?",
//                        "Warning", JOptionPane.YES_NO_OPTION);
//                if (n == JOptionPane.YES_OPTION) {
//                    sudokuSolver = new SudokuSolver(unsolvedSudokuBoard);
//                    boolean solvable = sudokuSolver.fillSudokuBoard();
//                    if (solvable) {
//                        refreshSudokuButtons();
//                    } else {
//                        JOptionPane.showMessageDialog(null,
//                                "This board isn't solvable like this. Please change some of the numbers!",
//                                "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                    // TODO: this requires a deep copy to pull off. But could use checkValidInsertion to not
//                    //  find whole solution, but give hint that could potentially lead to failure
////                    solvedBoard = sudokuSolver.getSudokuBoard();
////                    int emptyIndex = 0;
////                    for (int i = 0; i < 81; i++) {
////                        if (unsolvedSudokuBoard.getNumberAtIndex(i) == 0) {
////                            emptyIndex = i;
////                            break;
////                        }
////                    }
////                    updateBoardWithHint(emptyIndex);
//                }
//            }
//        });
//    }

//    private void makeCheckSolutionButton(JPanel buttonPanel) {
//        JButton checkSolutionButton = new JButton("Check your solution?");
//        buttonPanel.add(checkSolutionButton);
//
//        checkSolutionButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sudokuChecker = new SudokuChecker(unsolvedSudokuBoard);
//                boolean solved = sudokuChecker.checkValidBoard();
//                if (solved) {
//                    JOptionPane.showMessageDialog(null, "Looks like you solved it good work!",
//                            "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Looks like you made some mistakes." +
//                                    " Keep trying!", "Sorry!", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//    }

    private void updateBoardWithHint(Integer index) {
        int solvedNumber = solvedBoard.getNumberAtIndex(index);
        unsolvedSudokuBoard.addNumber(index, solvedNumber);
        refreshSudokuButtons();
    }

    private void refreshSudokuButtons() {
        sudokuButtons.removeAll();
        makeSudokuButtons();
        sudokuPanel.repaint();
        sudokuPanel.revalidate();
    }

    private void showPanel(JPanel panel, String panelName) {
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, panelName);
    }
}
