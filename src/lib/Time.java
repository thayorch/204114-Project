package lib;

public class Time {

  public static void sleep(int sec) {
    long startsec = System.currentTimeMillis() % 1000;
    while (startsec - System.currentTimeMillis() % 1000 <= sec) {
      
    }
  }

  public static void sleep(float sec) {
    long startsec = System.currentTimeMillis() % 1000;
    while (startsec - System.currentTimeMillis() <= (sec * 1000)) {
      
    }
  }
}
