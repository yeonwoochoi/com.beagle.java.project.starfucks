package utils;


/**
 * Class with a method that converts variables of type String and int
 */

public class TypeChangeUtils {


    /**
     * Convert a String type variable to an int type
     * @param input
     * @return Value converted to String type
     */
    public String intToString(int input)  {
        String output = Integer.toString(input);
        return output;
    }



    /**
     * Convert variable of int type to String type
     * @param input
     * @return Value converted to int type
     */
    public int StringToInt(String input) {
        int output = Integer.parseInt(input);
        return output;
    }

}
