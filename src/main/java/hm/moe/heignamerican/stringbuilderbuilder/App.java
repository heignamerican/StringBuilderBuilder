package hm.moe.heignamerican.stringbuilderbuilder;

import heignamerican.myutils.ExceptionUtil;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class App {
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
		JSplitPane tSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		aContainer.add(tSplitPane);

		Container tSelectPanel = new Panel();
		Container tTextPanel = new Panel();
		tSplitPane.setTopComponent(tSelectPanel);
		tSplitPane.setBottomComponent(tTextPanel);

		{// select
			final JComboBox<ComboBoxItem<String>> tComboBox = new JComboBox<>();
			tComboBox.addItem(new ComboBoxItem<String>("\\n", "\n"));
			tComboBox.addItem(new ComboBoxItem<String>("\\r\\n", "\r\n"));
			tComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent aItemEvent) {
					updateText();
				}
			});

			tSelectPanel.add(tComboBox);

			mNewLine = tComboBox;
		}

		{// text
			tTextPanel.setLayout(new GridLayout(1, 2));

			TextArea tFrom = new TextArea();
			tTextPanel.add(tFrom);

			TextArea tTo = new TextArea();
			tTo.setEditable(false);
			tTextPanel.add(tTo);

			tFrom.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent aEvent) {
					updateText();
				}

				@Override
				public void focusGained(FocusEvent aEvent) {
				}
			});

			mFrom = tFrom;
			mTo = tTo;
		}
	}

	private static JComboBox<ComboBoxItem<String>> mNewLine;
	private static TextArea mFrom;
	private static TextArea mTo;

	private static void updateText() {
		final String tInput = mFrom.getText();
		final String tDisplayNewLine = ComboBoxItem.getSelectedValue(mNewLine);
		final String tSystemNewLine = System.lineSeparator();

		String tOutput;
		try {
			tOutput = convertText(tInput, tDisplayNewLine, tSystemNewLine);
		} catch (Exception aCause) {
			tOutput = ExceptionUtil.toString(aCause);
		}

		mTo.setText(tOutput);
	}

	private static String convertText(final String aInput, final String aDisplayNewLine, final String aSystemNewLine) {
		return StringBuilderCodec.encode("tStringBuilder", aDisplayNewLine, aInput.replaceAll(aSystemNewLine, aDisplayNewLine)).replaceAll("\\n" + "$", aSystemNewLine);
	}
}
