package ViewPackage;

import ControlPackage.Control;

import java.util.Scanner;

/**
 * Created by Y50 on 5/1/2016.
 */
public class View {
    Control control;

    public View(Control control)
    {
        this.control = control;
    }

    public static void show(String data)
    {
        System.out.println(data);
    }
    public static void show(String data,String format)
    {
        System.out.printf(data,format);
    }
    public static void show(String data,int format)
    {
        System.out.printf(data,format);
    }


    public void getInput (Scanner scanner)
    {
        String input = scanner.nextLine();
        control.getInputHandler().handleInput(input);
    }

    public void invalidInput()
    {
        System.out.println("invalid input!");
    }
}
