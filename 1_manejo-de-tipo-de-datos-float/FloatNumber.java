
public class FloatNumber {
  public static void main(String[] args) {
    float number = 3.14159265359f;
    
    System.out.printf("\no Mostrar parte entera y separado la parte decimal de la siguiente variable de tipo float\n");
    split(number);
  }

  static void split(float numberToSplit) {
    int leftSide = (int) numberToSplit;
    float rightSide = numberToSplit - leftSide;

    System.out.printf("  Input: %f\n", numberToSplit);
    System.out.printf("  Output: %s | %f\n", leftSide, rightSide);
  }
}
