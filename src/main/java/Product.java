import java.util.HashMap;
import java.util.Map;

public class Product {
    private String name;
    private Integer seen;

    private Map<String, Integer> priceCount = new HashMap<String, Integer>();


    public Product(){}


    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPriceCount(String key, Integer value) {
        if(priceCount.containsKey(key)){
            this.priceCount.put(key, priceCount.get(key)+value);
        }else{
            this.priceCount.put(key, value);
        }

    }


    public Map<String, Integer> getPriceCount(){
        return priceCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", seen=" + seen +
                ", priceCount=" + priceCount +
                '}';
    }
}
