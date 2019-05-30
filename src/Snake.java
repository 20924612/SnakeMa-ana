
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
public class Snake {
    
    private List<Node> body;
    private Direction direction;
    private int remainingGrow;

    public Direction getDirection() {
        return direction;
    }

    public void setRemainingGrow(int remainingGrow) {
        this.remainingGrow = remainingGrow;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }    
    
    public Snake(int numNodes) {
        body = new ArrayList<Node>();
        direction = Direction.RIGHT;
        remainingGrow = 0;
        for (int i =0; i< numNodes; i++) {
            body.add(new Node(Config.numRows/2, Config.numCols/2 - i));
        }
                
    }
    
    public void paint(Graphics2D g, int squareWidth, int squareHeight) {
        for (Node node: body) {
            Board.drawSquare(g, squareWidth, squareHeight, node.getRow(), node.getCol(), Color.yellow);
        }
    }
    
    public boolean eat(Food food) {
        if (body.get(0).getRow() == food.getRow() &&
             body.get(0).getCol() == food.getCol() ) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isOnSnake(int row, int col) {
        for (Node node : body) {
            if (row == node.getRow() && col == node.getCol()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean move() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        switch(direction) {
            case UP: return tryToMove(row - 1, col);

            case DOWN: return tryToMove(row + 1, col);

            case LEFT: return tryToMove(row, col - 1);
 
            case RIGHT: return tryToMove(row, col + 1);

        }
        return true;
    }
    
    public boolean tryToMove(int row, int col) {
        if (row < 0 || col < 0 || 
                row >= Config.numRows || col >= Config.numCols) {            
            return false;
        } else { 
            if (collidesWithItself()) {
                return false;
            }  else {
                moveTo(row, col);
                return true;
            }
        }
    }
    
    private void moveTo(int row, int col) {
        body.add(0, new Node(row, col));
        if (remainingGrow == 0) {
            body.remove(body.size() - 1);
        } else {
            remainingGrow --;
        }
    }

    private boolean collidesWithItself() {
        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        for (int i = 1; i < body.size() - 1; i++) {
            if (row == body.get(i).getRow() && 
                col == body.get(i).getCol()) {
                return true;
            }
        }
        return false;
    }
}
