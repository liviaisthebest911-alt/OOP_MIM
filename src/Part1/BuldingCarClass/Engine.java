package Part1.BuldingCarClass;

public class Engine {
    private final int horsepower;
    private boolean isStarted;

    public Engine(int horsepower) {
        this.horsepower = horsepower;
        this.isStarted = false;
    }

    public void start() {
        if (!isStarted) {
            this.isStarted = true;
            System.out.println("Engine started.");
        } else {
            System.out.println("Engine is already running.");
        }
    }

    public void stop() {
        if (isStarted) {
            this.isStarted = false;
            System.out.println("Engine stopped.");
        } else {
            System.out.println("Engine is already off.");
        }
    }

    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public String toString() {
        return "Engine[HP=" + horsepower + ", Status=" + (isStarted ? "Running" : "Off") + "]";
    }

}
