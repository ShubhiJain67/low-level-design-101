package behavioral.interpreter.sample.expressions;

public class AddExpression implements IExpression {

    private final IExpression left;
    private final IExpression right;

    public AddExpression(IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}
