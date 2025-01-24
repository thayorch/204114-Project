package entity;

import main.*;

public class Player{

  public int player_id, health, evade, block, character, barrel, actionNum;
  public int normalBullet = 12;
  public int silverBullet = 1, magicBullet = 1, deathBullet = 1;
  public boolean ready = false;
  public int[] bullet;
  public int[] action;
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

  public int getTotalBullet(){
    if(health > 10)
      return normalBullet + silverBullet + magicBullet;
    return normalBullet + silverBullet + magicBullet + deathBullet;
  }
}
