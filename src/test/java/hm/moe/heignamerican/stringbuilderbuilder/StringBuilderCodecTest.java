package hm.moe.heignamerican.stringbuilderbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class StringBuilderCodecTest {
	@Test
	public void testEncode() {
		String tEncode = StringBuilderCodec.encode("hoge", "\n", "hoge\n\nfuga\n\\nyo\n");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();\n");
		tExpectedBuilder.append("hoge.append(\"hoge\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"fuga\\n\");\n");
		tExpectedBuilder.append("hoge.append(\"\\\\nyo\\n\");\n");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}

	@Test
	public void test1() {
		String tEncode = StringBuilderCodec.encode("hoge", "\n", "hoge\"1\"");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();\n");
		tExpectedBuilder.append("hoge.append(\"hoge\\\"1\\\"\");\n");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}

	@Test
	public void nyo() {
		final StringBuilder tStringBuilder = new StringBuilder();
		tStringBuilder.append("hoge\r\n");
		tStringBuilder.append("\r\n");
		tStringBuilder.append("fuga\r\n");
		tStringBuilder.append("\\nyo\r\n");

		assertThat(tStringBuilder.toString(), is("hoge\r\n\r\nfuga\r\n\\nyo\r\n"));
	}
}
