package com.skaia.data.output;

import io.netty.channel.ChannelHandlerContext;
import java.io.Closeable;
import java.io.Flushable;

/**
 * A data sink is a buffer that is written to, then flushed to a seperate stream.
 * @author Kevin
 */

public interface DataSink extends Closeable, Flushable {
    /**
     * Writes a byte to the buffer, which is flushed to the stream using writeTo().
     * @param i an integer (which is truncated to a byte)
     */
    void writeByte(int i);
    /**
     * Writes a short to the buffer, which is flushed to the stream using writeTo().
     * @param s a short
     */
    void writeShort(short s);
    /**
     * Writes an integer to the buffer, which is flushed to the stream using writeTo().
     * @param i an integer
     */
    void writeInt(int i);
    /**
     * Writes a long to the buffer, which is flushed to the stream using writeTo().
     * @param l a long
     */
    void writeLong(long l);
    /**
     * Writes a float to the buffer, which is flushed to the stream using writeTo().
     * @param f a float
     */
    void writeFloat(float f);
    /**
     * Writes a double to the buffer, which is flushed to the stream using writeTo().
     * @param d a double
     */
    void writeDouble(double d);
    /**
     * Writes a boolean to the buffer, which is flushed to the stream using writeTo().
     * Usually represented as:
     * true - 0x01
     * false - 0x00
     * @param b a boolean
     */
    void writeBoolean(boolean b);
    /**
     * Writes a character to the buffer, which is flushed to the stream using writeTo().
     * @param c a character
     */
    void writeChar(char c);
    
    /**
     * Writes a string to the buffer character-by-character, which is flushed to the stream using writeTo().
     * @param asciistr an ascii-formatted string
     */
    void writeASCIIString(String asciistr);
    /**
     * Writes a null terminated string.
     * @param asciistr an ascii-formatted string
     */
    void writeNullTerminatedASCIIString(String asciistr);
    /**
     * Writes a string's length, then the string itself, which is flushed to the stream using writeTo().
     * @param asciistr an ascii-formatted string
     */
    void writeLengthASCIIString(String asciistr);
    /**
     * Writes a string to the buffer character-by-character, which is flushed to the stream using writeTo().
     * @param unicodestr a unicode string
     */
    void writeUnicodeString(String unicodestr);
    /**
     * Writes a null terminated string.
     * @param asciistr a unicode string
     */
    void writeNullTerminatedUnicodeString(String asciistr);
    /**
     * Writes a string's length, then the string itself, which is flushed to the stream using writeTo().
     * @param unicodestr a unicode string
     */
    void writeLengthUnicodeString(String unicodestr);

    /**
     * Writes the buffer to the specified output stream. </br><b>This does not clear the stream. </b>
     * @param o the OutputStream to be written to.
     */
    void writeTo(ChannelHandlerContext ctx);
    
    /**
     * Clears the buffer so it can be reused.
     */
    void clear();
}
