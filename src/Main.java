import java.util.concurrent.Semaphore;

public static void main(String[] args) {
    int numeroDeFilosofos = 5;
    Semaphore[] garfos = new Semaphore[numeroDeFilosofos];
    Filosofo[] filosofos = new Filosofo[numeroDeFilosofos];

    for (int i = 0; i < numeroDeFilosofos; i++) {
        garfos[i] = new Semaphore(1);
    }

    for (int i = 0; i < numeroDeFilosofos; i++) {
        Semaphore garfoEsquerdo = garfos[i];
        Semaphore garfoDireito = garfos[(i + 1) % numeroDeFilosofos];

        if (i == numeroDeFilosofos - 1) {
            filosofos[i] = new Filosofo(i, garfoDireito, garfoEsquerdo);
        } else {
            filosofos[i] = new Filosofo(i, garfoEsquerdo, garfoDireito);
        }

        filosofos[i].start();
    }
}