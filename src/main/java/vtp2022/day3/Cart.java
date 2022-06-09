package vtp2022.day3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<String> contents = new LinkedList<>();
    private String username;

    

    public Cart(String name){
        this.username = name;
    }

    //check if item is already in the cart, if not add the item
    public void add(String item){
        for (String inCart:contents)
            if(inCart.equals(item))
                return;
            contents.add(item);
    }

    //if index is within the list, able to delete, if not do nothing
    public String delete (int index){
        if (index < contents.size())
            return contents.remove(index);
        return "nothing";
    }

    //Getters
    public List<String> getContents(){
        return contents;
    }

    public String getUsername() {
        return username;
    }

    public void load(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String item;
        while ((item =br.readLine()) !=null)
            contents.add(item);
        //order for input is important
        br.close();
        isr.close();        
    }

    public void save(OutputStream os) throws IOException{
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        for(String item: contents)
            bw.write(item +"\n");
            //order for output does not matter
            bw.flush();
            osw.flush();
            bw.close();
            osw.close();
    }

}