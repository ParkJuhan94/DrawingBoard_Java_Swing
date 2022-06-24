package mylib;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// 완성
public abstract class KAbstractButton extends KComponent{
	protected ArrayList<KActionListener> alisteners = new ArrayList<>();
	protected String command;
	
	public KAbstractButton(String text) {
		super(text);
		command = text;
	}
	
	public void setActionCommand(String cmd) {
		command = cmd;
	}

	public void addKActionListener(KActionListener l) {
//		System.out.println(command + " 리스너추가됨");
		alisteners.add(l);		
		
	}
	
	public KActionListener[] getKActionListeners() {
		return (KActionListener[])(alisteners.toArray(new KActionListener[0]));
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		System.out.println("들어왔나?1");
		if(e.getID() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("Click" + this.text);
			for(KActionListener l : alisteners) {
				l.actionPerformed(new ActionEvent(this, e.getID(), this.command));
			}
		}    		
	}
}