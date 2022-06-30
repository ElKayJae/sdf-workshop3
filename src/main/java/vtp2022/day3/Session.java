package vtp2022.day3;

import java.io.Console;
import java.util.List;

public class Session {
    public static final String LIST ="list";
    public static final String CARTS ="carts";
    public static final String ADD ="add";
    public static final String DELETE ="del";
    public static final String LOAD ="load";
    public static final String USERS ="users";
    public static final String SAVE ="save";
    public static final String END ="end";
    public static final String LOGIN ="login";

    private Repository repository;
    private Cart currentCart;
    
    public Session(Repository repo){
        this.repository =repo;
    }
    public void start(){
        Console cons = System.console();
        Boolean stop = false;
        currentCart = new Cart("anon");
        System.out.println("Welcome to the shopping cart!");

        while (!stop) {
            String input = cons.readLine("> ");
            String[] terms = input.split(" ");
            switch (terms[0]) {
                case CARTS:
                    System.out.println("List of shopping carts");
                    printList(repository.getShoppingCarts());
                    break;
                
                case LIST:
                    System.out.println("Items in " + currentCart.getUsername() + "'s shopping cart");
                    printList(currentCart.getContents());
                    break;
                
                case ADD:
                    int before = currentCart.getContents().size();
                    for (int i = 1; i < terms.length; i++) 
                        currentCart.add(terms[i]);
                    int addedCount = currentCart.getContents().size() -before;
                    System.out.println("Added " + addedCount + " to " + currentCart.getUsername()+"'s cart");
                    break;
                
                case DELETE:
                    int idx = Integer.parseInt(terms[1]);
                    String item = currentCart.delete(idx);
                    System.out.printf("Removed %s from %s's cart", item, currentCart.getUsername());
                    break;

                
                case SAVE:
                    repository.save(currentCart);
                    System.out.println("Done!");
                    break;
                
                case LOGIN:
                    currentCart = new Cart(terms[1]);
                    System.out.printf("%s login OK\n", terms[1]);
                    
                case LOAD:
                currentCart = repository.load(currentCart.getUsername());
                System.out.printf("Loaded %s shopping cart. There are %s item(s)\n", 
                                    currentCart.getUsername(), currentCart.getContents().size());
                break;

                case USERS:
                    List<String> allCarts =repository.getShoppingCarts();
                    this.printList(allCarts);
                    break;

                case END:
                    stop = true;
                    break;                    
            
                default:
                System.err.printf("unknown input : %s\n", terms[0]);
                    break;
            }

            System.out.println("");
        
        }
        System.out.println("Thank you for shopping with us!");
    }

    public void printList(List<String> list){
        if (list.size()<=0) {
            System.out.println("No record found!");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), list.get(i));
        }
    }
}
