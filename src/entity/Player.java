package entity;

import main.KeyHandler;

public class Player{

  KeyHandler keyHand;
  int player_id;
  String p_character;
  int[] bullet = new int[6];
  int[] action = new int[5];

  public Player(KeyHandler keyHand) {
    this.keyHand = keyHand;
  }
}
