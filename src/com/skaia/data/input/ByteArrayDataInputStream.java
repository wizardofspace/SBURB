package com.skaia.data.input;

import java.io.IOException;

/**
 *
 * @author Kevin
 */
public class ByteArrayDataInputStream extends GenericDataInput {
    private byte[] array;
    int index;
    
    public ByteArrayDataInputStream(byte[] array) {
        this.array = array;
    }

    @Override
    public int read() throws IOException {
        if (index < array.length)
            return array[index++];
        return -1;
    }
}
