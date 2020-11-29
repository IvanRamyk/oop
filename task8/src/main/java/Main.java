public class Main {

    static public void main(String[] args) throws InterruptedException {

        CounterValidator counterCheck = new CounterValidator(new CustomReentrantLock(), 10);
        boolean isValid = counterCheck.runValidation();

        System.out.println(isValid ? "Valid lock" : "Invalid lock");
    }
}