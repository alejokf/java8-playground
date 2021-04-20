package variance;

import java.util.ArrayList;
import java.util.List;

import lambda.Person;

public class VarianceMain {

    public static void main(String[] args) {
        // Covariance with Arrays
        NonEmpty[] nonEmptyArray = new NonEmpty[]{new NonEmpty(1, new Empty(), new Empty())};

        // Assigning nonEmptyArray (type NonEmpty[]) to a variable of type IntSet[]
        // This is allowed in compile time because NonEmpty is a subclass of IntSet
        IntSet[] intSetArray = nonEmptyArray;

        // Now we try to assign `new Empty()` to the first element of intSetArray
        // This is allowed in compile time because Empty is a subclass of IntSet

        // But this fails at runtime with and class ArrayStoreException extends RuntimeException because the initial
        // object that was created is of type NonEmpty[], and we are assigning an Empty set to a variable of type NonEmpty
        intSetArray[0] = new Empty();

        // Here it actually looks like we are assigning an Empty set to a variable of type NonEmpty
        NonEmpty nonEmpty = nonEmptyArray[0];

        // In Java Arrays are covariant at compile time, but this can be actually broken at run time


        // Covariance with List
        List<NonEmpty> nonEmptyList = new ArrayList<>();
        nonEmptyList.add(new NonEmpty(1, new Empty(), new Empty()));
        // This doesn't compile because Lists are not covariant in Java
        //List<IntSet> intSetList = nonEmptyList;

        //This also happens when having a null in a list
        List<Person> myList = new ArrayList<>();
        myList.add(new Person("Alejo", 35));
        myList.add(null);
        Integer[] array = myList.toArray(new Integer[myList.size()]);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
