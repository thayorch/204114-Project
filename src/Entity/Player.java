package entity;

import client.*;

public class Player{

  public int player_id, health, evade, block, barrel, actionNum;
  public String character;
  public int normalBullet = 12;
  public int silverBullet = 1, magicBullet = 1, deathBullet = 1;
  public boolean ready = false;
  public int[] bullet;
  public int[] action;
  GamePanel gamePanel;
  GameState gameState;

  public Player(GamePanel gamePanel){
    this.gamePanel = gamePanel;
    setDefaultvalues();
  }

  public Player(){
  }

  public void setCharacter(String character) {
	  this.character = character;
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
