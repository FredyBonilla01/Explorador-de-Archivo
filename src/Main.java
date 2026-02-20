import presenter.Runner;

public class Main {
    public static void main(String[] args) {
        Runner runner = new Runner();
        System.out.println(args[0]);
        runner.start(args[0]);
    }
}
