package phone;

public class Phoning {
  public static void main(String[] args) {
    Cellphone gabriel = new Cellphone("0494494949");
    Cellphone matt = new Cellphone("0595959595");
    gabriel.callSomeone("0303230301");

    User user = new User(5);
    for(int i = 0; i < 10; ++i) {
      user.randomCall("112");
    }
  }
}