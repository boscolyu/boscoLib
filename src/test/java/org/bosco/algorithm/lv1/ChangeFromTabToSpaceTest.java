package org.bosco.algorithm.lv1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
		String source = loadSourceFile("spaceSource.txt");
		Assert.assertNotNull(source);
		String output = changer.changeFromTabToSpace(source);
		
		
		
		
	}

	private String loadSourceFile(String filePath) {
		String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
			logger.info("info : {}", current);

		//Iterable<Path> paths = FileSystems.getDefault().getRootDirectories();
		//logger.info("info : {}", paths.toString());
		
			Path path = FileSystems.getDefault().getPath(current, "src/main/resources/org/bosco/algorithm/lv1", "spaceSource.txt");

			if (Files.notExists(path)) {
				logger.error("file is not exist {}", path.toString());
				return null;
			}
			List<String> source = Files.readAllLines(path, Charset.forName("utf-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
