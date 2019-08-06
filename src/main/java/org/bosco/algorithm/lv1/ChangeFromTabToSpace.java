package org.bosco.algorithm.lv1;

public class ChangeFromTabToSpace {

	/*
	 * input source file is tab indentation.
	 * Please change the indentation from tab to space(4) without java api.
	 * For example, replaceAll()
	 */
	public String changeFromTabToSpace(String source) {
		//source.replace("\t", "    ");
		return source.replaceAll("\t", "    ");
	}

	/*
	 * Please implement to check tab in source file without java api
	 */
	public boolean checkTabInSource(String source) {
		
		return true;
	}
}
