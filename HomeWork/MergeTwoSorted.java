package HomeWork;

public class MergeTwoSorted
{
    public static void mergeArrays(int[] arrayA, int[] arrayB)
    {
        int i = 0, j = 0, k = 0;
        int arrayC[]=new  int[arrayA.length+arrayB.length];
        while (i<arrayA.length && j <arrayB.length)
        {
            if (arrayA[i] < arrayB[j])
                arrayC[k++] = arrayA[i++];
            else
                arrayC[k++] = arrayB[j++];
        }
        while (i < arrayA.length)
            arrayC[k++] = arrayA[i++];
        while (j < arrayB.length)
            arrayC[k++] = arrayB[j++];

        System.out.println("Array after merging");
        for (int item:arrayC) {
            System.out.print(" "+item);
        }
    }

    public static void main (String[] args)
    {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        mergeArrays(arr1, arr2);
    }
}


