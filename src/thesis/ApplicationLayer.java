package thesis;

import java.util.Random;

public class ApplicationLayer extends SimEngine {

    //generate random number of bits in the packet
    public int generateData() {
        return new Random().nextInt(524280 - 416 + 1) + 416;
    }

}
