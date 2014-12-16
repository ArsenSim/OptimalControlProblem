public class Application {

    public static void main(String[] args) {
        int time = 6;
        History history = new History(time);
        history.iterate();
        new View(time, history.getNodesList());
    }
}