package chess;

import java.util.Arrays;

public class ByteArrayWrapper implements Cloneable {
    private final byte[] rawHash = new byte[20];
    @Override
    public synchronized boolean equals(Object o) {
        if (o instanceof ByteArrayWrapper) {
            return Arrays.equals(rawHash, ((ByteArrayWrapper) o).rawHash);
        }
        return false;
    }
    @Override
    public synchronized int hashCode() {
        return rawHash[0] | (((int) rawHash[1]) << 8) | (((int) rawHash[2]) << 16) | (((int) rawHash[3]) << 24);
    }
    public ByteArrayWrapper(byte[] me) {
        if (me.length != 20) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(me, 0, rawHash, 0, 20);
    }
    @Override
    public synchronized ByteArrayWrapper clone() {
        return new ByteArrayWrapper(Arrays.copyOf(rawHash, 20));
    }
    public synchronized void xor(byte[] other) {
        for (int i = 0; i < 20; i++) {
            rawHash[i] ^= other[i];
        }
    }
    public synchronized void xor(ByteArrayWrapper other) {
        for (int i = 0; i < 20; i++) {
            rawHash[i] ^= other.rawHash[i];
        }
    }
}
