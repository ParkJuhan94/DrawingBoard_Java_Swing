package mylib;

import java.util.ArrayList;

public class KButton extends KAbstractButton {	
	protected KButton btnType;
	protected ArrayList<KActionListenerOne> listenerList = new ArrayList<>();
	
	public KButton(String text) {
		super(text);
	}
	
	public KButton getBtnType() {
		return this.btnType;
	}
		
	public void setBtnType(KButton b) {
		this.btnType = b;
	}	
}
