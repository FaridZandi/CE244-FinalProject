package ControlPackage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Y50 on 5/11/2016.
 */
public class InputType {
    private String inputString;
    private ArrayList<String> validSituations;
    private String outputString;
    private CompoundMethod compoundMethod;

    public InputType(String inputString, String validSituations, String outputString , CompoundMethod compoundMethod) {
        this.inputString = inputString;
        this.validSituations = new ArrayList<String>(Arrays.asList(validSituations.split(" ")));
        this.outputString = outputString;
        this.compoundMethod = compoundMethod;
    }

    public String getInputString() {
        return inputString;
    }

    public ArrayList<String> getValidSituations() {
        return validSituations;
    }

    public String getOutputString() {
        return outputString;
    }

    public String getGeneralInput()
    {
        String generalInput = inputString;
        String replaced = generalInput.replaceAll("(?=\\()(.*?)(?=\\)).", "input");
        replaced = replaced.replaceAll("[+\"“”]" , "");
        replaced = replaced.replaceAll("[ ]{2,}" , " ");
        //System.out.println(generalInput);
        replaced = replaced.toLowerCase();
        return replaced;
    }

    public CompoundMethod getCompoundMethod() {
        return compoundMethod;
    }
}
