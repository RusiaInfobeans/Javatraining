package HomeWork;

import java.util.Scanner;

public class AddMatrix
{
    public  static  int[][] addMatrix(int mat1[][],int mat2[][],int row,int col){
        int res[][] = new int[row][col];
        for ( int i= 0 ; i < row ; i++ )
            for ( int j= 0 ; j < col ;j++ )
                res[i][j] = mat1[i][j] + mat2[i][j] ;

        return res;
    }

    public static void main(String args[])
    {
        int row, col,i,j;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows");
        row = in.nextInt();
        System.out.println("Enter the number columns");
        col = in.nextInt();
        int mat1[][] = new int[row][col];
        int mat2[][] = new int[row][col];
        System.out.println("Enter the elements of matrix1");
        for ( i= 0 ; i < row ; i++ )
        {
            for ( j= 0 ; j < col ;j++ )
                mat1[i][j] = in.nextInt();

        }
        System.out.println("Enter the elements of matrix2");
        for ( i= 0 ; i < row ; i++ )
        {
            for ( j= 0 ; j < col ;j++ )
                mat2[i][j] = in.nextInt();

        }
        int result[][]=addMatrix(mat1,mat2,row,col);

        System.out.println("Sum of matrices:-");
        for ( i= 0 ; i < row ; i++ )
        {
            for ( j= 0 ; j < col ;j++ )
                System.out.print(result[i][j]+"\t");

            System.out.println();
        }

    }
}
