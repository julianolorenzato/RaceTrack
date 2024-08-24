import java.util.Stack;
import java.util.Queue;
import java.util.Random;

class Pilot implements Runnable {
    enum Age {
        KID,
        TEEN,
        ADULT
    }

    Age age;

    int id = 0;
    static int counter = 0;

    Stack<Helmet> availableHelmets;
    Stack<Kart> availableKarts;

    Queue<Pilot> pickHelmetQueue;
    Queue<Pilot> pickKartQueue;

    public Pilot(
            Stack<Helmet> helmets,
            Stack<Kart> karts,
            Queue<Pilot> pickHelmetQueue,
            Queue<Pilot> pickKartQueue) {
        this.id = counter++;
        this.age = randomAge();

        this.availableHelmets = helmets;
        this.availableKarts = karts;

        this.pickHelmetQueue = pickHelmetQueue;
        this.pickKartQueue = pickKartQueue;
    }

    private Age randomAge() {
        Age[] ages = Age.values();
        int randIndex = new Random().nextInt(ages.length);
        return ages[randIndex];
    }

    private void sleep() {
        try {
            int time = new Random().nextInt(5);
            Thread.sleep(time * 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        Helmet pickedHelmet;
        Kart pickedKart;

        switch (this.age) {
            case KID:
                pickedHelmet = this.availableHelmets.pop();
                this.sleep();
                pickedKart = this.availableKarts.pop();

                break;
            case TEEN:
                pickedHelmet = this.availableHelmets.pop();
                this.sleep();
                pickedKart = this.availableKarts.pop();

                break;
            case ADULT:
                pickedKart = this.availableKarts.pop();
                this.sleep();
                pickedHelmet = this.availableHelmets.pop();

                break;
        }

        // availableHelmets.add(pickedHelmet);
    }
}