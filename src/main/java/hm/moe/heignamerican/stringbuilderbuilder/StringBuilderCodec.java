package hm.moe.heignamerican.stringbuilderbuilder;

import heignamerican.myutils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class StringBuilderCodec {
	public static String encode(final String aInput, final String aDisplayNewLineEscaped, final String aSystemNewLine, final String aBuilderName) {
		final String[] tSplit = aInput.split(aSystemNewLine, -1);

		final String tBuilderName = aBuilderName;
		final List<String> tList = new ArrayList<>();
		tList.add("final StringBuilder " + tBuilderName + " = new StringBuilder();");
		final int tLength = tSplit.length;
		if (tLength > 1)
			tList.add("final String tNewLine = \"" + aDisplayNewLineEscaped + "\";");

		for (int i = 0; i < tLength; i++) {
			final String tString = tSplit[i];

			final String tEscaped;
			{
				String tTemp = tString;
				tTemp = tTemp.replaceAll("\\\\", "\\\\\\\\");
				tTemp = tTemp.replaceAll("\"", "\\\\\"");
				tTemp = tTemp.replaceAll("\t", "\\\\\\t");
				tTemp = tTemp.replaceAll("\r", "\\\\\\r");
				tTemp = tTemp.replaceAll("\n", "\\\\\\n");
				tEscaped = tTemp;
			}

			if (i == tLength - 1) {
				if (tString.isEmpty()) {
					break; // XXX 必要?
				}
				tList.add(tBuilderName + ".append(\"" + tEscaped + "\");");
			} else {
				tList.add(tBuilderName + ".append(\"" + tEscaped + "\").append(tNewLine);");
			}
		}

		return StringUtil.mkString(aSystemNewLine, tList);
	}
}
