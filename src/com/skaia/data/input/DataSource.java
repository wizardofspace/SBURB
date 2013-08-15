//<editor-fold defaultstate="collapsed" desc="owner">
/**
 * This file was created by and is maintained by ::Kevin Siouve:: Who has
 * immediate ownership over all classes in packages com.mag.* and org.jelphi
 * (with noted exceptions.)
 */
//</editor-fold>
package com.skaia.data.input;

import java.io.Closeable;

/**
 * @author Kevin August <KevinAnonymousXD@gmail.com>
 * @version 1.0
 */
public interface DataSource extends Closeable {
    /**
     * Reads a (int)byte from the stream.
     * @return a single byte from a wrapping stream.
     */
    int readByte();
    /**
     * Reads a short from the stream.
     * @return a single byte from a wrapping stream.
     */
    short readShort();
    /**
     * Reads a integer from the stream.
     * @return a single byte from a wrapping stream.
     */
    int readInt();
    /**
     * Reads a long from the stream.
     * @return a single long from a wrapping stream.
     */
    long readLong();
    /**
     * Reads a float from the stream.
     * @return a single float from a wrapping stream.
     */
    float readFloat();
    /**
     * Reads a double from the stream.
     * @return a single double from a wrapping stream.
     */
    double readDouble();
    /**
     * Reads a boolean from the stream.
     * Most likely implemented as the same as byte.
     * * 0x00 = false
     * * 0x01 = true
     * @return a single boolean from a wrapping stream.
     */
    boolean readBoolean();
    /**
     * Reads a character from the stream.
     * @return a single character from a wrapping stream.
     */
    char readChar();
    
    /**
     * @param length Length that the string must be.
     * @return the string that is read from a wrapping stream.
     */
    String readUnicodeString(short length);
    /**
     * Reads a null-terminated Unicode string.
     * @return the unicode string
     */
    String readNullTerminatedUnicodeString();
    /**
     * Reads an s1-convention string, represented as a short defining the size of the string, then the string itself.
     * @return the string, defined with the s1-convention.
     */
    String readLengthUnicodeString();
    
    /**
     * @param length Length that the string must be.
     * @return the string that is read from a wrapping stream.
     */
    String readASCIIString(short length);
    /**
     * Reads a null-terminated ASCII string.
     * @return the ascii string
     */
    String readNullTerminatedASCIIString();
    /**
     * Reads an s1-convention string, represented as a short defining the size of the string, then the string itself.
     * @return the string, defined with the s1-convention.
     */
    String readLengthASCIIString();
}
