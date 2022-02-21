package day6;

import java.util.HashMap;
import java.util.Scanner;

public class CheckAnagrUsingamHashmap {
    static boolean areAnagram(String str1, String str2)
    {
        HashMap<Character, Integer> hmap1 = new HashMap<Character, Integer>();
        HashMap<Character, Integer> hmap2 = new HashMap<Character, Integer>();

        char arr1[] = str1.toCharArray();
        char arr2[] = str2.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
            if (hmap1.get(arr1[i]) == null) {
                hmap1.put(arr1[i], 1);
            }
            else {
                Integer c = (int)hmap1.get(arr1[i]);
                hmap1.put(arr1[i], ++c);
            }
        }
        for (int j = 0; j < arr2.length; j++) {

            if (hmap2.get(arr2[j]) == null)
                hmap2.put(arr2[j], 1);
            else {

                Integer d = (int)hmap2.get(arr2[j]);
                hmap2.put(arr2[j], ++d);
            }
        }

        if (hmap1.equals(hmap2))
            return true;
        else
            return false;
    }

    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter to Strings:");
        String str1=scanner.nextLine();
        String str2=scanner.nextLine();
        if (areAnagram(str1, str2))
            System.out.println("The two string Are anagram");
        else
            System.out.println("The two strings are not angram");
    }
}
