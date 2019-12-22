package ui;

import main.Difficulty;
import main.SolvableSudokuBoard;
import main.SudokuSolver;
import main.UnsolvedSudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private SolvableSudokuBoard solvableSudokuBoard;
    private SudokuSolver sudokuSolver;
    private UnsolvedSudokuBoard unsolvedSudokuBoard;
    private UnsolvedSudokuBoard solvedBoard;

    private JPanel cardPanel;
    private JPanel mainMenu;
    private JPanel sudokuPanel;
    private JButton startButton;
    private JButton quitButton;

    public MainGUI() {
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
        sudokuPanel = new JPanel(new GridLayout(0, 9));
        makeSudokuButtons();
        cardPanel.add(sudokuPanel, "SudokuBoard");
        showPanel(sudokuPanel,"SudokuBoard");
    }

    private void makeSudokuButtons() {
        JLabel heading = new JLabel("Try to solve this sudoku board!", SwingConstants.CENTER);
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
            sudokuPanel.add(button);
        }
        sudokuPanel.add(new Label(""));
        makeCheckSolutionButton();
        makeShowSolutionButton();
        makeBackButton();
    }

    private void makeBackButton() {
//        JPanel backAndHint = new JPanel();
//        backAndHint.setLayout(new GridLayout(1, 0));
//        backAndHint.add(new Label(" "));
//        JButton hintButton = new JButton("Hint?");
//        JButton backButton = new JButton("Back");
//        backAndHint.add(hintButton);
//        backAndHint.add(backButton);
//        sudokuPanel.add(backAndHint);

        JButton backButton = new JButton("Back");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 9;
        c.gridx = 0;
        c.gridy = 1;
        sudokuPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(mainMenu, "MainMenu");
            }
        });
    }

    private void makeShowSolutionButton() {
        JButton hintButton = new JButton("Show solution?");
        sudokuPanel.add(hintButton);

        hintButton.addActionListener(new ActionListener() {
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
                                "This board is solvable like this. Please change some of the numbers!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // TODO: this requires a deep copy to pull off
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
    }

    private void makeCheckSolutionButton() {
        JButton checkSolutionButton = new JButton("Check your solution?");
        sudokuPanel.add(checkSolutionButton);

        checkSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void updateBoardWithHint(Integer index) {
        int solvedNumber = solvedBoard.getNumberAtIndex(index);
        unsolvedSudokuBoard.setNumber(index, solvedNumber);
        refreshSudokuButtons();
    }

    private void refreshSudokuButtons() {
        sudokuPanel.removeAll();
        makeSudokuButtons();
        sudokuPanel.repaint();
        sudokuPanel.revalidate();
    }

    private void showPanel(JPanel panel, String panelName) {
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, panelName);
    }
}
