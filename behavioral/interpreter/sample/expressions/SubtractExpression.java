package behavioral.interpreter.sample.expressions;

public class SubtractExpression implements IExpression {

    private final IExpression left;
    private final IExpression right;

    public SubtractExpression(IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}
