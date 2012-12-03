package hm.moe.heignamerican.stringbuilderbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class AppTest {
	@Test
	public void test表示がCRLFシステムがCRLF() {
		String tEncoded = convertText("hoge\r\nfuga\r\n\r\npiyo\r\n\\nyo", "\r\n", "\\r\\n", "\r\n");

		StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder tStringBuilder = new StringBuilder();").append("\r\n");
		tExpectedBuilder.append("final String tNewLine = \"\\r\\n\";").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"hoge\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"fuga\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"piyo\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\\\\nyo\");");

		assertThat(tEncoded, is(tExpectedBuilder.toString()));
	}

	@Test
	public void test表示がLFシステムがCRLF() {
		String tEncoded = convertText("hoge\r\nfuga\r\n\r\npiyo\r\n\\nyo", "\n", "\\n", "\r\n");

		StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder tStringBuilder = new StringBuilder();").append("\r\n");
		tExpectedBuilder.append("final String tNewLine = \"\\n\";").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"hoge\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"fuga\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"piyo\").append(tNewLine);").append("\r\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\\\\nyo\");");

		assertThat(tEncoded, is(tExpectedBuilder.toString()));
	}

	@Test
	public void test表示がCRLFシステムがLF() {
		String tEncoded = convertText("hoge\nfuga\n\npiyo\n\\nyo", "\r\n", "\\r\\n", "\n");

		StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder tStringBuilder = new StringBuilder();").append("\n");
		tExpectedBuilder.append("final String tNewLine = \"\\r\\n\";").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"hoge\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"fuga\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"piyo\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\\\\nyo\");");

		assertThat(tEncoded, is(tExpectedBuilder.toString()));
	}

	@Test
	public void test表示がLFシステムがLF() {
		String tEncoded = convertText("hoge\nfuga\n\npiyo\n\\nyo", "\n", "\\n", "\n");

		StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder tStringBuilder = new StringBuilder();").append("\n");
		tExpectedBuilder.append("final String tNewLine = \"\\n\";").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"hoge\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"fuga\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"piyo\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("tStringBuilder.append(\"\\\\nyo\");");

		assertThat(tEncoded, is(tExpectedBuilder.toString()));
	}

	private String convertText(final String aInput, final String aDisplayNewLine, final String aDisplayNewLineEscaped, final String aSystemNewLine) {
		return StringBuilderCodec.encode(aInput, aDisplayNewLineEscaped, aSystemNewLine, "tStringBuilder");
	}
}
