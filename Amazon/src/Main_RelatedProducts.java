import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Niranjana on 07-04-2016.
 */
public class Main_RelatedProducts {

    public static void main(String args[]){
        HashSet<Long> products;
        RelatedProducts r = new RelatedProducts();
        r.RegisterPurchase(10, 101);
        r.RegisterPurchase(10, 102);
        r.RegisterPurchase(10, 103);
        r.RegisterPurchase(11, 101);
        r.RegisterPurchase(11, 105);
        r.RegisterPurchase(11, 106);
        r.RegisterPurchase(12, 101);
        r.RegisterPurchase(12, 108);
        r.RegisterPurchase(12, 106);
        r.RegisterPurchase(12, 102);

        System.out.println("\n\nTest of getRelatedProducts");
        /*** Case 1 - requested products are lesser than total size ***************/
        System.out.println("\nCase 1 - requested products are lesser than total size");
        products = r.getRelatedProducts(10, 101, 2);
        for(Long p:products){
            System.out.println(p);
        }
        /*** Case 2 - requested products are greater than total size ***************/
        /*** Case 2.a - when there are repeated products ( 11 and 12 has 106) ***************/

        System.out.println("\nCase 2 - requested products are greater than total size");
        System.out.println("Case 2.a - when there are repeated products (11 and 12 has 106)");
        products = r.getRelatedProducts(10, 101, 6);
        for(Long p:products){
            System.out.println(p);
        }

        /*** Case 3 - requested products are 0 ***************/
        System.out.println("\nCase 3 - requested products are 0 ");
        products = r.getRelatedProducts(10, 101, 0);
        for(Long p:products){
            System.out.println(p);
        }

        /*** Case 4 - for different product ***************/
        System.out.println("\nCase 4 - for different product");
        products = r.getRelatedProducts(12, 102, 3);
        for(Long p:products){
            System.out.println(p);
        }

        /*** Case 5 - for product not in the list ***************/
        System.out.println("\nCase 5 - for invalid product");
        products = r.getRelatedProducts(10, 115, 3);
        for(Long p:products){
            System.out.println(p);
        }

        /*** Case 6 - for no related products ***************/
        System.out.println("\nCase 6 - for no related products");
        products = r.getRelatedProducts(10, 103, 3);
        for(Long p:products){
            System.out.println(p);
        }


        System.out.println("\n\nTest of getRelatedCustomer");
        Long customer;
        /*** Case 1 - for product not in the list ***************/
        System.out.println("\nCase 1 - Related customer - positive");
        customer = r.getRelatedCustomer(10, 101);
        System.out.println(customer);
        /*** Case 2 - for product not in the list ***************/
        System.out.println("\nCase 2 - Related customer - negative");
        customer = r.getRelatedCustomer(10, 103);
        System.out.println(customer);
        /*** Case 3 - for product not purchased ***************/
        System.out.println("\nCase 3 - for product not purchased");
        customer = r.getRelatedCustomer(15, 126);
        System.out.println(customer);

    }
}
