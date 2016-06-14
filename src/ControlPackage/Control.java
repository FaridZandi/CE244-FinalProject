package ControlPackage;
import ModelPackage.*;
import ViewPackage.View;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * Created by Y50 on 5/11/2016.
 */
public class Control
{
    private Model model;
    private InputHandler inputHandler;
    private View view;

    public static void main(String[] args) {

        Control control = new Control();
        control.getContinuousInput();
    }

    public Control()
    {
        view = new View(this);
        model= new Model();
        inputHandler = new InputHandler(this);
    }

    public void getContinuousInput()
    {
        Scanner scanner = new Scanner(System.in);
        while(!model.getStory().isGameOver()) {
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
