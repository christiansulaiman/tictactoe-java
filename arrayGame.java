import java.util.Scanner;

public class arrayGame {

   public static void main(String[] args) {
   
      Scanner console = new Scanner(System.in);
      int row;
      int column;
      char user = 'X';
      
      // creates the 2d array for the game board
      char[][] board = new char[3][3];
      
      // initializes each slot as a number
      char slot = '1';
      
      // outside loop for the row
      for (int i = 0; i < 3; i++){
         
         // inner loop for the column
         for (int j = 0; j < 3; j++) {
            board[i][j] = slot++;
         }
      } 
      
      display(board);
      while (!checkWinner(board) == true) {
         
         // get input from user
         System.out.println("Select a row and a column from [0 - 2] for player " + user + ":");
         row = console.nextInt();
         column = console.nextInt();
         
         // if the selected slot is occupied , redo the input
         while (board[row][column] == 'X' || board[row][column] == 'O') {
            System.out.println("This slot is taken. Select another one");
            System.out.println("Select a row and a column from [0 - 2] for player " + user + ":");
            row = console.nextInt();
            column = console.nextInt();
         }
         
         // place the user's symbol
         board[row][column] = user;
         display(board);
         
         // checks if a winner is there and then outputs the winner's symbol to the system
         if (checkWinner(board)) {
            System.out.println("Player " + user + " is the winner!");
         }
         
         // swaps user symbol
         if (user == 'X') {
            user = 'O';
         }
         else {
            user = 'X';
         }
         
      }
     
      // if a draw occurs, it tells the players that the game is a draw
      if (checkWinner(board) == false) {
         System.out.println("The game ends with a draw.");
      }
   
   }
   
   // method to display the board using loops 
   public static void display(char[][] board) {
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
   }
   
   // checks for a winner if a certain win condition is met
   public static boolean checkWinner(char[][] board) {
      
      // checks if there is an empty spot
      boolean taken = true;
      
      // checks for draws or empty slots
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
         
            // checks for empty slots
            if (board[i][j] != 'O' || board[i][j] != 'X') {
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
   
   
}