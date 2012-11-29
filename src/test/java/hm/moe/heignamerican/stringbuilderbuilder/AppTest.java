package hm.moe.heignamerican.stringbuilderbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class AppTest {
	@Test
	public void test表示がCRLFシステムがCRLF() {
		String tInput = "hoge\r\nfuga\r\n\r\npiyo\r\n\\nyo";
		String tExpected = "final StringBuilder tStringBuilder = new StringBuilder();\r\ntStringBuilder.append(\"hoge\\r\\n\");\r\ntStringBuilder.append(\"fuga\\r\\n\");\r\ntStringBuilder.append(\"\\r\\n\");\r\ntStringBuilder.append(\"piyo\\r\\n\");\r\ntStringBuilder.append(\"\\\\nyo\");";
		assertThat(convertText(tInput, "\r\n", "\\r\\n", "\r\n"), is(tExpected));
	}

	@Test
	public void test表示がLFシステムがCRLF() {
		String tInput = "hoge\r\nfuga\r\n\r\npiyo\r\n\\nyo";
		String tExpected = "final StringBuilder tStringBuilder = new StringBuilder();\r\ntStringBuilder.append(\"hoge\\n\");\r\ntStringBuilder.append(\"fuga\\n\");\r\ntStringBuilder.append(\"\\n\");\r\ntStringBuilder.append(\"piyo\\n\");\r\ntStringBuilder.append(\"\\\\nyo\");";
		assertThat(convertText(tInput, "\n", "\\n", "\r\n"), is(tExpected));
	}

	@Test
	public void test表示がCRLFシステムがLF() {
		String tInput = "hoge\nfuga\n\npiyo\n\\nyo";
		String tExpected = "final StringBuilder tStringBuilder = new StringBuilder();\ntStringBuilder.append(\"hoge\\r\\n\");\ntStringBuilder.append(\"fuga\\r\\n\");\ntStringBuilder.append(\"\\r\\n\");\ntStringBuilder.append(\"piyo\\r\\n\");\ntStringBuilder.append(\"\\\\nyo\");";
		assertThat(convertText(tInput, "\r\n", "\\r\\n", "\n"), is(tExpected));
	}

	@Test
	public void test表示がLFシステムがLF() {
		String tInput = "hoge\nfuga\n\npiyo\n\\nyo";
		String tExpected = "final StringBuilder tStringBuilder = new StringBuilder();\ntStringBuilder.append(\"hoge\\n\");\ntStringBuilder.append(\"fuga\\n\");\ntStringBuilder.append(\"\\n\");\ntStringBuilder.append(\"piyo\\n\");\ntStringBuilder.append(\"\\\\nyo\");";
		assertThat(convertText(tInput, "\n", "\\n", "\n"), is(tExpected));
	}

	private String convertText(final String aInput, final String aDisplayNewLine, final String aDisplayNewLineEscaped, final String aSystemNewLine) {
		return StringBuilderCodec.encode(aInput, aDisplayNewLineEscaped, aSystemNewLine, "tStringBuilder");
	}
}
