package org.bosco.algorithm.compress;

import java.util.HashMap;

/**
 * Created by boscolyu on 2017. 11. 9..
 */
public class Huffman {

    public byte[] encoding(String src) {
        byte []encodingData = null;
        // character count
        char[] srcArray = src.toCharArray();
        HashMap<Character, Integer> srcDataCount = new HashMap<>();
        for (char srcData : srcArray) {
            Integer count = srcDataCount.get(new Character(srcData));
            if (count == null)
                srcDataCount.put(new Character(srcData), new Integer(0));
            else
                srcDataCount.put(new Character(srcData), count++);
        }

        srcDataCount.entrySet();



        // sorting


        // configure tree

        // write out with tree table
        return encodingData;
    }

    public String decoding(byte[] src) {
        return null;

    }
}
