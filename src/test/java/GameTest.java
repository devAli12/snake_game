
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Snake snake;
    private Board board;
    private Game game;

    @BeforeEach
    void setUp() {
        // Create a new board and snake for each test
        board = new Board(10, 10);
        snake = new Snake(new Cell(5, 5));
        game = new Game(snake, board);
    }

    @Test
    void testInitialGameState() {
        assertFalse(game.isGameOver());
        assertNotNull(game.getSnake());
        assertNotNull(game.getBoard());
        assertEquals(0, game.getDirection());
    }

    @Test
    void testSnakeMove() {
        // Initially, snake is at (5,5)
        assertEquals(5, game.getSnake().getHead().getRow());
        assertEquals(5, game.getSnake().getHead().getCol());

        // Move snake to the right
        game.setDirection(Game.DIRECTION_RIGHT);
        game.update();

        // After the update, snake should be at (5,6)
        assertEquals(5, game.getSnake().getHead().getRow());
        assertEquals(6, game.getSnake().getHead().getCol());
    }

    @Test
    void testSnakeGrowth() {
        // Initially, snake has one cell
        assertEquals(1, game.getSnake().getSnakeList().size());

        // Generate food at (5,6) and move the snake there
        board.getCells()[5][6].setCellType(CellType.FOOD);
        game.setDirection(Game.DIRECTION_RIGHT);
        game.update();

        // After eating food, snake should grow by one cell
        assertEquals(2, game.getSnake().getSnakeList().size());
    }

    @Test
    void testGameoverOnCollision() {
        // Initially, the game is not over
        assertFalse(game.isGameOver());
        game.setDirection(Game.DIRECTION_RIGHT);
        board.getCells()[5][6].setCellType(CellType.FOOD);
        // Move snake to the rightgame.update();
        game.update();
        board.getCells()[5][7].setCellType(CellType.FOOD);
        game.update();
        board.getCells()[5][8].setCellType(CellType.FOOD);
        game.update();
        board.getCells()[5][9].setCellType(CellType.FOOD);
        game.setDirection(Game.DIRECTION_DOWN);
        board.getCells()[6][9].setCellType(CellType.FOOD);
        game.update();
        game.setDirection(Game.DIRECTION_LEFT);
        board.getCells()[6][8].setCellType(CellType.FOOD);
        game.update();
        game.setDirection(Game.DIRECTION_UP);
        game.update();
        // Move snake to the right again to cause a collision
        game.setDirection(Game.DIRECTION_RIGHT);
        game.update();
        // After the collision, the game should be over
        assertTrue(game.isGameOver());
    }
}
