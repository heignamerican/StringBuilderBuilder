package hm.moe.heignamerican.stringbuilderbuilder;

import heignamerican.myutils.ExceptionUtil;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;

public class App {
	private static final String NEW_LINE = System.lineSeparator();

	public static void main(String[] aArgs) {
		JFrame tFrame = new JFrame("StringBuilderBuilder");
		int tWidth = 800;
		int tHeight = 600;
		tFrame.setBounds(new Rectangle(tWidth, tHeight));
		{
			// 位置調整
			Dimension tScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
			tFrame.setLocation((tScreenSize.width - tWidth) / 2, (tScreenSize.height - tHeight) / 2);
		}
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createComponents(tFrame.getContentPane());

		tFrame.setVisible(true);
	}

	private static void createComponents(Container aContainer) {
		aContainer.setLayout(new GridLayout(1, 2));

		final TextArea tFrom = new TextArea();
		aContainer.add(tFrom);

		final TextArea tTo = new TextArea();
		tTo.setEditable(false);
		aContainer.add(tTo);

		tFrom.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent aEvent) {
				String tToText;
				try {
					tToText = StringBuilderCodec.encode("tStringBuilder", NEW_LINE, tFrom.getText());
				} catch (Exception aCause) {
					tToText = ExceptionUtil.toString(aCause);
				}
				tTo.setText(tToText);
			}

			@Override
			public void focusGained(FocusEvent aEvent) {
			}
		});
	}
}
