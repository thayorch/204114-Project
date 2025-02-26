package entity;

import client.*;

public class Player {

  private final int EMPTY_BULLET = 0;
  private final int NORMAL_BULLET = 1;
  private final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  private final int SILVER_BULLET = 3; // Garuntee Hit
  private final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.

  private final int NORMAL_DAMAGE = 20;
  private final int MAGIC_DAMAGE = 15;
  private final int SILVER_DAMAGE = 17;
  private final int DEATH_DAMAGE = 9999;

  private final int NORMAL_AMOUNT = 12; // default 12
  private final int MAGIC_AMOUNT = 1; // default 1
  private final int SILVER_AMOUNT = 1; // default 1
  private final int DEATH_AMOUNT = 1; // default 1

  // ACTION
  public static final int ACTION_NONE = 0;
  public static final int ACTION_SHOOT = 1;
  public static final int ACTION_BLOCK = 2;
  public static final int ACTION_EVADE = 3;
  public static final int ACTION_EVADE_L = 4;
  public static final int ACTION_EVADE_R = 5;
  public static final int ACTION_SHOOT_L = 6;
  public static final int ACTION_SHOOT_M = 7;
  public static final int ACTION_SHOOT_R = 8;

  public static final int AMMO = 0; // for inventory
  public static final int ACTION = 1;

  // animation idel 0
  // animation shoot 1
  // animation block 2
  // animation evade 3 4 l,5 r
  // animation hurt 6 ? do you guys want hurt animation?

  public int animationType = ACTION_NONE;

  public int health, evade, block, barrel, actionNum, id, character;

  public boolean ready = false, blocked = false, evaded = false; // no static

  public int normalBullet = NORMAL_AMOUNT, silverBullet = SILVER_AMOUNT, magicBullet = MAGIC_AMOUNT,
      deathBullet = DEATH_AMOUNT;
  public int currentBarrel = 0, bulletPower = 0;
  public boolean magicStatus = false, deathDoor = false, win = false, duelStatus = false;

  public int[] gun_barrel;
  public int[] player_actions;

  public int shoot_value;

  GamePanel gamePanel;
  GameState gameState;
  public Bullet bullet = new Bullet();
  Action action = new Action();

  public Player(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    setDefaultvalues();
  }

  public Player(GameState gamState, int id) {
    this.gameState = gameState;
    this.id = id;
    setDefaultvalues();
  }

  public void setCharacter(int character) {
    this.character = character;
  }

  public void rotateBarrel(int chamber) { // when shoot
    gun_barrel[chamber] = 0; // fired
    currentBarrel = (currentBarrel + 1) % barrel;
  }

  public void damageTake(int bulletType) {
    int damage = 0;

    switch (bulletType) {
      case NORMAL_BULLET:
        damage = NORMAL_DAMAGE;
        break;
      case MAGIC_BULLET:
        damage = MAGIC_DAMAGE;
        break;
      case SILVER_BULLET:
        damage = SILVER_DAMAGE;
        break;
      case DEATH_BULLET:
        health = 0;
        damage = (int) (DEATH_DAMAGE * 1.25) + 1;
        break;
    }

    if (blocked) {
      damage *= 0.5;
      blocked = false;
    }

    health -= damage;
  }

  public int getAction(int actionType) {
    switch (actionType) {

      case ACTION_SHOOT:
        return 1; // alway shoot

      case ACTION_SHOOT_L:
        return 1; // alway shoot

      case ACTION_SHOOT_M:
        return 1; // alway shoot

      case ACTION_SHOOT_R:
        return 1; // alway shoot

      case ACTION_BLOCK:
        return block;

      case ACTION_EVADE:
        return evade;

      case ACTION_EVADE_L:
        return evade;

      case ACTION_EVADE_R:
        return evade;

      default:
        return ACTION_NONE;
    }
  }

  public int getActionType_slot(int slot) {

    int type = player_actions[slot];
    switch (type) {

      case ACTION_SHOOT_L:
        return ACTION_SHOOT;

      case ACTION_SHOOT_M:
        return ACTION_SHOOT;

      case ACTION_SHOOT_R:
        return ACTION_SHOOT;

      case ACTION_EVADE_L:
        return ACTION_EVADE;

      case ACTION_EVADE_R:
        return ACTION_EVADE;

      default:
        return player_actions[slot];
    }
  }

  public int getActionType_slot_direction(int slot) {
    return player_actions[slot];
  }

  public int getActionType(int actionType) {
    switch (actionType) {

      case ACTION_SHOOT_L:
        return ACTION_SHOOT;

      case ACTION_SHOOT_M:
        return ACTION_SHOOT;

      case ACTION_SHOOT_R:
        return ACTION_SHOOT;

      case ACTION_EVADE_L:
        return ACTION_EVADE;

      case ACTION_EVADE_R:
        return ACTION_EVADE;

      default:
        return actionType;
    }
  }

