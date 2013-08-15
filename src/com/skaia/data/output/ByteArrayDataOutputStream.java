//<editor-fold defaultstate="collapsed" desc="owner">
/**
 * This file was created by and is maintained by ::Kevin Siouve:: Who has
 * immediate ownership over all classes in packages com.mag.* and org.jelphi
 * (with noted exceptions.)
 */
//</editor-fold>

package com.skaia.data.output;

import io.netty.channel.ChannelHandlerContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Kevin August <KevinAnonymousXD@gmail.com>
 * @version 1.0
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
