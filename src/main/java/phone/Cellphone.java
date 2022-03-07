package phone;

public class Cellphone extends Phone {
  private double battery;

  public Cellphone(String n) {
    super(n);
    this.battery = 100;
  }

  public boolean isFullyCharged() {
    return battery == 100;
  }

  @Override
  public void callSomeone(String numberToCall) {
    if(battery >= 10) {
      super.callSomeone(numberToCall);
      battery -= 10;
    }
  }

  public void sendText(String numberToSendTo, String text) {
    if(battery >= 5) {
      System.out.println(number + " sending text `" + text + "` to " + numberToSendTo);
      battery -= 5;
    }
  }

  @Override
  public void voiceMail() {
    if(battery >= 3) {
      System.out.println("Accessing a cellphone voicemail through a dedicated app...");
    }
  }
}