  public void setActionType(int slot, int actionType) {
    player_actions[slot] = actionType;
  }

  public int getBulletType(int slot) {
    return gun_barrel[slot];
  }

  public void setBulletType(int slot, int bulletType) {
    gun_barrel[slot] = bulletType;
  }

  public void setDefaultvalues() {
    health = 100;
    evade = 2;
    block = 1;
    normalBullet = NORMAL_AMOUNT;
    silverBullet = SILVER_AMOUNT;
    magicBullet = MAGIC_AMOUNT;
    deathBullet = DEATH_AMOUNT;
    bulletPower = 0;
    win = false;

    barrel = 6;
    actionNum = 5;
    currentBarrel = 0;

    gun_barrel = new int[barrel];
    player_actions = new int[actionNum];
    System.out.println("[log: set player default values]");
  }

  public void setNewRoundvalues() {
    evade = 2;
    block = 1;
    evaded = false;
    blocked = false;
    bulletPower = 0;

    if (magicStatus) {
      evade = 1;
      block = 0;
      magicStatus = false;
    }

    if (checkForDeathDoor())
      deathDoor = true;

    barrel = 6;
    actionNum = 5;
    currentBarrel = 0;
    player_actions = new int[actionNum];
  }

  public int getTotalBullet() {
    return normalBullet + silverBullet + magicBullet;
  }

