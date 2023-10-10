package jsonMethods;

import org.json.JSONObject;
import java.util.Arrays;

public class Comparator {
    public static boolean personalizedEquals(JSONObject expected, JSONObject actual){
        String [] keys = JSONObject.getNames(expected);
        boolean result = true;
        if(Arrays.equals(keys, JSONObject.getNames(actual)) && keys!=null){
            System.out.println("----------------------------------------------");
            for( String key : keys){
                boolean comparison = (expected.get(key).equals(actual.get(key)) | expected.get(key).equals("ignore"));
                result = result && comparison;
                if(!comparison){
                    System.out.println("Expected "+key+": "+expected.get(key)+", Actual "+key+": "+actual.get(key));
                };
            }
        }else if(keys==null && JSONObject.getNames(actual)==null){
            System.out.println("----------------------------------------------");
            System.out.println("Los objetos estan vacios");
        }else if(keys==null && JSONObject.getNames(actual)!=null){
            System.out.println("----------------------------------------------");
            System.out.println("El objeto json Expected esta vacio");
            result=false;
        }
        else{
            System.out.println("----------------------------------------------");
            System.out.println("Los objetos no tienen los mismos parametros");
            System.out.println("Expected: "+expected.toString());
            System.out.println("Actual: "+actual.toString());
            result = false;
        }
        return result;
    }
}
