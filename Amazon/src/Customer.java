import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Niranjana on 06-04-2016.
 */
public class Customer {


    long customerID;
    boolean isPrime;
    HashSet<Long> productsToNotify = new HashSet<Long>();


    public Customer(long customerID, boolean isPrime) {
        this.customerID = customerID;
        this.isPrime = isPrime;
    }
}
