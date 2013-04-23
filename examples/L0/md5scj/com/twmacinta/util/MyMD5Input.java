package privmem.md5scj.com.twmacinta.util;

import java.io.IOException;

public class MyMD5Input {

    /**
     * MD5 context
     */
    private MD5 md5;

    public MyMD5Input() {
        md5 = new MD5();
    }

    public void run(String arg) {

        byte[] bytes = arg.getBytes();
        for (byte c : bytes) {
            int ret = read(c);
            if (ret == -1)
                break;
        }

    }

    /**
     * Read a byte of data.
     *
     * @see java.io.FilterInputStream
     */
    public int read(byte c) {

        if (c == -1)
            return -1;

        if ((c & ~0xff) != 0) {
            // System.out.println("MD5InputStream.read() got character with (c & ~0xff) != 0)!");
        } else {
            md5.Update(c);
        }

        return c;
    }

    /**
     * Returns array of bytes representing hash of the stream as finalized for
     * the current state.
     *
     * @see MD5#Final
     */
    public byte[] hash() {
        return md5.Final();
    }

    public MD5 getMD5() {
        return md5;
    }

    public void finalHash(String filename) {
        //System.out.println(MD5.asHex(hash()) + "  " + filename);
    }

}
