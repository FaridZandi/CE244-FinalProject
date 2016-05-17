package ControlPackage;
import ModelPackage.Model;
import ViewPackage.View;

import java.util.Scanner;

/**
 * Created by Y50 on 5/11/2016.
 */
public class Control
{
    private Model model;
    private InputHandler inputHandler;
    private View view;
    private CompoundMethod compoundMethod;

    public static void main(String[] args) {
        Control control = new Control();
        System.out.println("shit");
        control.something();
    }

    public Control()
    {
        view = new View(this);
        model= new Model();
        inputHandler = new InputHandler(this);
    }

    public void something()
    {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            view.getInput(scanner);
        }
    }


    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

}
