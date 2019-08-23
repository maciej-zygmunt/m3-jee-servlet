package pl.coderslab.model;

public class Calculator {
    private char operation;

    public Calculator(char operation) {
        this.operation = operation;
    }
    public int calculate(int a, int b) {
        switch (operation) {
            case '+': return a+b;
            case '-': return a-b;
            case '*': return a*b;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " " + operation +" ";
    }
}
