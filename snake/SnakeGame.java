import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class SnakeGame {
    private Menu optionsMenu;

    public SnakeGame() {
        optionsMenu = new Menu(this);
       
    }

    public void openOptionsMenu() {
        optionsMenu.setVisible(true);
    }

   


public class SnakeGame extends JFrame implements KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private final int DELAY = 75;
    private final int REPLAY_DELAY = 5000; // 5 seconds
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int bodyParts = 6;
    private int applesEaten;
    private int bestScore;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer gameTimer;
    private Timer replayTimer;
    private Image backgroundImage;
    private JButton replayButton;
    private JPanel gameOverPanel;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

     
        backgroundImage = new ImageIcon("background.jpg").getImage();

        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        gameTimer = new Timer(DELAY, this);
        gameTimer.start();
        resetSnake();
        resetScore();
        
        direction = 'R';
    }

    private void resetSnake() {
        
        x[0] = WIDTH / 4;
        y[0] = HEIGHT / 2;
    }

    private void resetScore() {
        applesEaten = 0;
    }

    public void paint(Graphics g) {
        
        g.drawImage(backgroundImage, 0, 0, this);

        // Dessiner la pomme
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }

       
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + applesEaten, 20, 30);

        g.drawString("Best Score: " + bestScore, 20, 60);
    }

    public void newApple() {
        
        Random random = new Random();
        appleX = random.nextInt((WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
      
        while (appleX >= WIDTH || appleY >= HEIGHT) {
            appleX = random.nextInt((WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            if (applesEaten > bestScore) {
                bestScore = applesEaten;
            }
            newApple();
        }
    }

    public void checkCollisions() {
       
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }


        if (x[0] < 0) {
            running = false;
        }
   
        if (x[0] > WIDTH - UNIT_SIZE) {
            running = false;
        }
      
        if (y[0] < 0) {
            running = false;
        }
       
        if (y[0] > HEIGHT - UNIT_SIZE) {
            running = false;
        }

        if (!running) {
            gameTimer.stop();
            displayGameOverMenu();
        }
    }

    private void displayGameOverMenu() {
        if (gameOverPanel == null) {
            gameOverPanel = new JPanel();
            replayButton = new JButton("Rejouer");
            replayButton.addActionListener(this);
            gameOverPanel.add(replayButton);
        }

        JOptionPane.showMessageDialog(null, gameOverPanel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayButton) {
            gameOverPanel.setVisible(false);
            gameOverPanel = null;
            replayTimer = new Timer(REPLAY_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replayTimer.stop();
                    startGame();
                }
            });
            replayTimer.setRepeats(false);
            replayTimer.start();
        } else if (running) {
            if (e.getSource() == gameTimer) {
                move();
                checkApple();
                checkCollisions();
                repaint();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
    }
}