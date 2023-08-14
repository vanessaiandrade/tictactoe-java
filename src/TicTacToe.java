import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    // Vars - Constant
    private final String PLAYER_TURN = "Who is playing: ";

    // Vars - Form
    private final JFrame jFrameTicTacToe = new JFrame("Tic Tac Toe");
    private JPanel jPanelTicTacToe;
    private JLabel labelPlayerTurn;
    private JButton btnPosition00;
    private JButton btnPosition01;
    private JButton btnPosition02;
    private JButton btnPosition10;
    private JButton btnPosition11;
    private JButton btnPosition12;
    private JButton btnPosition20;
    private JButton btnPosition21;
    private JButton btnPosition22;
    private JLabel labelVictoriesPlayerX;
    private JLabel labelVictoriesPlayerXValue;
    private JButton btnReset;
    private JLabel labelVictoriesPlayerO;
    private JLabel labelVictoriesPlayerOValue;

    // Vars - Logic
    private String[][] positions = new String[3][3];
    private String lastBeginnerPlayer = "X";
    private String whoIsPlaying = lastBeginnerPlayer;
    private int turns;
    private int xVictories;
    private int oVictories;

    // Methods
    public TicTacToe() {
        initializeButtonsEvents();

        jFrameTicTacToe.add(jPanelTicTacToe);
        jFrameTicTacToe.pack();
        jFrameTicTacToe.setLocationRelativeTo(null);
        jFrameTicTacToe.setResizable(false);
        jFrameTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameTicTacToe.setVisible(true);
    }

    private void initializeButtonsEvents() {
        setButtonClickEvent(btnPosition00, 0, 0);
        setButtonClickEvent(btnPosition01, 0, 1);
        setButtonClickEvent(btnPosition02, 0, 2);
        setButtonClickEvent(btnPosition10, 1, 0);
        setButtonClickEvent(btnPosition11, 1, 1);
        setButtonClickEvent(btnPosition12, 1, 2);
        setButtonClickEvent(btnPosition20, 2, 0);
        setButtonClickEvent(btnPosition21, 2, 1);
        setButtonClickEvent(btnPosition22, 2, 2);
        setResetButtonClickEvent();
    }

    private void setButtonClickEvent(JButton button, int i, int j) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                positions[i][j] = whoIsPlaying;
                turns++;

                button.setEnabled(false);
                button.setText(whoIsPlaying);

                gameLogic();
            }
        });
    }

    private void setResetButtonClickEvent() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xVictories = 0;
                oVictories = 0;

                endGame(true);
            }
        });
    }

    private void gameLogic() {
        int winnerPossibility = 0;
        String hasWinner = null;
        String test = null;

        while (winnerPossibility < 8 && hasWinner == null) {
            switch (winnerPossibility) {
                case 0 -> {
                    test = positions[0][0] + positions[0][1] + positions[0][2];
                }
                case 1 -> {
                    test = positions[1][0] + positions[1][1] + positions[1][2];
                }
                case 2 -> {
                    test = positions[2][0] + positions[2][1] + positions[2][2];
                }
                case 3 -> {
                    test = positions[0][0] + positions[1][0] + positions[2][0];
                }
                case 4 -> {
                    test = positions[0][1] + positions[1][1] + positions[2][1];
                }
                case 5 -> {
                    test = positions[0][2] + positions[1][2] + positions[2][2];
                }
                case 6 -> {
                    test = positions[0][0] + positions[1][1] + positions[2][2];
                }
                case 7 -> {
                    test = positions[0][2] + positions[1][1] + positions[2][0];
                }
            }

            hasWinner = checkWinner(test);
            winnerPossibility++;
        }

        if (hasWinner != null || turns == 9) {
            popUp(hasWinner);
            endGame(false);
        } else {
            changePlayerTurn();
        }
    }

    private String checkWinner(String test) {
        if (test.equals("XXX")) {
            xVictories++;
            return "X";
        } else if (test.equals("OOO")) {
            oVictories++;
            return "O";
        }

        return null;
    }

    private void changePlayerTurn() {
        whoIsPlaying = whoIsPlaying.equals("X") ? "O" : "X";
        labelPlayerTurn.setText(PLAYER_TURN + whoIsPlaying);
    }

    private void popUp(String winner) {
        if (winner == null) {
            new OptionPane("Draw");
        } else {
            new OptionPane("The Winner is: " + winner);
        }
    }

    private void endGame(boolean restart) {
        resetPositions();
        turns = 0;

        labelVictoriesPlayerXValue.setText(String.valueOf(xVictories));
        labelVictoriesPlayerOValue.setText(String.valueOf(oVictories));

        if (restart) {
            lastBeginnerPlayer = "X";
        } else {
            lastBeginnerPlayer = lastBeginnerPlayer.equals("X") ? "O" : "X";
        }

        whoIsPlaying = lastBeginnerPlayer;
        labelPlayerTurn.setText(PLAYER_TURN + whoIsPlaying);

        restoreDefaultButtons();
    }

    private void resetPositions() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                positions[i][j] = null;
            }
        }
    }

    private void restoreDefaultButtons() {
        helperRestoreDefaultButtons(btnPosition00);
        helperRestoreDefaultButtons(btnPosition01);
        helperRestoreDefaultButtons(btnPosition02);
        helperRestoreDefaultButtons(btnPosition10);
        helperRestoreDefaultButtons(btnPosition11);
        helperRestoreDefaultButtons(btnPosition12);
        helperRestoreDefaultButtons(btnPosition20);
        helperRestoreDefaultButtons(btnPosition21);
        helperRestoreDefaultButtons(btnPosition22);
    }

    private void helperRestoreDefaultButtons(JButton button) {
        button.setText("?");
        button.setEnabled(true);
    }

}