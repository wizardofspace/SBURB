package com.skaia.data.input;

import java.io.InputStream;

/**
 * @author Kevin
 */
public abstract class GenericDataInput extends InputStream implements DataSource {
    private static final int IO_EOF = -1;

    @Override
    public int readByte() {
        try {
            return read() & 0xff;
        } catch (Exception ex) {
            return IO_EOF;
        }
    }

    @Override
    public short readShort() {
        return (short)((readByte() << 8) + readByte());
    }

    @Override
    public int readInt() {
        return (readByte() << 24) +
               (readByte() << 16) +
               (readByte() << 8)  + readByte();
    }

    @Override
    public long readLong() {
        return (((long)(readByte()) << 56) +
                      ((long)(readByte()) << 48) +
                      ((long)(readByte()) << 40) +
                      ((long)(readByte()) << 32) +
                      ((long)(readByte()) << 24) +
                      ((long)(readByte()) << 16) +
                      ((long)(readByte()) << 8)  + readByte());
    }

    @Override
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public boolean readBoolean() {
        return readByte() == 1;
    }

    @Override
    public char readChar() {
        return (char)((readByte() << 8) + (readByte()));
    }

    @Override
    public String readLengthASCIIString() {
        return readASCIIString(readShort());
    }

    @Override
    public String readLengthUnicodeString() {
        return readUnicodeString(readShort());
    }

    @Override
    public String readNullTerminatedASCIIString() {
        StringBuilder s = new StringBuilder(32);
        for (;;) {
            byte c = (byte)readByte();
            if (c == 0) {
                break;
            }
            s.append((char)c);
        }
        return s.toString();
    }

    @Override
    public String readNullTerminatedUnicodeString() {
        StringBuilder s = new StringBuilder(32);
        for (;;) { //why do I even use for(;;) when there's a perfectly usable while(true)?
            char c = readChar();
            if (c == '\0') {
                break;
            }
            s.append(c);
        }
        return s.toString();
    }

    @Override
    public String readASCIIString(short length) {
        StringBuilder s = new StringBuilder(length);
        for (short sh = 0; sh < length; sh++) {
            byte b = (byte)readByte();
            s.append((char)b);
        }
        return s.toString();
    }

    @Override
    public String readUnicodeString(short length) {
        StringBuilder s = new StringBuilder(length);
        for (short sh = 0; sh < length; sh++) {
            s.append(readChar());
        }
        return s.toString();
    }
}
