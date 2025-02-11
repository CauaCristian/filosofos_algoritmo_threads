import java.util.concurrent.Semaphore;

class Filosofo extends Thread {
    private int id;
    private Semaphore garfoEsquerdo, garfoDireito;

    public Filosofo(int id, Semaphore garfoEsquerdo, Semaphore garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((long) (Math.random() * 2000));
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo.");
        Thread.sleep((long) (Math.random() * 2000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();

                garfoEsquerdo.acquire();
                System.out.println("Filósofo " + id + " pegou o garfo esquerdo.");

                garfoDireito.acquire();
                System.out.println("Filósofo " + id + " pegou o garfo direito.");

                comer();

                garfoDireito.release();
                System.out.println("Filósofo " + id + " largou o garfo direito.");

                garfoEsquerdo.release();
                System.out.println("Filósofo " + id + " largou o garfo esquerdo.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
