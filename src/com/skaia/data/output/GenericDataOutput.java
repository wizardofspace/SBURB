//<editor-fold defaultstate="collapsed" desc="owner">
/**
 * This file was created by and is maintained by ::Kevin Siouve:: Who has
 * immediate ownership over all classes in packages com.mag.* and org.jelphi
 * (with noted exceptions.)
 */
//</editor-fold>
package com.skaia.data.output;

import java.io.IOException;
import java.io.OutputStream;


/**
 * @author Kevin August <KevinAnonymousXD@gmail.com>
 * @version 1.0
 */
public abstract class GenericDataOutput extends OutputStream implements DataSink {
    public GenericDataOutput() {
        
    }

    @Override
    public final void write(int b) throws IOException {
        writeByte(b);
    }

    @Override
    public abstract void writeByte(int i);

    @Override
    public void writeShort(short s) { // 2 bytes
        writeByte((s >>> 8) & 0xff);
        writeByte(s & 0xff);
    }

    @Override
    public void writeInt(int i) { // 4 bytes
        writeByte((i >>> 24) & 0xff);
        writeByte((i >>> 16) & 0xff);
        writeByte((i >>> 8) & 0xff);
        writeByte(i & 0xff);
    }

    @Override
    public void writeLong(long l) { // 8 bytes
        writeByte((int)((l >>> 56) & 0xff));
        writeByte((int)((l >>> 48) & 0xff));
        writeByte((int)((l >>> 40) & 0xff));
        writeByte((int)((l >>> 32) & 0xff));
        writeByte((int)((l >>> 24) & 0xff));
        writeByte((int)((l >>> 16) & 0xff));
        writeByte((int)((l >>>  8) & 0xff));
        writeByte((int)(l & 0xff));
    }

    @Override
    public void writeFloat(float f) { // 4 bytes
        //System.out.println("writing float "+f);
        writeInt(Float.floatToIntBits(f));
    }

    @Override
    public void writeDouble(double d) { // 8 bytes
        writeLong(Double.doubleToLongBits(d));
    }

    @Override
    public void writeBoolean(boolean b) { // 1 byte
        writeByte(b ? 0x1 : 0x0);
    }

    @Override
    public void writeChar(char c) { //2 bytes
        writeByte((c >>> 8) & 0xff);
        writeByte(c & 0xff);
        
    }

    @Override
    public void writeASCIIString(String asciistr) {
        for (char c : asciistr.toCharArray()) {
            writeByte((c & 0xff)); //LEAST SIGNIFICANT BYTE.
        }
    }

    @Override
    public void writeLengthASCIIString(String asciistr) {
        writeShort((short) asciistr.length());
        writeASCIIString(asciistr);
    }

    @Override
    public void writeNullTerminatedASCIIString(String asciistr) {
        writeASCIIString(asciistr);
        writeByte(0);
    }
    
    @Override
    public void writeUnicodeString(String asciistr) {
        for (char c : asciistr.toCharArray()) {
            writeChar(c);
        }
    }

    @Override
    public void writeLengthUnicodeString(String asciistr) {
        writeShort((short) asciistr.length());
        writeUnicodeString(asciistr);
    }

    @Override
    public void writeNullTerminatedUnicodeString(String asciistr) {
        writeUnicodeString(asciistr);
        writeChar('\0');
    }
}