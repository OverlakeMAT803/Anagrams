import java.util.*;
import java.io.*;
import java.util.*;

public class TestAnagrams {

   private static final String DICTIONARY_FILE = "dict1.txt";
    
   public static void main(String[] args) throws FileNotFoundException {
       // read dictionary into a set
      Scanner input = new Scanner(new File(DICTIONARY_FILE));
      Set<String> dictionary = new TreeSet<String>();
      
      while (input.hasNextLine()) {
         dictionary.add(input.nextLine());
      }
      dictionary = Collections.unmodifiableSet(dictionary);
   
      Anagrams solver = new Anagrams(dictionary);
   
      // Test if constructor throws exceptions
      if (args.length == 0) {
         try { 
            Anagrams solver2 = new Anagrams(null);
         } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
         }
         return;
      }
      
      if (args[0].equals("printsAll")) { // Prints all
         solver.print("Barbara Bush");
      } else if (args[0].equals("printsMax")) { // Prints max words
         solver.print("George BUSH", 0);
      } else if (args[0].equals("getWords")) { // getWords matches
         System.out.println(solver.getWords("Barbara Bush").toString());
      } else if (args[0].equals("getWordsException")) { // getWords throws exception
         try { 
            solver.getWords(null);
         } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
         }
         return;
      } else if (args[0].equals("EmptyPrint")) { // Empty prints none
         solver.print("");
      } else if (args[0].equals("printException")) { // print throws exception
         try { 
            solver.print(null);
         } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
         }
         return;
      } else if (args[0].equals("hairbrush")) { // Max of 2
         solver.print(args[0], 2);
      } else if (args[0].equals("hairbrushNull")) { // printNum null check
         try { 
            solver.print(null, 0);
         } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
         }
         return;
      } else if (args[0].equals("negativeMax")) { // max < 0
         try { 
            solver.print("hairbrush", -1);
         } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
         }
         return;
      }
   
   }
}