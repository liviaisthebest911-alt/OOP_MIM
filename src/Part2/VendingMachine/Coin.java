package Part2.VendingMachine;

import java.util.Objects;

public class Coin {
    private final long value;
    private static final long[] ACCEPTED_VALUES = {1000, 2000, 5000, 10000};

    public Coin(long value){
        this.value=value;
    }

    public long getValue() {return value; }

    public static boolean isValid(long value){
        for(long vl : ACCEPTED_VALUES){
            if(vl == value){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return value == coin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
