import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI {
    private JFrame frame;
    private JTextArea gameArea;
    private JButton startButton;
    private Game game;
    private Timer timer;

    public GameGUI() {
        frame = new JFrame("Dungeon Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        gameArea = new JTextArea();
        gameArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        gameArea.setEditable(false);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(gameArea), BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
    }

    private void startGame() {
        try {
            game = new Game("dungeonO.txt");
            gameArea.setText(game.toString());
            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.isGameOver()) {
                        ((Timer) e.getSource()).stop();
                        JOptionPane.showMessageDialog(frame, "Caught by monster!");
                    } else {
                        game.step();
                        gameArea.setText(game.toString());
                    }
                }
            });
            timer.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI().show();
            }
        });
    }
}
