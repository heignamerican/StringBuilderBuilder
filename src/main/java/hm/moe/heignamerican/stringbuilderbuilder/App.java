package hm.moe.heignamerican.stringbuilderbuilder;

import heignamerican.myutils.ExceptionUtil;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

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

		tyousei();
	}

	private static void tyousei() {
		mTextSplitPane.setDividerLocation(0.5);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new FocusEvent(mFrom, FocusEvent.FOCUS_GAINED));
	}

	private static void createComponents(Container aContainer) {
		JSplitPane tRootSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		aContainer.add(tRootSplitPane);

		JPanel tSelectPanel = new JPanel();
		mTextSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		tRootSplitPane.setTopComponent(tSelectPanel);
		tRootSplitPane.setBottomComponent(mTextSplitPane);

		{// select
			final JComboBox<String> tComboBox = new JComboBox<>();
			tComboBox.addItem("\\n");
			tComboBox.addItem("\\r\\n");
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
			JTextArea tFrom = new JTextArea();
			mTextSplitPane.setLeftComponent(tFrom);

			JTextArea tTo = new JTextArea();
			tTo.setEditable(false);
			mTextSplitPane.setRightComponent(tTo);

			tFrom.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent aE) {
					updateText();
				}

				@Override
				public void focusGained(FocusEvent aE) {
				}
			});

			mFrom = tFrom;
			mTo = tTo;
		}
	}

	private static JComboBox<String> mNewLine;
	private static JTextArea mFrom;
	private static JTextArea mTo;
	private static JSplitPane mTextSplitPane;

	private static void updateText() {
		final String tInput = mFrom.getText();
		final String tDisplayNewLineEscaped = mNewLine.getSelectedItem().toString();

		String tOutput;
		try {
			tOutput = StringBuilderCodec.encode(tInput, tDisplayNewLineEscaped, "\n", "tStringBuilder");
		} catch (Exception aCause) {
			tOutput = ExceptionUtil.toString(aCause);
		}

		mTo.setText(tOutput);
	}
}
