import java.io.Serializable;

public class TaskObject implements ITask, Serializable {
    private static final long serialVersionUID = 1L;
    private int number;
    private int result;

    @Override
    public void setExecNumber(int x) {
        this.number = x;
    }

    @Override
    public void exec() {
        this.result = findLargestPrime(number);
    }

    @Override
    public int getResult() {
        return result;
    }

    private int findLargestPrime(int n) {
        for (int i = n; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
