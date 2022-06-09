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
    private Cart currenCart;
    
    public Session(Repository repo){
        this.repository =repo;
    }
    public void start(){
        Console cons = System.console();
        Boolean stop = false;
        currenCart = new Cart("anon");

        while (!stop) {
            String input = cons.readLine("> ");
            String[] terms = input.split(" ");
            switch (terms[0]) {
                case CARTS:
                    System.out.println("List of shopping carts");
                    printList(currenCart.getContents());
                    break;
                
                case LIST:
                    System.out.println("Items in " + currenCart.getUsername() + "'s shopping cart");
                    //TODO
                    break;
                
                case ADD:
                    int before = currenCart.getContents().size();
                    for (int i = 1; i < terms.length; i++) 
                        currenCart.add(terms[i]);
                    int addedCount = currenCart.getContents().size() -before;
                    System.out.println("Added " + addedCount + " to " + currenCart.getUsername()+"'s cart'");
                    break;
                
                case DELETE:
                    int idx = Integer.parseInt(terms[1]);
                    String item = currenCart.delete(idx);
                    System.out.printf("Removed %s from %s's cart", item, currenCart.getUsername());
                    break;
                
                case SAVE:
                    //TOOO
                    break;
                
                case LOGIN:
                    currenCart = new Cart(terms[1]);
                    break;

                case USERS:
                    //TOOO
                    break;

                case END:
                    stop = true;
                    break;                    
            
                default:
                System.err.printf("unknown inout : %s\n", terms[0]);
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
