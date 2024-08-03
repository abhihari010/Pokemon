public class Pikachu extends Pokemon {
  /* ENTER CONSTRUCTORS BELOW */ 
  
  public Pikachu(){
    super();
  }

  public Pikachu(int row, int col){
    super("Pikachu", row, col, 50, 5, 10);
  }
  //attack method specifically for pikachu, checks if it is a squirtle, if it is, it will check if the squirtle has shield, if it does then it will deduct from the shield, and if there is still left over, deduct from its health after, otherwise just normally deduct because no other pokemon has a shield.
  public void attack(Pokemon p) {
    if(p.getName().equalsIgnoreCase("Squirtle")){
      Squirtle s = (Squirtle) p;
      System.out.println("Pikachu uses quick attack on " + s.toString());
      if(s.getShieldHP() == 0){
        s.deductHP(super.getStrength());
        System.out.println("Squirtle has " + s.getHP() + " HP.");
      }
      else if(super.getStrength() <= s.getShieldHP()){
        s.deductShieldHP(super.getStrength());
        System.out.println("Squirtle is shielded and takes no damage");
        System.out.println("Squirtle has " + s.getHP() + " HP.");
      }
      else if(super.getStrength() > s.getShieldHP()){
        int leftOver = super.getStrength() - s.getShieldHP();
        s.deductShieldHP(s.getShieldHP());
        s.deductHP(leftOver);
        System.out.println("Squirtle is shielded, but also takes " + leftOver + " damage.");
        System.out.println("Squirtle has " + s.getHP() + " HP.");
    }
    }
    else{
      System.out.println("Pikachu uses quick attack on " + p.toString());
      p.deductHP(super.getStrength());
      System.out.println(p.getName() + " has " + p.getHP() + " HP.");
    }
  }

  public String toString() {
    return super.toString();
  }

}