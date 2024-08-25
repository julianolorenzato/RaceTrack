import java.util.concurrent.Semaphore;
import java.util.Random;

public class RaceTrack {
    private static final Semaphore helmetsSemaphore = new Semaphore(10, true);
    private static final Semaphore kartsSemaphore = new Semaphore(10, true);

    static int servedCustomers = 0;
    static float totalWaitTime = 0;
    static int helmetsUsed = 0;
    static int kartsUsed = 0;

    public static void main(String[] args) {
        System.out.println("O kartódromo abriu!");
        int i;
        for (i = 0; i < 12; i++) {
            System.out.println("Karts: " + kartsSemaphore.availablePermits() + " Capacetes: " + helmetsSemaphore.availablePermits());
            System.out.println("---------------> São " + (i + 8) + "h. <---------------");
            
            // Até 6 pessoas podem chegar a cada hora
            for (int j = 0; j < new Random().nextInt(6); j++) {
                servedCustomers++;
                Pilot p = new Pilot();
                System.out.println(p + " chegou!");
                
                new Thread(p).start();
            }
            
            sleep(2);
        }
        System.out.println("---------------> São " + (i + 8) + "h. O kartódromo fechou! <---------------");
        System.out.println("Clientes atendidos: " + servedCustomers);
        System.out.println("Tempo médio de espera: " + ((totalWaitTime / 1000) / servedCustomers) + " horas");
        System.out.println("Capacetes foram usados: " + helmetsUsed + " vezes.");
        System.out.println("Karts foram usados: " + kartsUsed + " vezes.");
    }

    static private void sleep() {
        try {
            int time = new Random().nextInt(12);
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static private void sleep(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    enum Age {
        KID,
        TEEN,
        ADULT
    }

    static class Pilot implements Runnable {
        Age age;

        String name;

        static int counter = 0;
        static String[] names = {
            "Alice", "Bob", "Charlie", "David", "Eva",
            "Fay", "George", "Hannah", "Ivy", "Jack",
            "Kara", "Liam", "Mia", "Nina", "Oliver",
            "Paul", "Quinn", "Rachel", "Sam", "Tina",
            "Ursula", "Vera", "Will", "Xander", "Yara",
            "Zach", "Ava", "Ben", "Clara", "Derek",
            "Ella", "Frank", "Grace", "Henry", "Iris",
            "James", "Kate", "Leo", "Maya", "Nate",
            "Olivia", "Parker", "Quincy", "Riley", "Sophie",
            "Tom", "Uma", "Violet", "Willow", "Xander",
            "Yasmine", "Zane", "Amelia", "Blake", "Chloe",
            "Daniel", "Eleanor", "Finn", "Giselle", "Hugo",
            "Isla", "Jasper", "Kylie", "Logan", "Molly",
            "Nora", "Oscar", "Penny", "Quinn", "Ryder",
            "Samantha", "Travis", "Ulysses", "Vera", "Wyatt",
            "Xena", "Yara", "Zeke", "Aiden", "Brianna",
            "Colin", "Daisy", "Eli", "Faith", "Gage",
            "Holly", "Ian", "Jade", "Kieran", "Luna",
            "Mason", "Nina", "Owen", "Piper", "Quincy"
        };

        public Pilot() {
            this.age = randomAge();
            this.name = names[counter++];
        }

        @Override
        public void run() {
            try {
                long startTime = System.currentTimeMillis();
                if (this.age == Age.ADULT) {
                    if (kartsSemaphore.availablePermits() == 0) {
                        System.out.println(this + " está aguardando um kart.");
                    }
                    kartsSemaphore.acquire();
                    kartsUsed++;
                    
                    if (helmetsSemaphore.availablePermits() == 0) {
                        System.out.println(this + " está aguardando um capacete.");
                    }
                    helmetsSemaphore.acquire();
                    helmetsUsed++;
                } else {
                    if (helmetsSemaphore.availablePermits() == 0) {
                        System.out.println(this + " está aguardando um capacete.");
                    }
                    helmetsSemaphore.acquire();
                    helmetsUsed++;
                    
                    if (kartsSemaphore.availablePermits() == 0) {
                        System.out.println(this + " está aguardando um kart.");
                    }
                    kartsSemaphore.acquire();
                    kartsUsed++;
                }
                long endTime = System.currentTimeMillis();
                totalWaitTime += endTime - startTime;
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println(this + " pegou os itens e está na pista.");

                sleep();
                
                kartsSemaphore.release();
                helmetsSemaphore.release();

                System.out.println(this + " finalizou a corrida e devolveu os itens.");
            }
        }

        private Age randomAge() {
            Age[] ages = Age.values();
            int randIndex = new Random().nextInt(ages.length);
            return ages[randIndex];
        }

        @Override
        public String toString() {
            return this.name + " (" + this.age + ")";
        }
    }
}
