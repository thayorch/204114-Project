package entity;

import client.*;

public class Player {

  private final int EMPTY_BULLET = 0;
  private final int NORMAL_BULLET = 1;
  private final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  private final int SILVER_BULLET = 3; // Garuntee Hit
  private final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.
  
  public static final int ACTION_NONE = 0;
  public static final int ACTION_SHOOT = 1;
  public static final int ACTION_BLOCK = 2;
  public static final int ACTION_EVADE = 3;
  public static final int ACTION_SHOOT_L = 4;
  public static final int ACTION_SHOOT_M = 5;
  public static final int ACTION_SHOOT_R = 6;
  public static final int ACTION_EVADE_L = 7;
  public static final int ACTION_EVADE_R = 8;
  
  private final int NORMAL_DAMAGE = 20;
  private final int MAGIC_DAMAGE = 15;
  private final int SILVER_DAMAGE = 17;
  private final int DEATH_DAMAGE = 9999;
  
  public static final int AMMO = 0; // for inventory
  public static final int ACTION = 1;

  public int health, evade, block, barrel, actionNum, id, character;

  public boolean ready = false, blocked = false, evaded = false; // no static

  public int normalBullet = 12, silverBullet = 1, magicBullet = 1, deathBullet = 1;
  public int currentBarrel = 0, bulletPower = 0;
  public boolean magicStatus = false, deathDoor = false, win = false, duelStatus = false;

  public int[] gun_barrel;
  public int[] player_actions;

  GamePanel gamePanel;
  GameState gameState;
  Bullet bullet = new Bullet();
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

  public Player() {

  }

  public void setCharacter(int character) {
    this.character = character;
  }  

  public void rotateBarrel(int chamber){ // when shoot
    gun_barrel[chamber] = 0; // fired
    currentBarrel = (currentBarrel + 1)%barrel;
  }

  public void damageTake(int bulletType){
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
        damage = DEATH_DAMAGE;
        break;
    }

    if(blocked)
      damage *= 0.8;

