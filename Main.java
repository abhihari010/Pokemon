public class Main{
  public static void main(String Args[]){
    //logic for game to work, first initializes the board and then calls the set up method, which asks the user what they want their pokemon to be. Then takes the turn of the player, and if the game still isn't over, it will take the turn of the CPU. Once the game is over, the result of who wins will print out.
    System.out.println("Welcome to the Pokemon Mystery Dungeon!");
    Board bo1 = new Board();
    bo1.setUp();
    System.out.println();
    while(bo1.isGameOver() != true){
      System.out.println("It is the users Turn");
      bo1.takeTurn("Player");
      if(bo1.isGameOver()!=true){
        System.out.println("It is the CPU's Turn");
        bo1.takeTurn("CPU");
      }
    }
    bo1.result();
  }
}