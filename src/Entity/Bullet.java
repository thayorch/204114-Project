package entity;

public class Bullet {
	protected final int EMPTY_BULLET = 0;
	protected final int NORMAL_BULLET = 1;
	protected final int MAGIC_BULLET = 2; // Reduce Evade & BLock
	protected final int SILVER_BULLET = 3; // 100% Hit
	protected final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.
	
	protected final int ACTION_NONE = 0;
	protected final int ACTION_GUARD = 1;
	protected final int ACTION_SHOOT = 2;
	protected final int ACTION_EVADE = 3;
	
	protected int currentSlot = 0;
	protected int currentType = 0; // 0 load ammo, 1 select action
	protected int currentAction = 0;
	protected int currentP1Action = 0;
	protected int currentP2Action = 0;
	protected int bulletType = 0;
	protected int round = 1;
		
	
	
	 
}

class Bullet{
	 private void checkPlayerAmmo(int bulletType, Player player){ // check player when choose bullet, replace current bullet
		    switch (bulletType) {
		      case EMPTY_BULLET:
		        player.bullet[currentSlot] = EMPTY_BULLET;
		        setPlayerAmmoBack(player, bulletType);
		        break;

		      case NORMAL_BULLET:
		        player.bullet[currentSlot] = EMPTY_BULLET;
		        if (player.normalBullet > 0 && player.bullet[currentSlot] != NORMAL_BULLET){
		          setPlayerAmmoBack(player); // add back bullet type
		          player.normalBullet--;
		          player.bullet[currentSlot] = bulletType;
		        }
		        break;

		      case MAGIC_BULLET:
		        player.bullet[currentSlot] = EMPTY_BULLET;
		        if (player.bullet[currentSlot] != MAGIC_BULLET){
		          setPlayerAmmoBack(player);
		          player.magicBullet--;
		          player.bullet[currentSlot] = bulletType;
		        }
		        break;

		      default:
		        break;
		    }

		  }

		  private void setPlayerAmmoBack(Player player){ // re add bullet to pool
		    switch (player.bullet[currentSlot]) {
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

		  private void setPlayerAmmoBack(Player player, int bulletType){ // re add bullet to pool
		    switch (player.bullet[currentSlot]) {

		      case EMPTY_BULLET:
		        if(bulletType == NORMAL_BULLET) player.normalBullet++;
		        if(bulletType == DEATH_BULLET) player.deathBullet++;
		        if(bulletType == SILVER_BULLET) player.silverBullet++;
		        if(bulletType == MAGIC_BULLET) player.magicBullet++;
		        break;

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

		  private void ammoUpdate(){

		    int TOTAL_BARREL = 6;  
		    int TOTAL_ACTION = 5;
		    int TOTAL_SLOT   = 0;
		    int[] SLOT;

		    switch (currentType) {
		      case 0:
		        TOTAL_SLOT = TOTAL_BARREL;
		        break;
		      case 1:
		        TOTAL_SLOT = TOTAL_ACTION;
		        break;
		    }

		    if(keyHand.ePressed){
		      keyHand.ePressed = false;
		      currentSlot = (currentSlot + 1)%TOTAL_SLOT;
		      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
		    }

		    if(keyHand.qPressed){
		      keyHand.qPressed = false;
		      currentSlot = (currentSlot - 1 + TOTAL_SLOT) % TOTAL_SLOT;
		      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
		    }

		    if(keyHand.spacePressed){
		      keyHand.spacePressed = false;
		      currentSlot = 0; // reset slot
		      if(currentType == 0)
		        currentType = 1;
		      else
		        currentType = 0;
		      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
		    }

		    if(keyHand.numPressed){
		      keyHand.numPressed = false;
		      switch (currentPlayer) {
		        case 0:
		          if(TOTAL_SLOT == TOTAL_BARREL){
		            bulletType = keyHand.numPressedNUM; // ammo check
		            checkPlayerAmmo(bulletType, gp.player1);
		          }
		          else
		            gp.player1.action[currentSlot] = keyHand.numPressedNUM;
		          break;

		        case 1:
		          if(TOTAL_SLOT == TOTAL_BARREL){
		            bulletType = keyHand.numPressedNUM;
		            checkPlayerAmmo(bulletType, gp.player2);
		          }
		          else
		            gp.player2.action[currentSlot] = keyHand.numPressedNUM;
		          break;
		      } 
		      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
		      keyHand.numPressedNUM = 0; // reset num
		    }

		    if(keyHand.enterPressed){

		      if(!gp.player1.ready){
		        gp.player1.ready = true;
		        currentType = 0;
		        currentSlot = 0;
		        currentPlayer = 1;
		        System.out.println("player1 ready");
		        keyHand.enterPressed = false;
		      }

		      if(!gp.player2.ready && keyHand.enterPressed){
		        gp.player2.ready = true;
		        System.out.println("player2 ready");
		      }

		      if(gp.player1.ready && gp.player2.ready){
		        System.out.println("change stage");
		        currentState = PREVIEW_STATE;
		        currentPlayer = 0;
		        gp.player1.ready = false;
		        gp.player2.ready = false;
		        keyHand.enterPressed = false;
		      }
		    }
		  }

		  private int calculateDamage(int bulletType, Player p1, Player p2){ // Gunner Victim
		    int damage = 0;
		    switch (bulletType) {
		      case NORMAL_BULLET:
		        break;
		      case MAGIC_BULLET:
		        break;
		      case SILVER_BULLET:
		        break;
		      case DEATH_BULLET:
		        break;
		    }
		    return damage;

			

		}	
	
}