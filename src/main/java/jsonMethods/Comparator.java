package jsonMethods;

import org.json.JSONObject;
import java.util.Arrays;

public class Comparator {
    public static boolean personalizedEquals(JSONObject expected, JSONObject actual){
        String [] keys = JSONObject.getNames(expected);
        if(Arrays.equals(keys, JSONObject.getNames(actual))){
            for( String key : keys){
                if(!(expected.get(key).equals(actual.get(key)) | expected.get(key).equals("ignore"))){
                    return false;
                };
            }
            return true;
        } else{
            return false;
        }
    }
}
