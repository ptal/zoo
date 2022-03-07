package phone;

import java.util.*;

public class User {
  private Phone[] phones;
  public User(int n) {
    phones = new Phone[n];
    for(int i = 0; i < n; ++i) {
      if((int)(Math.random() * 2) == 0) {
        phones[i] = new Cellphone("04040404" + i);
      }
      else {
        phones[i] = new LandlinePhone("060606006" + i);
      }
    }
  }

  public void randomCall(String numberToCall) {
    int phoneIdx = (int)(Math.random() * phones.length);
    System.out.println("Try to call with phone n°" + phoneIdx);
    phones[phoneIdx].callSomeone(numberToCall);
  }
}
