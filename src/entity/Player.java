package entity;

import main.*;

public class Player{

  public int player_id, health, accuracy, evade, character;
  public boolean ready = false;
  int[] bullet = new int[6];
  int[] action = new int[5];
  GamePanel gp;
  GameState gs;

  public Player(GamePanel gp, GameState gs, int player_id){
    this.gp = gp;
    this.gs = gs;
  }

  public void setdefaultvalues(int player_id){
    this.player_id = player_id;
    health = 3;
    accuracy = 80;
    evade = 3;
  }
}
