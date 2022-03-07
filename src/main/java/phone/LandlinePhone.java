package phone;

public class LandlinePhone extends Phone {
  private boolean off_hook;

  public LandlinePhone(String n) {
    super(n);
    this.off_hook = false;
  }

  public void pickUp() {
    off_hook = true;
  }

  public void hangUp() {
    off_hook = false;
  }

  public void callSomeone(String numberToCall) {
    // Small modification from the video, we must pick up / hang up the phone inside the method.
    pickUp();
    if(off_hook) {
      super.callSomeone(numberToCall);
      hangUp();
    }
  }

  public void voiceMail() {
    if(off_hook) {
      System.out.println("Listening to the voicemail of a landline phone...");
    }
  }
}
