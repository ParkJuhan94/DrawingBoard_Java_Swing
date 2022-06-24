package mylib;

public class KToolBar extends KContainer{
	
	public KToolBar() {}
	
	public KToolBar(String text) {
		super(text);
	}
	
	public void addBtn(KButton kButton) {
		add(kButton);
		kButton.setParent(this);
	}
}
