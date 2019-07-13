package p2.util;

public class ValidationUtilities {
  public static boolean checkNullOrEmpty(String parameter) {
    return parameter != null && !parameter.equals("");
  }
}

