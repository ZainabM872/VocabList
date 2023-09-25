import java.io.*;
import java.util.*;

class Main {

  // global variable scanner
  public static Scanner s = new Scanner(System.in);

  public static void main(String[] args) {
    // array list being used
    ArrayList<String> list = readStringAL("words.txt");

    // introduce prgram to user
    System.out.println("\nIn this program, you will be able to store a list of new words or phrases");

    int num = 0;
    do {
      //create seperate methods for each option
      System.out.println("\n Options: ");
      System.out.println("1. Print out the current list ");
      System.out.println("2. Enter a new word or phrase ");
      System.out.println("3. Enter a word to be searched for in the algorithim ");
      System.out.println("4. Remove a word from the list ");
      System.out.println("5. Save and exit the program ");
      System.out.println("\nEnter the number of your choice: ");
      num = s.nextInt();
  

      // if (num == 1) {call method 1}
      if (num == 1) {
        sort(list, "words.txt");
      }
      // if (num == 2) {call method 2}
      if (num == 2) {
        addList("words.txt", list);
      }
      // if (num == 3) {call method 3}
      if (num == 3) {
        searchForWord(list);
      }
      // if (num == 4) {call method 4}
      if (num == 4) {
        removeWord(list, "words.txt");
      }
      // if (num == 5) {call method 5}
      if (num == 5) {
        break;
      }
      System.out.println("\n ------------------------------------ ");
      
    } while (num != 5);

    // when the program breaks
    System.out.println("Thank you for using the program! This is the updated file: ");
    five("words.txt", list);
  }

  // method that reads arraylist and returns it to the main method
  public static ArrayList<String> readStringAL(String fileName) {
    File f = new File(fileName);
    ArrayList<String> list = new ArrayList<String>(); // initialize array list
    String line = "";

    // use a try-catch statement to catch errors
    try {
      BufferedReader br = new BufferedReader(new FileReader(f));
      // another string in the file
      while ((line = br.readLine()) != null) {
        list.add(line); // adds the lines to the array list
      }

      br.close(); // close the reader
    } catch (IOException e) // if an input-output error occurs
    {
      System.out.println("an error occured");
      e.printStackTrace();
    }

    return list; // return array list to be printed in main method
  }

  // sorting method
  public static void sort(ArrayList<String> list, String fileName) {
    File f = new File(fileName);
    String line = "";

    // sets i to the smallest index and increases until it reaches the largest index
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < list.size(); j++) {
        if (list.get(j).compareToIgnoreCase(list.get(i)) > 0) // if j compared to i is not the same (> 0: aka not equal
                                                              // to 0), then do what is in the if statement
        {

          String tmp = list.get(i);// creates temporary string varible that is equal to the index in the array list
          list.set(i, list.get(j));// updating index i to element j

          list.set(j, tmp);// updating index j to the temp variable
        }
      }
    }
    // prints out list
    System.out.println(list.toString());

    try {
      BufferedWriter wr = new BufferedWriter(new FileWriter(f)); // not true when overwriting it
      for (int i = 0; i < list.size(); i++) {
        wr.write(list.get(i) + "\n"); // +word
      }
      
      wr.close(); // close the reader
    } catch (IOException e) // if an input-output error occurs
    {
      System.out.println("an error occured");
      e.printStackTrace();
    }
  }

  // option 2
  public static void addList(String fileName, ArrayList<String> list) {
    File f = new File(fileName);
    String line = "";

    System.out.println("\nWhat word would you like to add to the list? ");
    String word = s.next();// user inputs word here

    System.out.println("\nUpdated list: ");
    list.add(word); // add the word to the list

    // sorting method
    sort(list, "words.txt");
    // prints out new list
  }

  // option 3
  public static void searchForWord(ArrayList<String> list) {
    System.out.println("\nEnter a word you want to search for: ");
    String search = s.next();

    int i = 0;

    // searches through each index for word
    for (i = 0; i < list.size(); i++) {
      // if the index is the same as the word being searched for (== 0), the print out
      // the word is in the list
      if (search.compareToIgnoreCase(list.get(i)) == 0) {
        System.out.println("\nthe word is in the list at index " + i);
        break;
      }
    }

    // if i becomes greater than the highest index then the word is not in the list
    if (i > list.size() - 1) {
      System.out.println("\nThe word is not in the list.");
      System.out.println("Choose option 2 if you would like to add it to the list");
    }

  }

  public static void removeWord(ArrayList<String> list, String fileName) {

    File f = new File(fileName);
    String line = "";

    // prints out arraylist so user can see words that can be removed
    System.out.println("\nPlease enter a word you would like to remove from the array list. " + list);
    String r = s.next();

    int i = 0;
    // searches through each index for word

    // initializes boolean
    boolean removed = true;
    for (i = 0; i < list.size(); i++) {

      // if word is found
      if (r.compareToIgnoreCase(list.get(i)) == 0) {
        list.remove(r);// remove word from the list
        System.out.println("\nThe word " + r + " has been removed");// prints out that the word has been removed
        System.out.print("new list: " + list);
        // prints out new list and breaks from the for loop

        try {
          BufferedWriter wr = new BufferedWriter(new FileWriter(f)); // not true when overwriting it


          for (i = 0; i < list.size(); i++) 
          {
            wr.write(list.get(i) + "\n"); // +\n
          }

          wr.close(); // close the reader
        } catch (IOException e) // if an input-output error occurs
        {
          System.out.println("an error occured");
          e.printStackTrace();
        }

        // if something has been removed == true then break from the for loop
        removed = true;

        break;
      }

      // if there has been nothing removed then set the boolean false
      removed = false;
    }

    // if removed == false, print out message below to user
    if (removed == false) {
      System.out.println("\nThe word you want to remove is not in the list. Please select a word in the list or select a different option.");
      // if word is not in the list, tell user to select one in the list or to select
      // another option and breaks from the for loop
    }

  }

  public static void five(String fileName, ArrayList<String> list) {
    File f = new File(fileName);

    try {
      BufferedWriter wr = new BufferedWriter(new FileWriter(f, true)); // not true when overwriting it

      wr.write("\n");

      System.out.println(list);
      wr.close(); // close the reader
    } catch (IOException e) // if an input-output error occurs
    {
      System.out.println("an error occured");
      e.printStackTrace();
    }

  }
}
