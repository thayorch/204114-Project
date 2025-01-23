package lib;
public class Logic {

  public static void all(int[] array) {
    
  }

  public static boolean all(boolean[] array) {
    boolean state = false;
    int count = 0;
    int array_length = array.length;

    for (boolean tmp : array)
      if(tmp == true)
        count++;

    if(count == array_length)
      state = true;

    return state;
  }
}
