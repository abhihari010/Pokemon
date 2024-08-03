public class Squirtle extends Pokemon{

  private int shieldHP;

  public Squirtle(){
    super();
    shieldHP = 0;
  }

  public Squirtle(int row, int col){
    super("Squirtle", row, col, 100, 2, 40);
  }

  public int getShieldHP(){
    return shieldHP;
  }
//deducts hp from the pokemon that is in the parameter, by its strength.
  public void attack(Pokemon p){
    System.out.println("Squirtle uses water gun.");
    p.deductHP(super.getStrength());
    System.out.println(p.getName() + " has " + p.getHP() + " HP."); 
  }
//adds to its shieldHP
  public void doSpecialMove(Pokemon p){
    shieldHP += 25;
    System.out.println("Squirtle uses defense curl. " + p.getName() + " gets flustered.");
  }
//allows for pikachu to deduct from its shield when it has it.
  public void deductShieldHP(int value){
    shieldHP-= value;
  }
  
  public String toString(){
    return (super.toString());
  }
}