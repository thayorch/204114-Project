package entity;

import main.KeyHandler;

public class Player{

  KeyHandler keyHand;
  int player_id, health, accuracy, evade;
  int[] bullet = new int[6];
  int[] action = new int[5];

  public Player(KeyHandler keyHand) {
    this.keyHand = keyHand;
  }

  public Player(){

  }

  public void setdefaultvalues(int player_id){
    this.player_id = player_id;
    health = 20;
    accuracy = 80;
    evade = 3;
  }
}
