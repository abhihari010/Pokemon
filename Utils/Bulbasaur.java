public class Bulbasaur extends Pokemon{
  private boolean isCharged;
//constructors
  public Bulbasaur(){
    super();
  }

  public Bulbasaur(int row, int col){
    super("Bulbasaur", row, col, 70, 3, 30);
  }

  public boolean getIsCharged(){
    return isCharged;
  }

  //deducts from the amount of hp from the pokemon that is in the parameter by the amount of its strength.
  public void attack(Pokemon p){
    System.out.println("Bulbasaur uses vine whip");
    p.deductHP(super.getStrength());
    System.out.println(p.getName() + " has " + p.getHP() + " HP.");
  }
//Checks if it has charged up, if it hasn't, it will charge up, if it has, it will do 1.5 times the damage. 
  public void doSpecialMove(Pokemon p){
    if(isCharged != true){
      System.out.println("Bulbasaur charges up.");
      isCharged = true;
    }
    else{
      System.out.println("Bulbasar uses solar beam.");
      p.deductHP((int)(super.getStrength() * 1.5));
      System.out.println(p.getName() + " has " + p.getHP() + " HP.");
      isCharged=false;
    }
  }
  public String toString(){
    return (super.toString());
  }
}