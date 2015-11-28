/* 
 * @(#)LineBufferedInputStream.java $version 2010. 9. 1.
 * 
 * Copyright 2010 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */

package org.bosco.lib.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/**
 * LineBufferedInputStream provides capability to read a line from an InputStream.
 * 
 */
public class LineBufferedInputStream extends BufferedInputStream {

	public LineBufferedInputStream(InputStream in) {
		super(in);
	}

	public LineBufferedInputStream(InputStream in, int size) {
		super(in, size);
	}

	/**
	 * read a line delimeted by 'CR LF' or 'LF'
	 * 
	 * @param charsetName a name of charset to decode bytes
	 * @param limit maximum length of line read
	 * @return line without delimeter, 'CR LF' or 'LF'
	 * @throws IOException
	 * @throws BufferOverflowException line read exceeds limit 
	 */
	public String readLine(String charsetName, int limit) throws IOException, BufferOverflowException {

		ByteBuffer byteBuffer = ByteBuffer.allocate(limit);

		boolean seenCR = false;

		// seenCR: 1 byte read-ahead for 'CR LF' pattern
		while (true) {
			int c = read();
			if (c == -1) {
				throw new IOException("EOF reached while reading a line (read " + byteBuffer.position() + " bytes)");
			}
			if (c == '\r') {
				if (seenCR) {
					// duplicated CR, put CR
					byteBuffer.put((byte)'\r');
					// seenCR = true;
				}
				seenCR = true;
				continue;
			}
			if (c == '\n') {
				break;
			} else {
				if (seenCR) {
					// not EOL, put CR
					byteBuffer.put((byte)'\r');
					seenCR = false;
				}
			}
			byteBuffer.put((byte)c);
		}

		return new String(byteBuffer.array(), 0, byteBuffer.position(), charsetName);

	}

}