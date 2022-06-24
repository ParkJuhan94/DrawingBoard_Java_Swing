package app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mylib.*;

//여기는 응용
class PainterKFrame extends KFrame {
	KMenuBar menuBar;
	KToolBar toolBar;
	KMenu fileMenu, shapeMenu, colorMenu;
	KMenuItem miOpen, miSave, miRect, miOval, miLine, miBlack, miRed, miBlue;
	KButton btnPaintMode, btnGrouping, btnDup, btnDel;
	ArrayList<Figure> figList = new ArrayList<>();
	int btnIdx, startX, startY;
	int drawingMode = 0, inButton = 0, captureCnt = 0, figType = 0, colorType = 1, groupMode = 0, paintMode = 0, dupMode = 0, delMode = 0;;
	myFigActionListener figListener = new myFigActionListener();
	myColorActionListener colorListener = new myColorActionListener();
	myGroupActionListener groupListener = new myGroupActionListener();
	myPaintActionListener paintListener = new myPaintActionListener();
	myDupActionListener dupListener = new myDupActionListener();
	myDelActionListener delListener = new myDelActionListener();

	class myFigActionListener implements KActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingMode = 1;
			if (e.getActionCommand().equals("사각형")) {								
				figType = 1;
			} else if (e.getActionCommand().equals("타원")) {				
				figType = 2;
			} else if (e.getActionCommand().equals("선")) {				
				figType = 3;
			}
		}
	}

	class myColorActionListener implements KActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingMode = 1;
			if (e.getActionCommand().equals("검정")) {
				colorType = 1;
			} else if (e.getActionCommand().equals("빨강")) {
				colorType = 2;
			} else if (e.getActionCommand().equals("파랑")) {
				colorType = 3;
			}
		}
	}

	class myGroupActionListener implements KActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (groupMode == 0) {
				groupMode = 1;
			} else {
				groupMode = 0;
			}
		}
	}

	class myPaintActionListener implements KActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (paintMode == 0) {
				System.out.println("paintMode 1로 바뀌었습니다.");
				paintMode = 1;
			} else {
				System.out.println("paintMode 0으로 바뀌었습니다.");
				paintMode = 0;
			}
		}				
	}
	
	class myDupActionListener implements KActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (dupMode == 0) {
				System.out.println("dupMode 1로 바뀌었습니다.");
				dupMode = 1;
			} else {
				System.out.println("dupMode 0으로 바뀌었습니다.");
				dupMode = 0;
			}
		}
		
	}
	
	class myDelActionListener implements KActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (delMode == 0) {
				System.out.println("delMode 1로 바뀌었습니다.");
				delMode = 1;
			} else {
				System.out.println("delMode 0으로 바뀌었습니다.");
				delMode = 0;
			}
		}
		
	}
	
	public PainterKFrame() {
		super();
	}

	public void initGUI() {
		menuBar = new KMenuBar();
		menuBar.setBounds(0, 30, 800, 25);
		setKMenuBar(menuBar);

		toolBar = new KToolBar();
		toolBar.setBounds(0, 55, 800, 25);
		setKToolBar(toolBar);

		setKContentPane(new KPanel());

		fileMenu = new KMenu("파일");
		shapeMenu = new KMenu("도형");
		colorMenu = new KMenu("컬러");
		fileMenu.setBounds(0, 30, 80, 25);
		shapeMenu.setBounds(80, 30, 80, 25);
		colorMenu.setBounds(160, 30, 80, 25);
		menuBar.addMenu(fileMenu);
		menuBar.addMenu(shapeMenu);
		menuBar.addMenu(colorMenu);

		miOpen = new KMenuItem("Open");
		miSave = new KMenuItem("Save");
		miRect = new KMenuItem("사각형");
		miOval = new KMenuItem("타원");
		miLine = new KMenuItem("선");
		miBlack = new KMenuItem("검정");
		miRed = new KMenuItem("빨강");
		miBlue = new KMenuItem("파랑");
		miOpen.setBounds(15, 55, 80, 25);
		miSave.setBounds(15, 80, 80, 25);
		miRect.setBounds(95, 55, 80, 25);
		miOval.setBounds(95, 80, 80, 25);
		miLine.setBounds(95, 105, 80, 25);
		miBlack.setBounds(175, 55, 80, 25);
		miRed.setBounds(175, 80, 80, 25);
		miBlue.setBounds(175, 105, 80, 25);

		fileMenu.addMenuItem(miOpen);
		fileMenu.addMenuItem(miSave);
		shapeMenu.addMenuItem(miRect);
		shapeMenu.addMenuItem(miOval);
		shapeMenu.addMenuItem(miLine);
		colorMenu.addMenuItem(miBlack);
		colorMenu.addMenuItem(miRed);
		colorMenu.addMenuItem(miBlue);

		btnPaintMode = new KButton("컬러: 선 / 채우기");		
		btnGrouping = new KButton("그룹화: On / Off");
		btnDup = new KButton("복사");
		btnDel = new KButton("삭제");
		btnPaintMode.setBounds(300, 55, 120, 25);		
		btnGrouping.setBounds(420, 55, 100, 25);
		btnDup.setBounds(520, 55, 50, 25);
		btnDel.setBounds(570, 55, 50, 25);
		toolBar.addBtn(btnPaintMode);		
		toolBar.addBtn(btnGrouping);
		toolBar.addBtn(btnDup);
		toolBar.addBtn(btnDel);

		miRect.addKActionListener(figListener);
		miOval.addKActionListener(figListener);
		miLine.addKActionListener(figListener);
		miBlack.addKActionListener(colorListener);
		miRed.addKActionListener(colorListener);
		miBlue.addKActionListener(colorListener);
		btnPaintMode.addKActionListener(paintListener);
		btnGrouping.addKActionListener(groupListener);
		btnDup.addKActionListener(dupListener);
		btnDel.addKActionListener(delListener);
	}

	@Override
	public void processMouseEvent(MouseEvent e) {

		switch (e.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			System.out.println("PRESSED");
			startX = e.getX();
			startY = e.getY();
			break;
		case MouseEvent.MOUSE_RELEASED:
			System.out.println("RELEASED");
			if (e.getButton() == MouseEvent.BUTTON3) {
				//이동
				System.out.println("우클릭");
				moveFig(startX, startY, e.getX(), e.getY());
				break;
			}
			
			for (KComponent c : compoList) {
				if (c.findChildRecursive(e.getX(), e.getY()) != null) {
					if (c.findChildRecursive(e.getX(), e.getY()).text.equals("도형")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("파일")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("컬러")) {
						drawingMode = 0;
					}
					break;
				}
			}

			if(dupMode == 1) {
				dupFig(e.getX() - startX, e.getY() - startY);
				break;
			}
			
			if(delMode == 1) {
				delFig();
				break;
			}
			
			if (drawingMode == 0) {
				// empty
			} else {
				if (groupMode == 0) {
					if (figType == 1) {
						addFig(new Rect(paintMode, colorType, "", startX, startY, e.getX() - startX, e.getY() - startY));
					} else if (figType == 2) {
						addFig(new Oval(paintMode, colorType, "", startX, startY, e.getX() - startX, e.getY() - startY));
					} else if (figType == 3) {
						addFig(new Line(paintMode, colorType, "", startX, startY, e.getX(), e.getY()));
					}
				} else {
					grouping(startX, startY, e.getX(), e.getY());
				}
			}

			break;
		case MouseEvent.MOUSE_CLICKED:
			for (KComponent c : compoList) {
				if (c.findChildRecursive(e.getX(), e.getY()) != null) {
					if (c.findChildRecursive(e.getX(), e.getY()).text.equals("사각형")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("타원")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("선")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("검정")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("빨강")
							|| c.findChildRecursive(e.getX(), e.getY()).text.equals("파랑")) {
						c.findChildRecursive(e.getX(), e.getY()).processMouseEvent(e);
					} else if (c.findChildRecursive(e.getX(), e.getY()).text.equals("Open")) {
						new Dinae();
					} else if (c.findChildRecursive(e.getX(), e.getY()).text.equals("Save")) {
						Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
						BufferedImage capture = null;
						try {
							capture = new Robot().createScreenCapture(screenRect);
						} catch (AWTException e1) {
							e1.printStackTrace();
						}
						try {
							ImageIO.write(capture, "bmp", new File("capture_" + captureCnt + ".bmp"));
							captureCnt++;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

					c.processMouseEvent(e);
					break;
				}
			}
			break;
		}

	}

	public void addFig(Figure f) {
		figList.add(f);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < figList.size(); i++) {
			figList.get(i).draw(g);
		}
	}

	public void grouping(int startX, int startY, int endX, int endY) {
		ArrayList<Figure> tempFigList = new ArrayList<>();
		int flag = 0;
		int minX = 2147000000, minY = 2147000000, maxX = 0, maxY = 0;
		Figure f;

		for (int i = 0; i < figList.size(); i++) {
			f = figList.get(i);
			if (startX <= f.x && (f.x + f.w) <= endX && startY <= f.y && (f.y + f.h) <= endY) {

				if (f.x < minX) {
					minX = f.x;
				}
				if ((f.x + f.w) > maxX) {
					maxX = f.x + f.w;
				}
				if (f.y < minY) {
					minY = f.y;
				}
				if ((f.y + f.h) > maxY) {
					maxY = f.y + f.h;
				}

				tempFigList.add(f);
				flag = 1;
			}
		}

		for (int i = 0; i < tempFigList.size(); i++) {
			figList.remove(tempFigList.get(i));
		}

		if (flag == 1) {
			GroupFigure g = new GroupFigure(paintMode, colorType, "", minX, minY, maxX - minX, maxY - minY);
			for (int i = 0; i < tempFigList.size(); i++) {
				g.add(tempFigList.get(i));
			}
			addFig(g);
		}
	}

	public void moveFig(int startX, int startY, int endX, int endY) {

		for (int i = figList.size() - 1; i >= 0; i--) {
			Figure f = figList.get(i);
			if (f.x <= startX && startX <= (f.x + f.w) && f.y <= startY && startY <= (f.y + f.h)) {
				if(f.figType == 3) {
					f.x += endX - startX;
					f.y += endY - startY;
					f.w += endX - startX;
					f.h += endY - startY;		
				}else {
					f.x += endX - startX;
					f.y += endY - startY;	
				}
				
				
				f.groupMove(endX - startX, endY - startY);
				repaint();
				break;
			}
		}
	}
	
	public void dupFig(int dx, int dy) {
		for (int i = figList.size() - 1; i >= 0; i--) {
			Figure f = figList.get(i);			
			
			if (f.x <= startX && startX <= (f.x + f.w) && f.y <= startY && startY <= (f.y + f.h)) {
				paintMode = f.paintType;
				colorType = f.color;
				
				if(f.figType == 1) {
					addFig(new Rect(paintMode, colorType, "", f.x + dx, f.y + dy, f.w, f.h));
				}else if(f.figType == 2) {
					addFig(new Oval(paintMode, colorType, "", f.x + dx, f.y + dy, f.w, f.h));
				}else if(f.figType == 3) {
					addFig(new Line(paintMode, colorType, "", f.x + dx, f.y + dy, f.w + dx, f.h + dy));
				}						
				repaint();
				break;
			}					
		}
	}
	
	public void delFig() {
		for (int i = figList.size() - 1; i >= 0; i--) {
			Figure f = figList.get(i);			
			
			if (f.x <= startX && startX <= (f.x + f.w) && f.y <= startY && startY <= (f.y + f.h)) {
				figList.remove(f);							
				repaint();
				break;
			}
		}
	}
}

class Dinae {
	Dinae() {
		JFrame jFrame = new JFrame("저장한 파일의 경로 출력하기");
		JButton jButton = new JButton("file");
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
		jPanel.add(jButton);
		JTextField jTextField = new JTextField();
		jPanel.add(jTextField);
		jFrame.add(jPanel);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialogOpen = new FileDialog(jFrame, "파일 열기", FileDialog.LOAD);
				fileDialogOpen.setVisible(true);
				String filePath = fileDialogOpen.getDirectory() + fileDialogOpen.getFile();
				jTextField.setText(filePath);
			}
		});
		jFrame.setSize(600, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
}

public class Main {
	public static void main(String[] args) {
		KAdapterFrame adapterFrame = new KAdapterFrame();
		adapterFrame.setSize(800, 600);
		adapterFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

		PainterKFrame myFrame = new PainterKFrame();
		adapterFrame.setKFrame(myFrame);
		myFrame.initGUI();
		adapterFrame.setVisible(true);
	}

}
