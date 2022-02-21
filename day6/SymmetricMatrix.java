package day6;
import java.util.Scanner;
public class SymmetricMatrix
{
 public static boolean checkSymmetric(int array[][],int n){
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(array[i][j] != array[j][i])
                {
                   return false;
                }
            }
        }
       return true;
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of the Matrix");
        int n = in.nextInt();
        int array[][] = new int[n][n];
        System.out.println("Enter all the elements of matrix:");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                array[i][j] = in.nextInt();
            }
        }
        System.out.println(checkSymmetric(array,n));
    }
}