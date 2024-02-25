package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JFrame implements ActionListener, KeyListener {
    private Game game;
    private Timer timer;

    public SnakeGame() {
        int rowCount = 20;
        int colCount = 20;

        Board board = new Board(rowCount, colCount);
        Snake snake = new Snake(board.getCells()[rowCount / 2][colCount / 2]);

        game = new Game(snake, board);
        timer = new Timer(200, this);

        setTitle("Snake Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(this);
        setFocusable(true);

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame snakeGame = new SnakeGame();
            snakeGame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        if (game.isGameOver()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // Create an off-screen buffer
        Image offScreenBuffer = createImage(getWidth(), getHeight());
        Graphics offScreenGraphics = offScreenBuffer.getGraphics();

        // Paint the game board on the off-screen buffer
        Cell[][] cells = game.getBoard().getCells();
        int cellSize = getWidth() / game.getBoard().getCOL_COUNT();

        for (int row = 0; row < game.getBoard().getROW_COUNT(); row++) {
            for (int col = 0; col < game.getBoard().getCOL_COUNT(); col++) {
                Cell cell = cells[row][col];
                offScreenGraphics.setColor(getCellColor(cell.getCellType()));
                offScreenGraphics.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                offScreenGraphics.setColor(Color.WHITE);
                offScreenGraphics.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        Cell cell = game.getSnake().getHead();
        offScreenGraphics.setColor(new Color(45,180,0));
        offScreenGraphics.fillOval(cell.getCol()*cellSize, cell.getRow()*cellSize,cellSize,cellSize );

        // Draw the off-screen buffer to the actual screen
        g.drawImage(offScreenBuffer, 0, 0, this);
    }

    private Color getCellColor(CellType cellType) {
        return switch (cellType) {
            case EMPTY -> Color.BLACK;
            case FOOD -> Color.RED;
            case SNAKE_NODE -> Color.GREEN;
        };
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if(game.getDirection()!=Game.DIRECTION_DOWN)
                 game.setDirection(Game.DIRECTION_UP);
                break;
            case KeyEvent.VK_DOWN:
                if(game.getDirection()!=Game.DIRECTION_UP)
                 game.setDirection(Game.DIRECTION_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if(game.getDirection()!=Game.DIRECTION_RIGHT)
                 game.setDirection(Game.DIRECTION_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if(game.getDirection()!=Game.DIRECTION_LEFT)
                 game.setDirection(Game.DIRECTION_RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
