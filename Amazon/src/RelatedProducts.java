import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.*;

/**
 * Created by Niranjana on 06-04-2016.
 */
public class RelatedProducts {

    //Every customer is mapped to all the products he has purchased
    HashMap<Long, HashSet<Long>> custToProducts = new HashMap<Long, HashSet<Long>>();
    //Every product is mapped to all the products he has purchased
    HashMap<Long, HashSet<Long>> prodToCustomers = new HashMap<Long, HashSet<Long>>();
    /*Notes:
     * HashMap/HashSet is used to avoid duplicate customerId and productId
     */



        //When a product is purchased by a customer
        public void RegisterPurchase(long customerId, long productId){
            //To map customers to product HashSet
            //Use the existing product hashset if the cusromerId is already in the Map else create a new hashset
            HashSet<Long> productsSet;
            if (custToProducts.containsKey(customerId)){
                productsSet = custToProducts.get(customerId);
            }else{
                productsSet = new HashSet<Long>();
            }
            productsSet.add(productId);
            custToProducts.put(customerId,productsSet);

            //To map products to customer HashSet
            //Use the existing customer hashset if the cusromerId is already in the Map else create a new hashset
            HashSet<Long> customersSet;
            if (prodToCustomers.containsKey(productId)){
                customersSet = prodToCustomers.get(productId);
            }else{
                customersSet = new HashSet<Long>();
            }
            customersSet.add(customerId);
            prodToCustomers.put(productId,customersSet);
        }

        //Get Related Products
        public HashSet<Long> getRelatedProducts(long customerId, long productId, int numProducts){
            HashSet<Long> relatedProducts = new HashSet<>();
            HashSet<Long> customersSet;
            if (prodToCustomers.containsKey(productId)){
                //Get all customers who have purchased this product
                customersSet = prodToCustomers.get(productId);
                Iterator<Long> itr_cust = customersSet.iterator();
                int num = 0;

                while (num < numProducts && itr_cust.hasNext()){
                    Long customer = itr_cust.next();
                    //Check if the customer is not the requested customer
                    /*
                     *Assumption - To display products of related customer other than the *requested customer*
                     */
                    if (customer != customerId){
                        if (custToProducts.containsKey(customer)) {
                            //Get all products of this particular related customer
                            HashSet<Long> productsSet = custToProducts.get(customer);

                            Iterator<Long> itr_prod = productsSet.iterator();
                            while (num < numProducts && itr_prod.hasNext()){
                                //Check if the product is not the requested product
                                /*
                                 *Assumption - To display products other than the *requested product*
                                 */
                                Long product = itr_prod.next();
                                if (product != productId){
                                    if (relatedProducts.add(product))
                                    num++;
                                }
                            }
                        }
                    }
                }
            }else{
                System.out.println("This product is not purchased by any customer");
                return relatedProducts;
            }
            if (relatedProducts.size() == 0 && numProducts > 0){
                System.out.println("There are no related products");
            }
            return relatedProducts;
        }

        //Get related customer
        /*
         * Assumption - to retrieve any random related customer with no particular order
         */
        public long getRelatedCustomer(long customerId, long productId){
            long relatedCustomer = 0l;

            HashSet<Long> customersSet;
            if (prodToCustomers.containsKey(productId)){
                customersSet = prodToCustomers.get(productId);
                Iterator<Long> itr = customersSet.iterator();
                while (itr.hasNext()){
                    Long customer = itr.next();
                    //If customer is not same as the requested customer
                    if (customer != customerId){
                        relatedCustomer = customer;
                        return relatedCustomer;
                    }
                }
            }else{
                System.out.println("Product is not yet purchased by anyone");
                return relatedCustomer;
            }
            if(relatedCustomer == 0l){
                System.out.println("No one else has got this product");
            }
            return relatedCustomer;
        }
}
