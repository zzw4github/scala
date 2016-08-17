package org.scalalang.tutorial.tour;

/**
  * Created by infosea on 2016-08-17.
  */
@interface Source {
    public String URL();
    public String mail();
}

@Source(URL = "http://coders.com",
        mail = "support@coders.com")
class Myclass {

}



public class Annotation {
  public static void main(String[] args) {
    try {
      Reader in = new Reader("README.md1");
      int c;
      while ((c = in.read()) != -1) {
        System.out.print((char) c);
      }
    } catch (java.io.IOException e) {
      System.out.println(e.getMessage());
    }
  }
}