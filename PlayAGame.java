import java.util.Arrays;
import java.util.*;

public class PlayAGame {

   public static void main(String[] args) {
   
      Scanner console = new Scanner(System.in);
      Board b = new Board();
      Player pl = new Player();
      
      int game = choice(console);
   
      if (game == 1) {
         // tictactoe board
         b.showTicBoard();
         pl.ticTurn();
      }
      else {
         // connect 4 board
         b.showConnectBoard();
         pl.connectTurn();
      }
      
      System.out.print("Do you want to play again? ");
      String again = console.next();
      if (again.startsWith("n") || again.startsWith("N")){
         return;
      }
   }
   
   public static int choice(Scanner console) {
      
      while (true){
         System.out.print("What game would you like to play? \n TicTacToe or Connect4 > ");
         String game = console.next();
         
         if (game.toLowerCase().equals("tictactoe")) {
            return 1;
         }
         else if (game.toLowerCase().equals("connect4")) {
            return 2;
         }
      }
   }


}

class Player {

   Scanner input = new Scanner(System.in);
   private static String player = "X";
   Board bd = new Board();
   String[][] connect = bd.getConnectBoard();
   String[][] tic = bd.getTicBoard();

   public static String switchPlayer(String currentPlayer){
      if (currentPlayer == "X"){
         return "O"; 
      } 
      else {
         return "X"; 
      }
   }
   
   public static boolean tryDropPiece(String[][] board, int col, String player){
      
      // Check if the column is full
      if (!board[0][col].equals(" ")){
         System.out.println("That column is already full."); 
         return false;
      } 
      
      // drops the piece to the most bottom
      for (int row = board.length - 1; row >= 0; row--){ 
          if (board[row][col].equals(" ")){
            board[row][col] = player;
            return true; 
          }
      }
      return false;
   }
   
   public void connectTurn() {
      System.out.print("Player " + player + ", please enter the column where you'd like to drop your piece: ");
      int col = input.nextInt();
      
      if (tryDropPiece(connect, col, player)){
   
        // Check for winner
        if(bd.checkForWin(connect)){ 
   
           System.out.println("Player " + player + " wins!"); 
           bd.showConnectBoard();
      
           // To end the game
           return;
             
        }
   
      }
   }  
   public void ticTurn(){
      while (bd.checkWinner(tic) != true) {
         
         // get input from user
         System.out.println("Select a row and a column from [0 - 2] for player " + player + ":");
         int row = input.nextInt();
         int column = input.nextInt();
         
         // if the selected slot is occupied , redo the input
         while (tic[row][column].equals("X") || tic[row][column].equals("O")) {
            System.out.println("This slot is taken. Select another one");
            System.out.println("Select a row and a column from [0 - 2] for player " + player + ":");
            row = input.nextInt();
            column = input.nextInt();
         }

         // place the user's symbol
         tic[row][column] = player;
         bd.showTicBoard();
         
         // checks if a winner is there and then outputs the winner's symbol to the system
         if (bd.checkWinner(tic)) {
            System.out.println("Player " + player + " is the winner!");
         }
         
         player = switchPlayer(player);
         
         // if a draw occurs, it tells the players that the game is a draw
         if (bd.checkWinner(tic) == false) {
            System.out.println("The game ends with a draw.");
         }

      }
   }
}
   
class Board{

   String[][] connect = new String[6][7];
   String[][] tic = new String[3][3];

   public static String[][] connectBoard(String[][] board){
     for (int row = 0; row < board.length; row++){ 
        java.util.Arrays.fill(board[row], 0, board[row].length, " ");
     }
      System.out.println();
      for (int row = 0; row < board.length; row++){
         System.out.print("|");
         for (int col = 0; col < board[row].length; col++){
            System.out.print(" " + board[row][col] + " |");
         }
         System.out.println();
      }
      return board;
   }

   public static String[][] ticBoard(String[][] board) {
   
    String slot = "-";
    
    for (int i = 0; i < 3; i++){
         for (int j = 0; j < 3; j++) {
            board[i][j] = slot;
         }
      } 
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; j++) {
            if (j == board[i].length - 1) {
               System.out.print(board[i][j]);
            }
            else {
               System.out.print( board[i][j] + " | ");
            }
         }
         System.out.println();
      }
      
      return board;
   }
   
    public static boolean checkForWin(String[][]board){
   
      // horizontal
      for (int row = 0; row < board.length; row++){
         for (int col = 0; col < board[row].length - 3; col++){
            if (!board[row][col].equals(" ") && board[row][col] == board[row][col+1] && board[row][col] == board[row][col+2] && board[row][col] == board[row][col +3]){
              return true; 
            }
         } 
      }
      
      // vertical
      for (int col = 0; col < board[0].length; col++){ 
         for (int row = 0; row < board.length - 3; row++){
            if (!board[row][col].equals(" ") && board[row][col] == board[row+1][col] && board[row][col] == board[row+2][col] && board[row][col] == board[row+3][col]){
               return true; 
            }
         } 
      }
      
      // diagonal
      for (int row = 0; row < board.length - 3; row++){
         for (int col = 0; col < board[row].length - 3; col++){
            if (!board[row][col].equals(" ") && board[row][col] == board[row+1][col +1] && board[row][col] == board[row+2][col+2] && board[row][col] == board[row+3][col+3]){
               return true; 
            }
         }
      }
      
      for (int row = 0; row < board.length - 3; row++){ 
         for (int col = 3; col < board[row].length; col++){
            if (!board[row][col].equals(" ") && board[row][col] == board[row+1] [col-1] && board[row][col] == board[row+2][col-2] && board[row] [col] == board[row+3][col-3]){
               return true; 
            }
         } 
      }
      
      return false;
   }
   
   // checks for a winner if a certain win condition is met
   public static boolean checkWinner(String[][] board) {
      
      // checks if there is an empty spot
      boolean taken = true;
      
      // checks for draws or empty slots
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
         
            // checks for empty slots
            if (!board[i][j].equals("O") || !board[i][j].equals("X")) {
               taken = false;
            }
         }
      } 
      
      if (taken) {
         return false;
      }
      
      // checks whether or not the win conditions are fulfilled
      return ((board[0][0] == board [0][1] && board[0][0] == board [0][2]) ||
         (board[0][0] == board [1][1] && board[0][0] == board [2][2]) ||
         (board[0][0] == board [1][0] && board[0][0] == board [2][0]) ||
         (board[2][0] == board [2][1] && board[2][0] == board [2][2]) ||
         (board[2][0] == board [1][1] && board[0][0] == board [0][2]) ||
         (board[0][2] == board [1][2] && board[0][2] == board [2][2]) ||
         (board[0][1] == board [1][1] && board[0][1] == board [2][1]) ||
         (board[1][0] == board [1][1] && board[1][0] == board [1][2])) ;
   
      
   }

   public void showTicBoard() {
      Arrays.toString(ticBoard(tic));
   }
   
   public void showConnectBoard() {
      Arrays.toString(connectBoard(connect));
   }
   
   public String[][] getConnectBoard() {
      return connect;  
   }
   
   public String[][] getTicBoard() {
      return tic;
   }

}