    health -= damage;
  }

  public int getAction(int actionType){
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

  public int getActionType_slot(int slot){

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

  public int getActionType_slot_direction(int slot){
    return player_actions[slot];
  }

  public int getActionType(int actionType){
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

  public int getBulletType(int slot){
    return gun_barrel[slot];
  }

  public void setActionType(int slot, int actionType){
    player_actions[slot] = actionType;
  }

  public void setBulletType(int slot, int bulletType){
    gun_barrel[slot] = bulletType;
  }

  public void setDefaultvalues() {
    health = 100;
    evade = 2;
    block = 1;

    barrel = 6;
    actionNum = 5;

    gun_barrel = new int[barrel];
    player_actions = new int[actionNum];
    System.out.printf("[log: set player default values]");
  }

  public void setNewRoundvalues(){
    evade = 2;
    block = 1;
    evaded = false;
    blocked = false;

    if(magicStatus){
      evade = 1;
      block = 0;
      magicStatus = false;
    }

    if(checkForDeathDoor())
      deathDoor = true;

    barrel = 6;
    actionNum = 5;
    currentBarrel = 0;
    player_actions = new int[actionNum];
  }

  public int getTotalBullet(){
    if(health > 10)
      return normalBullet + silverBullet + magicBullet;
    return normalBullet + silverBullet + magicBullet + deathBullet;
  }

  public int getBullets(int bulletType){
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

  public void checkPlayer(int numPress, int type){
    if(type == AMMO){
      checkPlayerAmmo(numPress); // ammoCheck
    }
    else if(type == ACTION){ // action check
      if(numPress >= 4) numPress = 0;
      checkPlayerAction(numPress); // actionCheck
    }
  }

  public void checkPlayerDirection(int numPress, int type){
    if(type == AMMO){
      checkPlayerAmmo(numPress); // ammoCheck
    }
    else if(type == ACTION){ // action check
      checkPlayerAction(numPress); // actionCheck
    }
  }

  public int checkDirection(int numPress, int type){
    if(type == ACTION_SHOOT){
      if(numPress == 1)
        return ACTION_SHOOT_L;
      if(numPress == 2)
        return ACTION_SHOOT_M;
      if(numPress == 3)
        return ACTION_SHOOT_R;
    }
    if(type == ACTION_EVADE){
      if(numPress == 1)
        return ACTION_EVADE_L;
      if(numPress == 3)
        return ACTION_EVADE_R;
    }
    return ACTION_NONE;
  }

  public void checkPlayerAmmo(int bulletType){ // check player barrel when insert bullet, replace current bullet
    
    int slot = GameState.currentSlot;
    if (getBullets(bulletType) > 0 && getBulletType(slot) != bulletType){ // have enogh and not same type
      
      // check for deathDoor
      if(!checkForDeathDoor() && bulletType == DEATH_BULLET)
        bulletType = EMPTY_BULLET;

      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      bullet.removeBullet(this, bulletType); // insert bullet into barrel
      setBulletType(slot, bulletType);
    }

    else if(getBullets(bulletType) == 0 && getBulletType(slot) != EMPTY_BULLET){ // insert out of bullet into barrel
      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      setBulletType(slot, EMPTY_BULLET);
    }

    else if(getBulletType(slot) == bulletType){
      bullet.addBullet(this, getBulletType(slot)); // add back bullet type
      setBulletType(slot, EMPTY_BULLET); // remove bullet
    }

    else 
      setBulletType(GameState.currentSlot, EMPTY_BULLET); // Empty Bullet
  }

  public void checkPlayerAction(int actionType){
    
    int slot = GameState.currentSlot;
    //System.out.println(slot + " " + actionType);
    //System.out.println(actionType == ACTION_SHOOT_L);
    //System.out.println(convertActionType(actionType));
    //System.out.println(getActionType(slot));

    if (getAction(actionType) > 0 && getActionType_slot(slot) != getActionType(actionType)){ //  add getActionType(actionType) for shoot & evade
      action.addAction(this, getActionType_slot(slot));
      action.removeAction(this, getActionType(actionType)); // same reason as condition check
      setActionType(slot, actionType);
    }

    else if(getAction(actionType) == 0 && getActionType_slot(slot) != ACTION_NONE){ 
      action.addAction(this, getActionType_slot(slot)); 
      setActionType(slot, ACTION_NONE);
    }

    else if(getActionType_slot(slot) == getActionType(actionType)){
      action.addAction(this, getActionType_slot(slot));
      setActionType(slot, ACTION_NONE);
    }

    else {
      setActionType(slot, ACTION_NONE); // Action None
    }
  }

  public boolean checkForDeathDoor(){
    if(health >= 10)
      return false;
    return true;
  }

  public void actionCompare(int slot, Player enemey){

    int myAction = getActionType_slot(slot);
    int myActionDirection = player_actions[slot];
    int myBullet = gun_barrel[currentBarrel];

    int enAction = enemey.getActionType_slot(slot);
    int enActionDirection = enemey.player_actions[slot];
    int enBullet = enemey.gun_barrel[enemey.currentBarrel];

    boolean missed = false; // for enemey

    if(myAction == ACTION_BLOCK)
      blocked = true;

    if(myAction == ACTION_EVADE)
      evaded = true;
    
    // me None | blocked, enemey Shoot 
    if((myAction == ACTION_NONE || myAction == ACTION_BLOCK)){

      // hit
      if(enActionDirection == ACTION_SHOOT_M)
        missed = false;

      // miss
      else
        missed = true;
    }

    // me Evade, enemey Shoot
    if(myAction == ACTION_EVADE && enAction == ACTION_SHOOT){ 

      // left
      if (myActionDirection == ACTION_EVADE_L && enActionDirection == ACTION_SHOOT_L)
        missed = false;
      
      // right
      else if (myActionDirection == ACTION_EVADE_R && enActionDirection == ACTION_SHOOT_R)
        missed = false;

    }

    // me shoot, enemey Shoot
    if(myAction == ACTION_SHOOT && enAction == ACTION_SHOOT){

      // left
      if (myActionDirection == ACTION_SHOOT_L && enActionDirection == ACTION_SHOOT_L){
        duelStatus = true;
        missed = false;
      }

      // right
      else if (myActionDirection == ACTION_SHOOT_M && enActionDirection == ACTION_SHOOT_M){
        duelStatus = true;
        missed = false;
      }

      // right
      else if (myActionDirection == ACTION_SHOOT_R && enActionDirection == ACTION_SHOOT_R){
        duelStatus = true;
        missed = false;
      }

      // enemey shoot left, right
      else if (enActionDirection == ACTION_SHOOT_R || enActionDirection == ACTION_SHOOT_L){
        missed = true;
      }
    }
    
    // hit or miss
    if(enAction == ACTION_SHOOT){

      if(!missed){
        damageTake(enBullet);
        enemey.rotateBarrel(enemey.currentBarrel);
      }

      else if(missed){
        if(enBullet == DEATH_BULLET)
          enemey.damageTake(DEATH_BULLET);
        enemey.rotateBarrel(enemey.currentBarrel);
      }
    }
  }
  

  // GET Medthod

  public int getID(){
    return id+1;
  }
  public int getCharacter(){
    return character+1;
  }

  public boolean readyStatus(){
    return ready;
  }

  public int getHealth(){
    return health;
  }

  public boolean getMagigstatus(){
    return magicStatus;
  }

  public boolean getDeathDoor(){
    return deathDoor;
  }

  public boolean getDuelStatus(){
    return duelStatus;
  }

  public int getNormalBullet(){
    return normalBullet;
  }
  public int getSilverBullet(){
    return silverBullet;
  }
  public int getMagicBullet(){
    return magicBullet;
  }
  public int getDeathBullet(){
    return deathBullet;
  }
  public int getBlock(){
    return block;
  }
  public int getEvade(){
    return evade;
  }

  public int[] getGun_barrel(){
    return gun_barrel;
  }
  public int[] getPlayer_actions(){
    return player_actions;
  }


  // for DEBUG
  // public void printInventory(){
  //   System.out.println("===========================");
  //   System.out.printf("player%d - character %d: %s\n", id+1, character, ready);
  //   System.out.printf("hp: %d, magicStatus: %s, deathDoor: %s, duelStatus: %s\n", health, magicStatus, deathDoor, duelStatus);
  //   System.out.printf("n:%d s:%d m:%d d:%d | b:%d e:%d\n", normalBullet, silverBullet, magicBullet, deathBullet, block, evade);
  //   System.out.printf("bullet: [ ");
  //   for (int bullet : gun_barrel) 
  //     System.out.printf("%d ", bullet);

  //   System.out.printf("]\naction: [ ");
  //   for(int action: player_actions)
  //     System.out.printf("%d ", action);
  //   System.out.printf("]\n");

  // }
}
