import java.util.Stack;
import java.util.Random;

class Pilot implements Runnable {
    enum Age {
        KID,
        TEEN,
        ADULT
    }

    String name;
    Age age;

    Stack<Helmet> availableHelmets;
    Stack<Kart> availableKarts;

    public Pilot(String name, Stack<Helmet> helmets, Stack<Kart> karts) {
        this.name = name;
        this.age = randomAge();
        this.availableHelmets = helmets;
        this.availableKarts = karts;
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