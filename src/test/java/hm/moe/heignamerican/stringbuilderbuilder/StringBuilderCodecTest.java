package hm.moe.heignamerican.stringbuilderbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class StringBuilderCodecTest {
	@Test
	public void testEncode() {
		String tEncode = StringBuilderCodec.encode("hoge\n\nfuga\n\\nyo\n", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();").append("\n");
		tExpectedBuilder.append("final String tNewLine = \"\\n\";").append("\n");
		tExpectedBuilder.append("hoge.append(\"hoge\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("hoge.append(\"\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("hoge.append(\"fuga\").append(tNewLine);").append("\n");
		tExpectedBuilder.append("hoge.append(\"\\\\nyo\").append(tNewLine);");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}
	@Test
	public void testEncode1行改行なし() {
		String tEncode = StringBuilderCodec.encode("a", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();").append("\n");
		tExpectedBuilder.append("hoge.append(\"a\");");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}
	@Test
	public void testEncode1行改行あり() {
		String tEncode = StringBuilderCodec.encode("a\n", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();").append("\n");
		tExpectedBuilder.append("final String tNewLine = \"\\n\";").append("\n");
		tExpectedBuilder.append("hoge.append(\"a\").append(tNewLine);");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}

	@Test
	public void testEncodeタブ文字() {
		String tEncode = StringBuilderCodec.encode("\t", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();").append("\n");
		tExpectedBuilder.append("hoge.append(\"\\t\");");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
	}

	@Test
	public void testEncode_ダブルクウォートのエスケープ() {
		String tEncode = StringBuilderCodec.encode("hoge\"1\"", "\\n", "\n", "hoge");

		final StringBuilder tExpectedBuilder = new StringBuilder();
		tExpectedBuilder.append("final StringBuilder hoge = new StringBuilder();").append("\n");
		tExpectedBuilder.append("hoge.append(\"hoge\\\"1\\\"\");");

		assertThat(tEncode, is(tExpectedBuilder.toString()));
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
