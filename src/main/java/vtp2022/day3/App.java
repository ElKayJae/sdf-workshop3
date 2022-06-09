package vtp2022.day3;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String defaultDb = "db";

    public static void main( String[] args )
    {
        if (args.length>0)
            
            if(args[0]!=null){
            System.out.println(args[0]);
            App.defaultDb=args[0];
        
    }
         
        System.out.println(defaultDb);
        Repository repo = new Repository(defaultDb);
        Session session = new Session(repo);
        session.start();
    }
}
