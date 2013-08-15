package com.skaia.data.output;

import io.netty.channel.ChannelHandlerContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * @author Kevin
 */

public class ByteArrayDataOutputStream extends GenericDataOutput {
    private ByteArrayOutputStream baos;

    public ByteArrayDataOutputStream() {
        baos = new ByteArrayOutputStream();
    }

    @Override
    public void writeByte(int i) {
        baos.write(i);
    }

    @Override
    public void writeTo(ChannelHandlerContext ctx) {
        ctx.write(baos.toByteArray());
    }

    @Override
    public void close() throws IOException {
        clear();
        baos.close();
    }

    @Override
    public void clear() {
        baos.reset();
    }
    
    public byte[] toByteArray() {
        return baos.toByteArray();
    }
}
