import java.util.ArrayList;
import java.util.HashSet;

public class Main_ProductNotifier {
    static Customer c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    public static void createCustomers(){
         c1 = new Customer(1, true);
         c2 = new Customer(2, true);
         c3 = new Customer(3, true);
         c4 = new Customer(4, true);
         c5 = new Customer(5, true);
         c6 = new Customer(6, false);
         c7 = new Customer(7, false);
         c8 = new Customer(8, false);
         c9 = new Customer(9, false);
         c10 = new Customer(10, false);
    }
    public static void main(String[] args) {
        ProductNotifier p = new ProductNotifier();
        ArrayList<Long> customers;
        createCustomers();
        //Case 1: When Only Prime Customers are in notify list
        p.notifyMe(101,c1);
        p.notifyMe(101,c2);
        p.notifyMe(101,c3);
        p.notifyMe(102,c3);
        p.notifyMe(101,c4);
        p.notifyMe(102,c4);

        System.out.println("\nCase 1: When Only Prime Customers are in notify list");
        //Case 1.a - Scheme 0
        //Case 1.a.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,0,2);
        System.out.println("Scheme:0 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.a.2 - When the list size is less than the requested size
        customers = p.getCustomersToNotify(101,0,6);
        System.out.println("Scheme:0 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b - Scheme 1
        //Case 1.b.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,1,2);
        System.out.println("Scheme:1 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b.2 - When the list size is lesser than the requested size
        customers = p.getCustomersToNotify(101,1,6);
        System.out.println("Scheme:1 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

         /*******************************************************************/
        //Case 2: When Only Non Prime Customers are in notify list
        p = new ProductNotifier();
        createCustomers();
        p.notifyMe(101,c6);
        p.notifyMe(101,c7);
        p.notifyMe(101,c8);
        p.notifyMe(101,c9);

        System.out.println("\nCase 2: When Only Non Prime Customers are in notify list");
        //Case 1.a - Scheme 0
        //Case 1.a.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,0,2);
        System.out.println("Scheme:0 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.a.2 - When the list size is less than the requested size
        customers = p.getCustomersToNotify(101,0,6);
        System.out.println("Scheme:0 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b - Scheme 1
        //Case 1.b.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,1,2);
        System.out.println("Scheme:1 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b.2 - When the list size is lesser than the requested size
        customers = p.getCustomersToNotify(101,1,6);
        System.out.println("Scheme:1 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

        /*******************************************************************/
        //Case 3: When both Prime and Non Prime Customers are in notify list
        p = new ProductNotifier();
        createCustomers();
        p.notifyMe(101,c1);
        p.notifyMe(101,c6);
        p.notifyMe(101,c2);
        p.notifyMe(101,c3);
        p.notifyMe(101,c7);
        p.notifyMe(101,c8);

        System.out.println("\nCase 3: When both Prime and Non Prime Customers are in notify list");
        //Case 1.a - Scheme 0
        //Case 1.a.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,0,2);
        System.out.println("Scheme:0 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.a.2 - When the list size is lesser than/equal to the requested size
        customers = p.getCustomersToNotify(101,0,6);
        System.out.println("Scheme:0 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b - Scheme 1
        //Case 1.b.1 - When the list size is greater than the requested size
        customers = p.getCustomersToNotify(101,1,2);
        System.out.println("Scheme:1 Product:101 Customers:2");
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 1.b.2 - When the list size is lesser than/equal to the requested size
        customers = p.getCustomersToNotify(101,1,6);
        System.out.println("Scheme:1 Product:101 Customers:6");
        for(Long c: customers){
            System.out.println(c);
        }

        /*******************************************************************/
        //Case 4: Multiple Products
        p = new ProductNotifier();
        createCustomers();
        p.notifyMe(101,c7);
        p.notifyMe(102,c6);
        p.notifyMe(101,c8);
        p.notifyMe(102,c3);
        p.notifyMe(101,c2);
        p.notifyMe(102,c8);
        p.notifyMe(101,c1);

        System.out.println("\nCase 4: Multiple Products");
        //Case 4.a - Scheme 0
        customers = p.getCustomersToNotify(102,0,4);
        System.out.println("Scheme:0 Product:102 Customers:4");
        for(Long c: customers){
            System.out.println(c);
        }


        //Case 4.b - Scheme 1
        customers = p.getCustomersToNotify(102,1,4);
        System.out.println("Scheme:1 Product:102 Customers:4");
        for(Long c: customers){
            System.out.println(c);
        }
        /*******************************************************************/
        //Case 5: Duplicate NotifyMe Click
        p = new ProductNotifier();
        createCustomers();
        System.out.println("\nCase 5: Duplicate NotifyMe Click");
        p.notifyMe(101,c1);
        p.notifyMe(101,c6);
        p.notifyMe(101,c4);
        p.notifyMe(101,c4);
        p.notifyMe(101,c1);


        //Case 5a - Scheme 0
        System.out.println("Scheme:0 Product:101 Customers:4");
        customers = p.getCustomersToNotify(101,0,4);
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 5b - Scheme 1
        System.out.println("Scheme:1 Product:101 Customers:4");
        customers = p.getCustomersToNotify(101,1,4);
        for(Long c: customers){
            System.out.println(c);
        }

        /*******************************************************************/
        //Case 6: Empty Data
        p = new ProductNotifier();
        createCustomers();
        p.notifyMe(101,c4);
        p.notifyMe(101,c1);

        System.out.println("\nCase 6: Empty Data");
        //Case 6a - Scheme 0
        System.out.println("Scheme:0 Product:102 Customers:4");
        customers = p.getCustomersToNotify(102,0,4);
        for(Long c: customers){
            System.out.println(c);
        }

        //Case 6a - Scheme 1
        System.out.println("Scheme:1 Product:102 Customers:4");
        customers = p.getCustomersToNotify(102,1,4);
        for(Long c: customers){
            System.out.println(c);
        }




    }


}
