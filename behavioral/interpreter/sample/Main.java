package behavioral.interpreter.sample;

import behavioral.interpreter.sample.expressions.AddExpression;
import behavioral.interpreter.sample.expressions.IExpression;
import behavioral.interpreter.sample.expressions.NumberExpression;
import behavioral.interpreter.sample.expressions.SubtractExpression;

public class Main {

    public static void main(String[] args) {
        IExpression ten = new NumberExpression(10);
        IExpression twenty = new NumberExpression(20);

        IExpression addition = new AddExpression(ten, twenty);

        IExpression subtraction = new SubtractExpression(ten, twenty);

        IExpression finalExpression = new SubtractExpression(addition, subtraction);
        System.out.println(finalExpression.interpret());

    }
}
