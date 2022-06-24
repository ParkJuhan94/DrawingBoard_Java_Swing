package mylib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KActionListenerOne implements ActionListener {
	public KButton b;

	@Override
	public void actionPerformed(ActionEvent e) {
		b.setBtnType((KButton) e.getSource());
	}

	public void setBtn(KButton b) {
		this.b = b;
	}

}
