package entity;

import main.*;

public class Player{

  public int player_id, health, evade, block, character, barrel, actionNum;
  public boolean ready = false;
  private boolean blocked = false, evaded = false;
  private int[] bullet;
  private int[] action;

  private final int EMPTY_BULLET = 0;
  private final int NORMAL_BULLET = 1;
  private final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  private final int SILVER_BULLET = 3; // Garuntee Hit
  private final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.

  private final int NORMAL_DAMAGE = 20;
  private final int MAGIC_DAMAGE = 15;
  private final int SILVER_DAMAGE = 17;
  private final int DEATH_DAMAGE = 9999;

  private int normalBullet = 12, silverBullet = 1, magicBullet = 1, deathBullet = 1;
  private int magicStatus = 0;

  protected final int ACTION_NONE = 0;
  protected final int ACTION_BLOCK = 1;
  protected final int ACTION_SHOOT = 2;
  protected final int ACTION_EVADE = 3;

  GamePanel gp;
  GameState gs;

  public Player(GamePanel gp){
    this.gp = gp;
    setDefaultvalues();
  }

  public Player(){
  }

  public void setDefaultvalues(){
    health = 100;
    evade = 2;
    block = 1;

    barrel = 6;
    actionNum = 5;

    bullet = new int[barrel];
    action = new int[actionNum];
  }

  public void setStartRoundValues(){
    evade = 2 - magicStatus;
    block = 1 - magicStatus;
    magicStatus = 0;
  }

  public int getActionLength(){
    return action.length;
  }

  public int getActionType(int slot){
    return action[slot];
  }

  public int getAction(int actionType){
    switch (actionType) {

      case ACTION_BLOCK:
        return block;

      case ACTION_EVADE:
        return evade;

      default:
        return 0; // ACTION_NONE
    }
  }

  public void addAction(int actionType){
    switch (actionType) {

      case ACTION_BLOCK:
        block++;

      case ACTION_EVADE:
        evade++;
    }
  }

  public void removeAction(int actionType) {
    switch (actionType) {

      case ACTION_BLOCK:
        block--;

      case ACTION_EVADE:
        evade--;
    }
  }

  public void setActionType(int slot, int actionType){
    action[slot] = actionType;
  }

  public int getTotalBullet(){
    if(health > 10)
      return normalBullet + silverBullet + magicBullet;
    return normalBullet + silverBullet + magicBullet + deathBullet;
  }

  public int getBulletType(int slot){
    return bullet[slot];
  }

  public int getBullet(int bulletType){
    switch (bulletType) {

      case NORMAL_BULLET:
        return normalBullet;

      case MAGIC_BULLET:
        return magicBullet;

      case SILVER_BULLET:
        return silverBullet;

      case DEATH_BULLET:
        return deathBullet;

      default:
        return 0; // EMPTY_BULLET
    }
  }

  public void setBulletType(int slot, int bulletType){
    bullet[slot] = bulletType;
  }

  public void addBullet(int bulletType){
    switch (bulletType) {

      case NORMAL_BULLET:
        normalBullet++;
        break;

      case MAGIC_BULLET:
        magicBullet++;
        break;

      case SILVER_BULLET:
        silverBullet++;
        break;

      case DEATH_BULLET:
        deathBullet++;
        break;

    }
  }

  public void removeBullet(int bulletType){
    switch (bulletType) {

      case NORMAL_BULLET:
        normalBullet--;
        break;

      case MAGIC_BULLET:
        magicBullet--;
        break;

      case SILVER_BULLET:
        silverBullet--;
        break;

      case DEATH_BULLET:
        deathBullet--;
        break;
    }
  }

  private double damageDealth (int bulletType){ // Gunner Victim
    double damage = 0;
    switch (bulletType) {
      case NORMAL_BULLET:
        damage = NORMAL_DAMAGE;
        break;
      case MAGIC_BULLET:
        damage = MAGIC_DAMAGE;
        magicStatus = 1;
        break;
      case SILVER_BULLET:
        damage = SILVER_DAMAGE;
        break;
      case DEATH_BULLET:
        health = 0;
        break;
    }
    if(blocked){
      damage = damage - (damage * 0.3);
    }
    return damage;
  }

  public void versusAction(Player player1, Player player2){

  }
}
