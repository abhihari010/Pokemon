public class Charmander extends Pokemon{
  
  private int numOfGrowls;
//constructors
  public Charmander(){
    super();
    numOfGrowls = 3;
  }

  public Charmander(int row, int col){
    super("Charmander", row, col, 50, 4, 20);
    numOfGrowls = 3;
  }

  public int getNumOfGrowls(){
    return numOfGrowls;
  }
  //attack method specifically for charmander, decreases the amount of hp to the pokemon called by charmanders strength..
  public void attack(Pokemon p){
    System.out.println("Charmander uses ember.");
    p.deductHP(super.getStrength());
    System.out.println(p.getName() + " has " + p.getHP() + " HP.");
  }
  // special move specifically for charmander, decreases the attack strength of the pokemon targeted at. 
  public void doSpecialMove(Pokemon p){
    numOfGrowls--;
    System.out.print("Charmander uses growl, " + p.getName() + "'s attack is decreased from " + p.getStrength() + " to ");
    p.deductAttack();
    System.out.println(p.getStrength() + ".");
    System.out.println("Charmander has " + numOfGrowls + " growls left.");
  }

  public String toString(){
    return (super.toString());
  }
}