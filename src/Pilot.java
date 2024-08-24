import java.util.concurrent.Semaphore;
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

    Semaphore helmetsSemaphore;
    Semaphore kartsSemaphore;

    Queue<Pilot> pickHelmetQueue;
    Queue<Pilot> pickKartQueue;

    public Pilot(Semaphore helmetsSemaphore, Semaphore kartsSemaphore, Queue<Pilot> pickHelmetQueue, Queue<Pilot> pickKartQueue) {
        this.id = counter++;
        this.age = randomAge();

        this.helmetsSemaphore = helmetsSemaphore;
        this.kartsSemaphore = kartsSemaphore;

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


        try {
            if (this.age == Age.ADULT) {
                System.out.println("Piloto " + this.id + " entrou na fila para pegar um kart!");
                this.pickKartQueue.add(this);
                this.kartsSemaphore.acquire();
                this.pickKartQueue.remove();
                System.out.println("Piloto " + this.id + " pegou um kart!");
                this.helmetsSemaphore.acquire();
                System.out.println("Piloto " + this.id + " pegou um capacete!");
            } else {
                this.helmetsSemaphore.acquire();
                System.out.println("Piloto " + this.id + " pegou um capacete!");
                this.kartsSemaphore.acquire();
                System.out.println("Piloto " + this.id + " pegou um kart!");
            }

            this.wait(5000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            this.helmetsSemaphore.release();
            this.kartsSemaphore.release();
        }

        // availableHelmets.add(pickedHelmet);
    }
}