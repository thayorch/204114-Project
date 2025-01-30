package entity;

public class Action {

  private final int ACTION_NONE = 0;
  private final int ACTION_SHOOT = 1;
  private final int ACTION_BLOCK = 2;
  private final int ACTION_EVADE = 3;
  private final int ACTION_SHOOT_L = 4;
  private final int ACTION_SHOOT_M = 5;
  private final int ACTION_SHOOT_R = 6;
  private final int ACTION_EVADE_L = 7;
  private final int ACTION_EVADE_R = 8;

  public void addAction(Player player, int actionType){
    switch (actionType) {

      case ACTION_BLOCK:
        player.block++;
        break;

      case ACTION_EVADE:
        player.evade++;
        break;

      case ACTION_EVADE_L:
        player.evade++;
        break;

      case ACTION_EVADE_R:
        player.evade++;
        break;
    }
  }

  public void removeAction(Player player, int actionType) {
    switch (actionType) {

      case ACTION_BLOCK:
        player.block--;
        break;

      case ACTION_EVADE:
        player.evade--;
        break;

      case ACTION_EVADE_L:
        player.evade--;
        break;

      case ACTION_EVADE_R:
        player.evade--;
        break;
    }
  } 
}
