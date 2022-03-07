package phone;

public abstract class Phone {
  protected String number;
  public Phone(String number) {
    this.number = number;
  }

  public void callSomeone(String numberToCall) {
    System.out.println(number + " calls " + numberToCall + "...");
  }

  public abstract void voiceMail();
}