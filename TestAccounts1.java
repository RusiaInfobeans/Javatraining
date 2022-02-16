package Task;

// TestAccounts1
// A simple program to test the numAccts method of the
// Account class.
//************************************************************
import java.util.ArrayList;
import java.util.Scanner;
public class TestAccounts1
{
    public static void main(String[] args)
    {
        ArrayList<Account> accountHolder = new ArrayList<Account>();
        Account testAcct;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many accounts would you like to create?"); int num =
            scan.nextInt();
        for (int i=1; i<=num; i++)
        {
            accountHolder.add(new Account(100, "Name" + i));
            System.out.println("\nCreated account " + accountHolder.get(i-1));
        }
        System.out.println("Total  "+Account.numAccount()+" account");
        accountHolder.get(1).close();

        System.out.println("Now there are " + Account.numAccount()
                + " accounts");
    }
}
