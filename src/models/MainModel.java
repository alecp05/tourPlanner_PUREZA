package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainModel {
    // http://openbook.rheinwerk-verlag.de/javainsel/12_004.html
    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("Hello VM!");
    private final StringProperty outputSmiley = new SimpleStringProperty("");

    public StringProperty inputProperty() {
        System.out.println("VM: get input prop");
        return input;
    }

    public StringProperty outputProperty() {
        System.out.println("VM: get output prop");
        return output;
    }
    public StringProperty outputSmiley() {
        System.out.println("VM: get output prop 2");
        return outputSmiley;
    }

    public void setOutputSmiley(){
        this.outputSmiley.set("You clicked :)!");
    }

    public void calculateOutputString() {
        System.out.println("VM: calculate Output");
        this.output.set("Hello ".concat(this.input.get()).concat("!"));
        this.input.set("");
    }
}
