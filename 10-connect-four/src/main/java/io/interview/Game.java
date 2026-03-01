package io.interview;

import io.interview.model.Move;
import io.interview.model.Player;

import java.util.Stack;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameState state;
    private Player winner;
    private Stack<Move> moveHistory;

    public Game(Player player1, Player player2){
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = GameState.IN_PROGRESS;
        this.winner = null;
        this.moveHistory = new Stack<>();
    }

    // Revert to the previous move
    public boolean undoLastMove(){
        if(moveHistory.isEmpty()){
            return false;
        }
        Move last = moveHistory.pop();
        board.clearCell(last.getRow(), last.getColumn());
        currentPlayer = last.getPlayer();

        state = GameState.IN_PROGRESS;
        winner = null;
        return true;
    }

    // Make a move, the player will play
    public boolean makeMove(Player player, int column){
        // The game is already in WON or DRAW state
        if(state != GameState.IN_PROGRESS){
            return false;
        }

        // Wrong player's turn. We switch the player on every move
        if(player != currentPlayer){
            return false;
        }

        // Either the disc is placed successfully or it was invalid
        int row = board.placeDisc(column, player.getColor());
        moveHistory.push(new Move(player, row, column));
        if(row == -1){
            return false;
        }

        if(board.checkWin(row, column, player.getColor())){
            state = GameState.WON;
            winner = currentPlayer;
        }else if(board.isFull()){
            state = GameState.DRAW;
        }else{
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        return false;
    }



}