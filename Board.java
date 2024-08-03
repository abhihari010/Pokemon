import java.util.ArrayList;
import java.util.Scanner;
public class Board {
  /* ENTER INSTANCE VARIABLES BELOW */
  private String[][] grid;
  private ArrayList<Pokemon> userPokemon;
  private ArrayList<Pokemon> cpuPokemon;
  private ArrayList<Integer> userIcon;
  private ArrayList<String> cpuIcon;
  private int numOfUserPokemon;
  private int numOfCpuPokemon;


  /* CONSTRUCTOR AND METHODS DEFINED. COMPLETE THEM */
  // Default constructor, this initilizes the grid and also adds to the letters of the cpus pokemon to its corresponding array list of strings, and adds to its array list of pokemon. Then the letters are added to the grid, so that they are actually shown on the grid.
  public Board() {
    grid = new String[9][9];
    userPokemon = new ArrayList<Pokemon>();
    cpuPokemon = new ArrayList<Pokemon>();
    userIcon = new ArrayList<Integer>();
    cpuIcon = new ArrayList<String>();
    numOfUserPokemon = 3;
    numOfCpuPokemon = 3;
    cpuPokemon.add(new Pikachu(0,2));
    cpuPokemon.add(new Pikachu(0,4));
    cpuPokemon.add(new Pikachu(0,6));
    cpuIcon.add("A");
    cpuIcon.add("B");
    cpuIcon.add("C");
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        grid[i][j] = "-";
      }
    }
    char letter = 'A';
    for(int col = 2; col < grid[0].length-2; col+=2){
      grid[0][col] = String.valueOf(letter);
      letter = (char)(letter + 1);
    }
    
  }

  // this method shows the grid for the game by utilizing a nested for loop 
  public void show() {
    for(int row = 0; row < grid.length; row++){
      System.out.print("       ");
      for(int col = 0; col < grid[0].length; col++){
        System.out.print(grid[row][col]);
      }
      System.out.println();
    }

  }
    // checks if the game is over by checking if either the cpu's pokemon or users pokemon has run out, or is at 0 in this case.
  public boolean isGameOver() {
    if(numOfUserPokemon == 0 || numOfCpuPokemon == 0){
      return true;
    }
  return false;
  }

  // this prints out the result after the game over method turns out to be true, it checks if the user has 0 pokemon left, if they do, they lost, if the cpu has 0 left, the player wins, then the congratulations print statement is printed out. 
  public void result(){
    if(numOfUserPokemon == 0){
      System.out.println("CPU Wins; you suck!");
    }
    if(numOfCpuPokemon == 0){
      System.out.println("Congratulations; you won");
    }
  }

  // this method sets up the game by asking the user which pokemon they want in their set of three, by utilizing a while loop, the question is able to be asked three times, and this method also ensures that the user puts a valid pokemon type, if they don't it will keep asking the question. Once they input the three pokemon they want, it is stored into both their integer and pokemon array lists, and also put into the grid, to be presented during the game.
  public void setUp() {
    Scanner sc = new Scanner(System.in);
    int teamMemberNum = 1;
    int row = 8;
    int col = 2;
    while(teamMemberNum <= 3){
      System.out.print("What type of pokemon should team member #" + teamMemberNum + " be? (\"b\" for Bulbasaur, \"c\" for Charmander, \"s\" for Squirtle)");
      String type = sc.nextLine();
      while(!type.equalsIgnoreCase("b") && !type.equalsIgnoreCase("c") && !type.equalsIgnoreCase("s")){
        System.out.print("Invalid input; try again: (\"b\" for Bulbasaur, \"c\" for Charmander, \"s\" for Squirtle)");
        type = sc.nextLine();
      }
      
      if(type.equalsIgnoreCase("b")){
        userPokemon.add(new Bulbasaur(row, col));
      }
      
      if(type.equalsIgnoreCase("c")){
        userPokemon.add(new Charmander(row, col));
      }     
    
      if(type.equalsIgnoreCase("s")){
        userPokemon.add(new Squirtle(row, col));
      }
        
      grid[row][col] = String.valueOf(teamMemberNum);
      col +=2;
      userIcon.add(teamMemberNum);
      teamMemberNum++;
      }
}


  public void takeTurn(String player) {
    Scanner sc = new Scanner(System.in);
    //this is the players turn, which utlizes a for loop to ask the player what they want to do for each of their pokemon. They can either move, attack, or do a special move. If the user doesn't input any valid commands, the question will be asked again until they do. Once they enter a valid command, it is checked if it is move, attack, or special, and whatever letter they choice, the method that corresponds to it will output. 
    if(player.equals("Player")){
      int pokemonNum = 0;
      while(pokemonNum < userPokemon.size() && isGameOver() != true){
        Pokemon temp = userPokemon.get(pokemonNum);
        show();
        System.out.println("Pokemon #" + userIcon.get(pokemonNum) + " is " + temp.toString());
        System.out.print("What would you like to do? (\"m\" for move, \"a\" for Attack, \"s\" for Special.) ");
        String choice = sc.nextLine();
        while(!choice.equalsIgnoreCase("m") && !choice.equalsIgnoreCase("a") && !choice.equalsIgnoreCase("s")){
          System.out.print("What would you like to do? (\"m\" for move, \"a\" for Attack, \"s\" for Special.) ");
          choice = sc.nextLine();
        }
        // this is the move method, it will ask the user what row and column they want to move to, but they have to enter a valid row and column for it to work, it has to be in range, on the board, and not have another pokemon at that location. Once a valid row and column are inputted, the pokemon is moved, and it is the next pokemons turn. 
        if(choice.equalsIgnoreCase("m")){
          boolean invalid1 = true;
          int choiceRow;
          int choiceCol;
          do{
          System.out.print("Choose a row you would like to move to: ");
          choiceRow = sc.nextInt();
          System.out.print("Choose a col you would like to move to: ");
          choiceCol = sc.nextInt();
          if(choiceRow > grid.length-1 || choiceRow < 0 || choiceCol > grid[0].length-1 || choiceCol < 0){
            System.out.println("That location is not on the board! Try again.");
            System.out.println();
          }
            
          else if(Math.abs(choiceRow - temp.getRow()) + Math.abs(choiceCol - temp.getCol()) > temp.getSpeed()){
            System.out.println("You picked a location too far away! Try again.");
            System.out.println();
          }

          else if(choiceRow == temp.getRow() && choiceCol == temp.getCol()) {
            invalid1 = false;
          }
          else if(!(grid[choiceRow][choiceCol].equals("-"))){
            System.out.println("Uh oh, a Pokémon is already at that location! Try again.");
            System.out.println();
          }
          else{
            invalid1 = false;
          }
          }while(invalid1);

          grid[temp.getRow()][temp.getCol()] = "-";
          temp.move(choiceRow - temp.getRow(), (choiceCol - temp.getCol()));
          grid[choiceRow][choiceCol] = Integer.toString(userIcon.get(pokemonNum));
          System.out.println("Pokemon #" + userIcon.get(pokemonNum) + " is now at (" + choiceRow + ", " + choiceCol + ").");
          pokemonNum++;
                    
        }
        
        // this is the attack method, if the user chooses to attack, they will be prompted with the question of which pokemon they want to attack. They have to choose a pokemon that is within range, if they don't they will be asked the general question of whether they want to move, attack, or do special move again. Once they enter a valid input, the pokemon will attack its intended target, and if that pokemons health reaches 0, that pokemon will be removed from the game.
        if(choice.equalsIgnoreCase("a")){
          boolean invalid2 = true;
          String attackChoice;
          do{
            System.out.println("Select an opponent to attack: ");
            for(int i = 0; i < cpuIcon.size(); i++){
              System.out.println(cpuIcon.get(i) + ": " + cpuPokemon.get(i).toString());
            }
            attackChoice = sc.nextLine();
            if(!(attackChoice.equalsIgnoreCase("a")) && !(attackChoice.equalsIgnoreCase("b")) && !(attackChoice.equalsIgnoreCase("c"))){
              System.out.println("Invalid Input. Try again: ");
            }
            
            else{
              invalid2 = false;
           }
          }while(invalid2);
        
          if(attackChoice.equalsIgnoreCase("a") || attackChoice.equalsIgnoreCase("b") || attackChoice.equalsIgnoreCase("c")){
            if(temp.isCloseTo(cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase()))) != true){
              System.out.println(temp.getName() + " is too far away from " + cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())).getName());
              
              System.out.println();
            }
              
            else{
              temp.attack(cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())));
              if(cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())).getHP() <= 0){
                System.out.println(cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())).getName() + " has fainted");
                grid[cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())).getRow()][cpuPokemon.get(cpuIcon.indexOf(attackChoice.toUpperCase())).getCol()] = "-";
                cpuPokemon.remove(cpuIcon.indexOf(attackChoice.toUpperCase()));
                cpuIcon.remove(cpuIcon.indexOf(attackChoice.toUpperCase()));
                numOfCpuPokemon--;
              }
              pokemonNum++;
            }
          }
          
        }

        // this is the special move. If the user chooses to use their special move, they will be asked which pokemon they want to use it on. If they are too far away from their intended pokemon, it will ask the general question of whether they want to move, attack, or special, again. Once they are close enough and their intended target is a valid input, it will do their special move on their intended target. However, for squirtle, he can use his special move anywhere on the board, he doesn't have to be close enough to his target. Then if the special move causes a pokemons health to go to 0, that pokemon will be removed from the game and the cpus array lists. 
        if(choice.equalsIgnoreCase("s")){       
          String specialChoice;
          boolean invalid3 = true;
          do{
          System.out.println("Select an opponent: ");
          for(int i = 0; i < cpuIcon.size(); i++){
            System.out.println(cpuIcon.get(i) + ": " + cpuPokemon.get(i).toString());
        }
          specialChoice = sc.nextLine();
          if(!(specialChoice.equalsIgnoreCase("a")) && !(specialChoice.equalsIgnoreCase("b")) && !(specialChoice.equalsIgnoreCase("c"))){
            System.out.println("Not a valid choice. Try again.");
            System.out.println("Select an opponent: ");
            specialChoice = sc.nextLine();
          }

          else{
            invalid3 = false;
          }
          }while(invalid3);
          
          if(specialChoice.equalsIgnoreCase("a") || specialChoice.equalsIgnoreCase("b") || specialChoice.equalsIgnoreCase("c")){
            if(temp instanceof Squirtle == true){
              temp.doSpecialMove(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())));  
              pokemonNum++;
            }
            
            else if(temp.isCloseTo(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase()))) != true){
              System.out.println(temp.getName() + " is too far away from " +       cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())).getName());    
            }
              
            else if(temp.isCloseTo(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase()))) == true){
              temp.doSpecialMove(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())));                                           if(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())).getHP() <= 0){
                System.out.println(cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())).getName() + " has fainted");
                grid[cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())).getRow()][cpuPokemon.get(cpuIcon.indexOf(specialChoice.toUpperCase())).getCol()] = "-";
                cpuPokemon.remove(cpuIcon.indexOf(specialChoice.toUpperCase()));
                cpuIcon.remove(cpuIcon.indexOf(specialChoice.toUpperCase()));
                numOfCpuPokemon--;
              }
              pokemonNum++;
            }
          
          }
        }
      }
    }
        //this is the cpus turn, this is a while loop that goes through each of the cpus pokemon and does a turn for each of them.  
    if(player.equals("CPU")){

      boolean hasAttacked = false;
      int cpuNum = 0;
      while(cpuNum < cpuPokemon.size() && isGameOver() != true){
        Pokemon temp2 = cpuPokemon.get(cpuNum);
        show();
        //this checks if the cpus pokemon is close enough to any of the users pokemon, if it is, it will attack said pokemon. Then it will check if the users pokemon has reached 0 health, if it has, it will remove that pokemon from the game and both of the users array lists, then it will be the next pokemons turn in the loop. If the cpus pokemon isn't close enough to any of the users pokemon, it will instead randomize a spot for said pokemon to move on the board, but this randomization will be ensured that it is within that pokemons speed.
        System.out.println("Pokemon " + cpuIcon.get(cpuNum) + " " + temp2.toString());
        for(int i = 0; i < userPokemon.size(); i++){
          if(temp2.isCloseTo(userPokemon.get(i)) == true){
            temp2.attack(userPokemon.get(i));
            hasAttacked = true;
            if(userPokemon.get(i).getHP() <= 0){
              System.out.println(userPokemon.get(i).getName() + " has fainted");
              grid[userPokemon.get(i).getRow()][userPokemon.get(i).getCol()] = "-";
              userPokemon.remove(i);
              userIcon.remove(i);
              numOfUserPokemon--;
            }
          i = userPokemon.size();
          }
        } 

        //Randomizes a spot for the cpus pokemon to go if they aren't close enough to any of the users pokemon to attack, while ensuring that is within said pokemons speed.
          if(hasAttacked == false){
            boolean invalid4 = true;
            int randRow;
            int randCol;
            do{
            randRow = (int)(Math.random() * grid.length);
            randCol = (int)(Math.random() * grid[0].length);
            if(randRow > grid.length-1 || randRow < 0 || randCol > grid[0].length-1 || randCol < 0){
            System.out.println("That location is not on the board! Try again.");
            System.out.println();
            }
            else if(Math.abs(randRow - temp2.getRow()) + Math.abs(randCol - temp2.getCol()) > temp2.getSpeed()){
              randRow = (int)(Math.random() * grid.length);
              randCol = (int)(Math.random() * grid[0].length);
            }
            else if(randRow == temp2.getRow() && randCol == temp2.getCol()){
              invalid4 = false;
            }
            else if(!(grid[randRow][randCol].equals("-"))){
              randRow = (int)(Math.random() * grid.length);
              randCol = (int)(Math.random() * grid[0].length);
            }

            else{
              invalid4 = false;
            } 
          }while(invalid4);

            System.out.println("Pokemon " + cpuIcon.get(cpuNum) + " is now at (" + randRow + ", " + randCol + ").");
            grid[temp2.getRow()][temp2.getCol()] = "-";
            temp2.move(randRow - temp2.getRow(), (randCol - Math.abs(temp2.getCol())));
            grid[randRow][randCol] = cpuIcon.get(cpuNum); 
          }
          cpuNum++;
          }
        }
      }

  // Clears the console. DO NOT use this until your game is finalized!
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}