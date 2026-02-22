package demo;

interface Operation {
    int compute(int a, int b);
}

class AddOperation implements Operation {
    public int compute(int a, int b) {
        return a + b;
    }
}

class MaxOperation implements Operation {
    public int compute(int a, int b) {
        return a > b ? a : b;
    }
}

public class TargetProgramRTA {

    public static int execute(Operation op, int a, int b) {
        return op.compute(a, b);   // dynamic dispatch
    }

    public static void main(String[] args) {

        Operation op = new AddOperation();   // ⚠ Only AddOperation instantiated

        int result = execute(op, 3, 5);
        System.out.println(result);
    }
}


