/**
 * Represents cost function which depends on time
 */
public class Cost extends Polynomial{

    /**
     * String representing function
     */
    private String func;

    public Cost(String func) {
        super(func);
        this.func = func;
    }

    /**
     * Calculates cost function
     */
    public int calc(int t) {
        return super.eval(t);
    }

    @Override
    public String toString() {
        return "Cost{" +
                "func='" + func + '\'' +
                '}';
    }
}
