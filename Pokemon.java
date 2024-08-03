public class Pokemon {
  private String name;
  private int row;
  private int col;
  private int hp;
  private int speed;
  private int strength;

  public Pokemon() {
    name = "Missingno";
    row = 0;
    col = 0;
    hp = 33;
    speed = 29;
    strength = 136;
  }

  public Pokemon(String name, int row, int col, int hp, int speed, int strength) {
    this.name = name;
    this.row = row;
    this.col = col;
    this.hp = hp;
    this.speed = speed;
    this.strength = strength;
  }

  public String getName() {
    return name;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getHP() {
    return hp;
  }
  
  public int getSpeed() {
    return speed;
  }

  public int getStrength() {
    return strength;
  }
//all of the methods that each of the pokemon perform.
  //adds to the current column and row of the pokemon.
  public void move(int rowAmount, int colAmount) {
    row += rowAmount;
    col += colAmount;
  }
  //deducts the amount of hp that is inputted from the pokemon that is called
  public void deductHP(int value) {
    hp -= value;
    if (hp < 0) {
      hp = 0;
    }
  }

  //weakens the attack of a pokmeon, specifically for charmander.
  public void deductAttack() {
    strength -= (int) (strength/5);
  }
  
  // attack and special move methods for each pokemon.
  public void attack(Pokemon p) {}
  public void doSpecialMove(Pokemon p) {}

  //checks if the pokemon called is close enough to the pokemon given in the paramter.
  public boolean isCloseTo(Pokemon p) {
    int rowDistance = Math.abs(row - p.getRow());
    int colDistance = Math.abs(col - p.getCol());
    int totalDistance = rowDistance + colDistance;
    return speed >= totalDistance;
  }
  
  public String toString() {
    return name + " at (" + row + ", " + col + ") with " + hp + " HP, " + speed + " speed, and " + strength + " strength.";
  }
}