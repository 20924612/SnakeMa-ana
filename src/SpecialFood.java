
import java.awt.Color;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author K501U
 */
public class SpecialFood extends Food {

    private int visibleMilliseconds;
    private static final int MAX_MILLISECONDS = 10000;
    private static final int MIN_MILLISECONDS = 3000;

    public SpecialFood(Snake snake) {
        super(snake);
        visibleMilliseconds = (int) (Math.random() * (MAX_MILLISECONDS - MIN_MILLISECONDS)
                + MIN_MILLISECONDS);
    }

    public int getVisibleMilliseconds() {
        return visibleMilliseconds;
    }

    public void paint(Graphics2D g2d, int squareWidth, int squareHeight) {
        Board.drawSquare(g2d, squareWidth, squareHeight, getRow(), getCol(), Color.BLUE);
    }

}
