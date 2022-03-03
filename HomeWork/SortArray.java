package HomeWork;

public class SortArray
{
    public static void sortArray(int[] arrayA)
    {
        for (int i = 1; i < arrayA.length; i++)
        {
            int j = i;
            int a = arrayA[i];
            while ((j > 0) && (arrayA[j-1] > a))
            {
                arrayA[j] = arrayA[j-1];
                j--;
            }
            arrayA[j] = a;
        }
        for (int item:arrayA) {
            System.out.print(" "+item);
        }
    }
    public static void main (String[] args)
    {
        int[] arr1 = {9,1, 3,10, 5, 7};
        sortArray(arr1);
    }
}


