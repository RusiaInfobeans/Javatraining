package day6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AnagramCheck {
static boolean areAnagram(char[] str1, char[] str2)
    {
        int n1 = str1.length;
        int n2 = str2.length;
        if (n1 != n2)
            return false;

        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i = 0; i < n1; i++)
            if (str1[i] != str2[i]) {
                return false;
            }
        return true;
    }

    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter to Strings:");
        String s1=scanner.nextLine();
        String s2=scanner.nextLine();
        char str1[] = s1.toCharArray();
        char str2[] = s2.toCharArray();
        if (areAnagram(str1, str2))
            System.out.println("The two string Are anagram");
        else
            System.out.println("The two strings are not angram");
    }
}
