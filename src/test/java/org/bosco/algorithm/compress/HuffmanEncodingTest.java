package org.bosco.algorithm.compress;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by boscolyu on 2017. 11. 9..
 */
public class HuffmanEncodingTest {

    @Test
    public void testHuffmanEncoding() {
        String src = "BETTER_LATE_THAN_NEVER";
        byte[] target = new byte[70];
        target = new Huffman().encoding(src);
        //Assert.assertEquals(src, new Huffman().decoding(target));

    }

}
