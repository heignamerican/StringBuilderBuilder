package hm.moe.heignamerican.stringbuilderbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class StringBuilderCodecTest {
	@Test
	public void testEncode() {
		String tEncode = StringBuilderCodec.encode("hoge\n\nfuga\n\\nyo\n", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();\n");
		tExpectedBuilder.append("hoge.append(\"hoge\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"fuga\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"\\\\nyo\\n\");\n");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}

	@Test
	public void testEncode_ダブルクウォートのエスケープ() {
		String tEncode = StringBuilderCodec.encode("hoge\"1\"", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();\n");
		tExpectedBuilder.append("hoge.append(\"hoge\\\"1\\\"\");");

		String tString = tExpectedBuilder.toString();
		assertThat(tEncode, is(tString));
	}

	@Test
	public void testEncodeEncoded() {
		final StringBuilder tStringBuilder = new StringBuilder();
		tStringBuilder.append("hoge\r\n");
		tStringBuilder.append("\r\n");
		tStringBuilder.append("fuga\r\n");
		tStringBuilder.append("\\nyo\r\n");

		assertThat(tStringBuilder.toString(), is("hoge\r\n\r\nfuga\r\n\\nyo\r\n"));
	}
}
