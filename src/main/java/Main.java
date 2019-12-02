import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        //reading resources file to string
        String output = (new Main()).readRawDataToString();
        //correcting string a little
        String file = JerksonParser.correctingString(output);

        //parsing the jerkson file
        String[] products = {"Milk", "Bread", "Cookies", "Apples"};
        JerksonParser jerk = new JerksonParser(file);

        ArrayList<Product> list = new ArrayList<Product>();

        for(String each : products){
            list.add(jerk.productCount(each));
        }

        for(Product each: list){
            String k;
            Integer v;
            System.out.printf("name: \t%s\t\tseen: %d times%n", each.getName(), each.getSeen());
            System.out.println("============\t\t=============");
            for(Map.Entry<String, Integer> sets : each.getPriceCount().entrySet()){
                k = sets.getKey();
                v = sets.getValue();
                System.out.printf("Price: \t%s\t\tseen: %d times%n",k, v);
                System.out.println("---------------------------------");
            }
        }
        System.out.printf("ERRORS \t\t\t\tseen: %d times%n", jerk.getErrors());




    }
}
