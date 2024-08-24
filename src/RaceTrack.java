import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class RaceTrack {
    public static void main(String[] args) {
        Stack<Helmet> availableHelmets = new Stack<Helmet>();
        for (int i = 0; i < 10; i++) {
            availableHelmets.add(new Helmet(i));
        }

        Stack<Kart> availableKarts = new Stack<Kart>();
        for (int i = 0; i < 10; i++) {
            availableKarts.add(new Kart(i));
        }

        Queue<Pilot> pickHelmetQueue = new PriorityQueue<Pilot>(RaceTrack::pickHelmetQueueComparator);
        Queue<Pilot> pickKartQueue = new LinkedList<Pilot>();

        System.out.println("O kartódromo abriu!");
        for (int i = 0; i < 12; i ++) {
            System.out.println("São " + (i + 8) + "h.");
            
        }
        System.out.println("O kartódromo fechou!");
    }

    static private int pickHelmetQueueComparator(Pilot p1, Pilot p2) {
        if (p1.age.equals(Pilot.Age.KID) && p2.age.equals(Pilot.Age.TEEN)) {
            return -1;
        } else if (p1.age.equals(Pilot.Age.TEEN) && p2.age.equals(Pilot.Age.KID)) {
            return 1;
        } else {
            return 0;
        }
    }
}