  public int getBullets(int bulletType) {
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
        return EMPTY_BULLET; // EMPTY_BULLET
    }
  }

  public void checkPlayer(int numPress, int type) {
    if (type == AMMO) {
      checkPlayerAmmo(numPress); // ammoCheck
    } else if (type == ACTION) { // action check
      if (numPress >= 4)
        numPress = 0;
      checkPlayerAction(numPress); // actionCheck
    }
  }

  public void checkPlayerDirection(int numPress, int type) {
    if (type == AMMO) {
      checkPlayerAmmo(numPress); // ammoCheck
    } else if (type == ACTION) { // action check
      checkPlayerAction(numPress); // actionCheck
    }
  }

  public int checkDirection(int numPress, int type) {
    if (type == ACTION_SHOOT) {
      if (numPress == 1)
        return ACTION_SHOOT_L;
      if (numPress == 2)
        return ACTION_SHOOT_M;
      if (numPress == 3)
        return ACTION_SHOOT_R;
    }
    if (type == ACTION_EVADE) {
      if (numPress == 1)
        return ACTION_EVADE_L;
      if (numPress == 3)
        return ACTION_EVADE_R;
    }
    return ACTION_NONE;
  }

  public void checkPlayerAmmo(int bulletType) { // check player barrel when insert bullet, replace current bullet

    int slot = GameState.currentSlot;
    if (getBullets(bulletType) > 0 && getBulletType(slot) != bulletType) { // have enogh and not same type

      // check for deathDoor
      if (!checkForDeathDoor() && bulletType == DEATH_BULLET)
        bulletType = EMPTY_BULLET;

      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      bullet.removeBullet(this, bulletType); // insert bullet into barrel
      setBulletType(slot, bulletType);
    }

    else if (getBullets(bulletType) == 0 && getBulletType(slot) != EMPTY_BULLET) { // insert out of bullet into barrel
      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      setBulletType(slot, EMPTY_BULLET);
    }

    else if (getBulletType(slot) == bulletType) {
      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      setBulletType(slot, EMPTY_BULLET); // remove bullet
    }

    else
      setBulletType(GameState.currentSlot, EMPTY_BULLET); // Empty Bullet
  }

  public void checkPlayerAction(int actionType) {

    int slot = GameState.currentSlot;

    if (getAction(actionType) > 0 && getActionType_slot(slot) != getActionType(actionType)) { // add
                                                                                              // getActionType(actionType)
                                                                                              // for shoot & evade
      action.addAction(this, getActionType_slot(slot));
      action.removeAction(this, getActionType(actionType)); // same reason as condition check
      setActionType(slot, actionType);
    }

    else if (getAction(actionType) == 0 && getActionType_slot(slot) != ACTION_NONE) {
      action.addAction(this, getActionType_slot(slot));
      setActionType(slot, ACTION_NONE);
    }

    else if (getActionType_slot(slot) == getActionType(actionType)) {
      action.addAction(this, getActionType_slot(slot));
      setActionType(slot, ACTION_NONE);
    }

    else {
      setActionType(slot, ACTION_NONE); // Action None
    }
  }

  public boolean checkForDeathDoor() {
    if (health >= 10)
      return false;
    return true;
  }

  public void actionCompare(int slot, Player enemey) {
    int myAction = getActionType_slot(slot);
    int myActionDirection = player_actions[slot];
    int myBullet = gun_barrel[currentBarrel];

    int enAction = enemey.getActionType_slot(slot);
    int enActionDirection = enemey.player_actions[slot];
    int enBullet = enemey.gun_barrel[enemey.currentBarrel];

    boolean missed = true; // for enemey

    if (myAction == ACTION_NONE)
      animationType = ACTION_NONE;

    if (getActionType(myAction) == ACTION_SHOOT)
      animationType = ACTION_SHOOT;

    if (myAction == ACTION_BLOCK) {
      animationType = ACTION_BLOCK;
      blocked = true;
    }

    if (getActionType(myAction) == ACTION_EVADE)
      animationType = ACTION_EVADE_L;
    if (myAction == ACTION_EVADE_R)
      animationType = ACTION_EVADE_R;
    evaded = true;

    if (enBullet == SILVER_BULLET) // always hit
      missed = false;

    // me None | blocked, enemey Shoot
    if ((myAction == ACTION_NONE || myAction == ACTION_BLOCK) && enAction == ACTION_SHOOT) {

      // blocked always got hit
      if (enAction == ACTION_SHOOT && myAction == ACTION_BLOCK)
        missed = false;

      else if (enActionDirection == ACTION_SHOOT_M)
        missed = false;
    }

    // me Evade, enemey Shoot
    if (myAction == ACTION_EVADE && enAction == ACTION_SHOOT) {

      // left
      if (myActionDirection == ACTION_EVADE_L && enActionDirection == ACTION_SHOOT_L) {
        animationType = ACTION_EVADE_L;
        evaded = false;
        missed = false;
      }

      // right
      else if (myActionDirection == ACTION_EVADE_R && enActionDirection == ACTION_SHOOT_R) {
        animationType = ACTION_EVADE_R;
        evaded = false;
        missed = false;
      }
    }

    // me shoot, enemey Shoot
    if (myAction == ACTION_SHOOT && enAction == ACTION_SHOOT) {

      // EMPTY_BULLET
      if (myBullet == EMPTY_BULLET || enBullet == EMPTY_BULLET) {
        duelStatus = false;
      }

      // SILVER_BULLET
      else if (myBullet == SILVER_BULLET || enBullet == SILVER_BULLET) {
        duelStatus = true;
        missed = false;
      }

      // match
      else if (myActionDirection == enActionDirection) {
        duelStatus = true;
        missed = false;
      }

      // enemey shoot middle and me shoot left & right
      if (enActionDirection == ACTION_SHOOT_M && myActionDirection != ACTION_SHOOT_M)
        missed = false;

    }

    // hit or miss
    if (enAction == ACTION_SHOOT && !duelStatus) {

      if (!missed) {

        animationType = 4; // hurt

        if (enBullet == MAGIC_BULLET)
          magicStatus = true;

        damageTake(enBullet);
        enemey.rotateBarrel(enemey.currentBarrel);
      }

      else if (missed) {
        if (enBullet == DEATH_BULLET)
          enemey.damageTake(DEATH_BULLET);
        enemey.rotateBarrel(enemey.currentBarrel);
      }
    }

    // reset
    evaded = false;
    blocked = false;
  }

  // dueling
  public void duelingQTE(Player enemey) {
    int myPower = bulletPower;
    int enPower = enemey.bulletPower;

    int myBullet = gun_barrel[currentBarrel];
    int enBullet = enemey.gun_barrel[enemey.currentBarrel];

    if (myPower <= enPower && enPower != 0) {

      if (enBullet == MAGIC_BULLET)
        magicStatus = true;

      damageTake(enBullet);
      System.out.println(id + 1 + " take damage");
    }
  }

  public boolean QTE(Player enemey) {
    int myPower = bulletPower;
    int enPower = enemey.bulletPower;
    return (myPower <= enPower && enPower != 0);
  }

  // GET Medthod

  public int getID() {
    return id + 1;
  }

  public int getCharacter() {
    return character + 1;
  }

  public boolean readyStatus() {
    return ready;
  }

  public int getHealth() {
    return health;
  }

  public boolean getMagigstatus() {
    return magicStatus;
  }

  public boolean getDeathDoor() {
    return deathDoor;
  }

  public boolean getDuelStatus() {
    return duelStatus;
  }

  public int getNormalBullet() {
    return normalBullet;
  }

  public int getSilverBullet() {
    return silverBullet;
  }

  public int getMagicBullet() {
    return magicBullet;
  }

  public int getDeathBullet() {
    return deathBullet;
  }

  public int getBlock() {
    return block;
  }

  public int getEvade() {
    return evade;
  }

  public int[] getGun_barrel() {
    return gun_barrel;
  }

  public int[] getPlayer_actions() {
    return player_actions;
  }

  public int getBulletPower() {
    return bulletPower;
  }
}
