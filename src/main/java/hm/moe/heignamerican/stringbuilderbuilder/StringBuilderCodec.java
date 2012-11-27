package hm.moe.heignamerican.stringbuilderbuilder;

public class StringBuilderCodec {
	public static String encode(final String aStringBuilderInstanceName, final String aNewLine, final String aInput) {
		return encode(aStringBuilderInstanceName, aNewLine, splitWithoutChop(aInput, aNewLine, -1));
	}

	public static String encode(final String aStringBuilderInstanceName, final String aNewLine, final String[] aInput) {
		final StringBuilder tBuilder = new StringBuilder();
		// StringBuilder変数宣言
		tBuilder.append("final StringBuilder ");
		tBuilder.append(aStringBuilderInstanceName);
		tBuilder.append(" = new StringBuilder();");
		tBuilder.append(aNewLine);
		// 各行
		final int tLength = aInput.length;
		for (int i = 0; i < tLength; i++) {
			String tString = aInput[i];

			if (i == tLength - 1 && tString.isEmpty()) {
				continue;
			}

			tString = tString.replaceAll("\\\\", "\\\\\\\\");
			tString = tString.replaceAll("\"", "\\\\\"");
			tString = tString.replaceAll("\r", "\\\\r");
			tString = tString.replaceAll("\n", "\\\\n");

			tBuilder.append(aStringBuilderInstanceName);
			tBuilder.append(".append(\"");
			tBuilder.append(tString);
			tBuilder.append("\");" + aNewLine);
		}

		return tBuilder.toString();
	}

	/**
	 * @param aInput
	 * @param aSimplePattern
	 *            シンプルなパターンでないと破綻する気がする
	 * @param aLimit
	 * @return
	 */
	private static String[] splitWithoutChop(final String aInput, final String aSimplePattern, final int aLimit) {
		final String[] tSplit = aInput.split(aSimplePattern, aLimit);
		final String[] tResult = new String[tSplit.length];
		final int tShortLength = tSplit.length - 1;
		for (int i = 0; i < tShortLength; i++) {
			tResult[i] = tSplit[i] + aSimplePattern;
		}
		tResult[tShortLength] = tSplit[tShortLength];
		return tResult;
	}
}
