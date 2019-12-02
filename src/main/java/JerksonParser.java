
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerksonParser {
    private String [] objectSplitter;


    public JerksonParser(String file){
        Pattern pattern = Pattern.compile("##");
        this.objectSplitter = pattern.split(file);

    }



    public Product productCount(String product){
        Integer count = 0;
        Product item = new Product();

        for(String each: objectSplitter) {
            Pattern p = Pattern.compile("((?<=name:)" + product + ")", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(each);

            if(m.find()){
                Pattern pricePattern = Pattern.compile("((?<=price:)\\d.\\d{0,2})", Pattern.CASE_INSENSITIVE);
                Matcher priceMatcher = pricePattern.matcher(each);
                if(priceMatcher.find()){
                    item.addPriceCount(priceMatcher.group(), 1);
                    count += m.groupCount();
                }
            }
        }

        item.setName(product);
        item.setSeen(count);
        return item;
    }

    public Integer getErrors(){
        Integer Error = 0;

        for(String each: objectSplitter){
            Pattern p = Pattern.compile("((?<=name:)\\w+)", Pattern.CASE_INSENSITIVE);
            Pattern p2 = Pattern.compile("((?<=price:)\\d.\\d{0,2})", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(each);
            Matcher m2 = p2.matcher(each);
            if(m.find() && m2.find()){
                Error++;
            }
        }
        return objectSplitter.length - Error;
    }


    //String corrector before instantiating class
    public static String correctingString(String input){
        Pattern p = Pattern.compile("(?<=name:)co.....", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);

        input =  m.replaceAll("cookies");


        return input;
    }

}
