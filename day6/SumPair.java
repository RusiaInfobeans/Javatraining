package day6;

import java.util.Scanner;

public class SumPair {
    public static boolean getPairs(int[] arr, int sum)
    {
        int count = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if ((arr[i] + arr[j]) == sum){
                    return true;
                }
        return false;
    }
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Sum to be find");
        int[] arr = { 1, 5, 7, -1, 5 };
        int sum = scanner.nextInt();
        System.out.println(getPairs(arr, sum));
    }


}

