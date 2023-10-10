package jsonMethods;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComparatorTest {
    @Test
    public void verifyJSONObjectEquals(){
        JSONObject jo = new JSONObject(
                "{\"city\":\"ignore\",\"name\":\"jon doe\",\"age\":\"ignore\"}"
        );
        JSONObject jo2 = new JSONObject(
                "{\"city\":\"chicago\",\"name\":\"jon poe\",\"age\":\"23\"}"
        );
        boolean actual = Comparator.personalizedEquals(jo,jo2);
        boolean expected = false;
        Assertions.assertEquals(expected, actual, "ERROR: el metodo no los compara correctamente");
    }
}
