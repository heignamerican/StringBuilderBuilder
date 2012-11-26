package hm.moe.heignamerican.stringbuilderbuilder;

import javax.swing.JComboBox;

public class ComboBoxItem<T> {
	private final String mName;
	private final T mValue;

	public String getName() {
		return mName;
	}

	public T getValue() {
		return mValue;
	}

	public ComboBoxItem(String aName, T aValue) {
		super();
		mName = aName;
		mValue = aValue;
	}

	@Override
	public String toString() {
		return mName;
	}

	public static <T> T getSelectedValue(JComboBox<ComboBoxItem<T>> aComboBox) {
		@SuppressWarnings("unchecked")
		ComboBoxItem<T> tItem = (ComboBoxItem<T>) aComboBox.getSelectedItem();
		return tItem.getValue();
	}
}
