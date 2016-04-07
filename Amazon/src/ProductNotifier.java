import java.util.*;

/**
 * Created by Niranjana on 06-04-2016.
 */

public class ProductNotifier {

    //Map to store a list of customers to be notified for every product
    /*
     *Notes:-
     * Approach 1:
     * Store all the customers to beME notified in a list A and prime members alone in a seperate list B.
     * When request for SCHEME_FIFO is called - output data from A.
     * When request for SCHE_PRIME_FIRST is called - output data from B and if there are more space, output only the non prime from list A.
     * Used when SCHEME_FIFO is called more frequently
     *
     * Approach 2:
     * in notifyMe - Store all the prime members to list A and non prime members alone in list B along with a generated seriel number to save the order.
     * When request for SCHEME_PRIME_FIRST is called - output data from A.
     * When request for SCHEME_FIFO is called - merge both list A and B based on seriel number and output
     * Used when SCHEME_PRIME_FIRST is called more frequently
     *
     * I have used Approach 1.
     *
     */
    HashMap<Long, ArrayList<Customer>> bothQueue = new HashMap<Long, ArrayList<Customer>>();
    HashMap<Long, ArrayList<Customer>> primeQueue = new HashMap<Long, ArrayList<Customer>>();


    // Scheme for ordering the notification list of customers
    final int SCHEME_FIFO = 0;         // First in First out
    final int SCHEME_PRIME_FIRST = 1;  // Prime members in FIFO order followed by nonPrime in FIFO

    //Get list of customers to notify
    public ArrayList<Long> getCustomersToNotify(long productId, int scheme, int numCustomersToBeNotified){
        ArrayList<Long> customersToNotify = new ArrayList<Long>();
        if (bothQueue.containsKey(productId)) {

            if (scheme == SCHEME_FIFO) {
                //Get the list from bothQueue and output
                ArrayList<Customer> cust_bothq = bothQueue.get(productId);
                Iterator<Customer> itr = cust_bothq.iterator();
                int num = 0;
                while (num<numCustomersToBeNotified && itr.hasNext()){
                    customersToNotify.add(itr.next().customerID);
                    num++;
                }

            } else if (scheme == SCHEME_PRIME_FIRST) {
                int num = 0;

                //Get list of prime customers
                if (primeQueue.containsKey(productId)) {

                    ArrayList<Customer> cust_primeq = primeQueue.get(productId);
                    Iterator<Customer> itr = cust_primeq.iterator();
                    while (num < numCustomersToBeNotified && itr.hasNext()) {
                        Customer customer = itr.next();
                        customersToNotify.add(customer.customerID);
                        num++;
                    }
                }

                    //If more customers can be notifed, get all the non prime customers from bothQueue
                    ArrayList<Customer> cust_bothq = bothQueue.get(productId);
                    Iterator<Customer> itr = cust_bothq.iterator();
                    while (num<numCustomersToBeNotified && itr.hasNext()){
                        Customer customer = itr.next();
                        if (!customer.isPrime){
                            customersToNotify.add(customer.customerID);
                            num++;
                        }
                    }
            }
        }else{
            System.out.println("Product has no customers to be notified");
        }
        return customersToNotify;

    }

    //Notify Customers
    public void notifyMe(long productId, Customer customer){

        //If product is already in the customer's notification list - return
        if(customer.productsToNotify.contains(productId)){
            System.out.println("Product already in notification list");
            return;
        }
        //Add the product to the customer's notification list
        customer.productsToNotify.add(productId);

        //Get the customer list for the requested product. If it does not exist create new.
        ArrayList<Customer> custList_bothq;
        if(bothQueue.containsKey(productId)){
            custList_bothq = bothQueue.get(productId);
        }else{
            custList_bothq = new ArrayList<Customer>();
        }
        custList_bothq.add(customer);
        //bothQueue stores both prime & non prime customer list for every product
        bothQueue.put(productId, custList_bothq);


        //If prime customer
        if (customer.isPrime){
            //Get the customer list for the requested product. If it does not exist create new.
            ArrayList<Customer> custList_primeq;
            if(primeQueue.containsKey(productId)){
                custList_primeq = primeQueue.get(productId);
            }else{
                custList_primeq = new ArrayList<Customer>();
            }
            custList_primeq.add(customer);
            //primeQueue stores only prime customer list for every product
            primeQueue.put(productId, custList_primeq);
        }

    }
}
