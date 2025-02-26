package entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Bullet {
  private final int EMPTY_BULLET = 0;
  private final int NORMAL_BULLET = 1;
  private final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  private final int SILVER_BULLET = 3; // Garuntee Hit
  private final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.

  public Bullet() {
  }
  

  public void setStartRoundValues(Player player) {
    if (player.magicStatus) {
      player.evade = 1;
      player.block = 0;
      player.magicStatus = false;
    }
    if (player.checkForDeathDoor()) {
      player.deathDoor = true;
    }
  }

  public void addBullet(Player player, int bulletType) {
    switch (bulletType) {

      case NORMAL_BULLET:
        player.normalBullet++;
        break;

      case MAGIC_BULLET:
        player.magicBullet++;
        break;
      case SILVER_BULLET:
        player.silverBullet++;
        break;

      case DEATH_BULLET:
        player.deathBullet++;
        break;

    }
  }

  public void removeBullet(Player player, int bulletType) {
    switch (bulletType) {

      case NORMAL_BULLET:
        player.normalBullet--;
        break;

      case MAGIC_BULLET:
        player.magicBullet--;
        break;

      case SILVER_BULLET:
        player.silverBullet--;
        break;

      case DEATH_BULLET:
        player.deathBullet--;
        break;
    }
  }

}
