package org.example;

class BlackHoleThread extends Thread {
    private BlackHole blackHole;

    public BlackHoleThread(BlackHole blackHole) {
        this.blackHole = blackHole;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Black Hole " + blackHole.name + " is exerting gravitational pull!");
            try {
                Thread.sleep(1500); // Simulate gravitational effects delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBlackHole() {
            return blackHole;
    }
}
