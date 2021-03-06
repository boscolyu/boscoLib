package org.bosco.algorithm.lv1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;

public class ChangeFromTabToSpaceTest {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testChangeFromTabToSpace() {
		
		ChangeFromTabToSpace changer = new ChangeFromTabToSpace() ;
		String source = loadSourceFile("./src/main/resources/org/bosco/algorithm/lv1/spaceSource.txt", "utf-8");
		Assert.assertNotNull(source);
		Assert.assertNotSame("", source);
		
		String output = changer.changeFromTabToSpace(source);
		boolean result = changer.checkTabInSource(output);
		
		Assert.assertTrue(result);
	}

	private String loadSourceFile(String filePath, String charset) {

		try {
			Path path = FileSystems.getDefault().getPath(filePath);

			if (Files.notExists(path)) {
				logger.error("File is not exist {}", path.toString());
				return null;
			}
			
			List<String> source = Files.readAllLines(path, Charset.forName(charset));
			
			StringBuilder builder = new StringBuilder();
			for (String lineSource : source) {
				builder.append(lineSource);
				builder.append("\n");
			}
			return builder.toString();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
