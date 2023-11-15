import java.lang.reflect.Method;
import java.util.*;

class Person {
    protected String firstName = "";
    protected String lastName = "";

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

public class PeopleList {
  public static void main(String[] args) {
    ArrayList<Person> people = howManyInstancePerson(5);
    sortAndShowPeople(people, "FirstName", "asc");
    sortAndShowPeople(people, "LastName", "asc");
    sortAndShowPeople(people, "LastName", "desc");
  }

  static private String getRandomFirstName() {
    String[] firstNames = {"Sergio", "Rocio", "Florencia", "Jose Luis", "Nicolas", "Sebastian", "Leonardo", "Javier", "Tadeo"};
    int randomIndex = (int)((Math.random() * (firstNames.length-1)));
    return firstNames[randomIndex];
  }

    static private String getRandomLastName() {
        String[] lastNames = {"Martinez", "Sanchez", "Nuñez", "Rosales", "Rojas", "Gonzales", "Molina", "Castro", "Diaz"};
        int randomIndex = (int)((Math.random() * (lastNames.length-1)));
        return lastNames[randomIndex];
    }

  static private ArrayList<Person> howManyInstancePerson(int numberOfInstances) {
      List<Person> people = new ArrayList<>();

      for (int i = 0; i < numberOfInstances; i++) {
          Person person = new Person(getRandomFirstName(), getRandomLastName());
          people.add(person);
      }

      return (ArrayList)people;
  }

  static private void sortAndShowPeople(ArrayList<Person> people, String attributeSort, String typeOfSort) {

      Comparator<Person> comparator = (a, b) -> {
          try {
              Method method = Person.class.getMethod("get" + attributeSort);
              Comparable<Object> valueA = (Comparable<Object>) method.invoke(a);
              Comparable<Object> valueB = (Comparable<Object>) method.invoke(b);

              if (typeOfSort.equals("asc")) return valueA.compareTo(valueB);
              else if (typeOfSort.equals("desc")) return valueB.compareTo(valueA);
              else throw new IllegalArgumentException("typeOfSort debe ser 'asc' o 'desc'");

          } catch (Exception e) {
              System.out.println(e);
              return 0;
          }

      };

      Collections.sort(people, comparator);
      System.out.printf("\nOrdenados port %s de orden %s :\n", attributeSort, typeOfSort);

      for (Person person: people) {
          if (attributeSort == "FirstName") System.out.printf(" - %s\n", person.getFirstName());
          else System.out.printf(" - %s\n", person.getLastName());
      }
  }

}
