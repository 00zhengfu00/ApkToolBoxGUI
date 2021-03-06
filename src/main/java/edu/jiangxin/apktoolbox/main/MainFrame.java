package edu.jiangxin.apktoolbox.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import edu.jiangxin.apktoolbox.Version;
import edu.jiangxin.apktoolbox.help.AboutMouseListener;
import edu.jiangxin.apktoolbox.help.CheckUpdateMouseListener;
import edu.jiangxin.apktoolbox.help.ContributeMouseListener;
import edu.jiangxin.apktoolbox.i18n.I18NAddMouseListener;
import edu.jiangxin.apktoolbox.monkey.MonkeyMouseListener;
import edu.jiangxin.apktoolbox.reverse.AXMLPrinterMouseListener;
import edu.jiangxin.apktoolbox.reverse.ApkSignerMouseListener;
import edu.jiangxin.apktoolbox.reverse.ApktoolDecodeMouseListener;
import edu.jiangxin.apktoolbox.reverse.ApktoolRebuildMouseListener;
import edu.jiangxin.apktoolbox.reverse.JADXMouseListener;
import edu.jiangxin.apktoolbox.reverse.JDMouseListener;
import edu.jiangxin.apktoolbox.screenshot.ScreenshotMouseListener;
import edu.jiangxin.apktoolbox.swing.extend.JEasyFrame;
import edu.jiangxin.apktoolbox.text.EncodeConvertMouseListener;
import edu.jiangxin.apktoolbox.text.OSConvertMouseListener;
import edu.jiangxin.apktoolbox.utils.Utils;

public class MainFrame extends JEasyFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private ResourceBundle bundle = ResourceBundle.getBundle("apktoolbox");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());*/
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle(MessageFormat.format(bundle.getString("main.title"), Version.VERSION));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		Utils.setJFrameCenterInScreen(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu reverseMenu = new JMenu("Reverse");
		menuBar.add(reverseMenu);

		JMenuItem apktoolDecodeMenuItem = new JMenuItem("Apktool Decode");
		apktoolDecodeMenuItem.addMouseListener(new ApktoolDecodeMouseListener());
		reverseMenu.add(apktoolDecodeMenuItem);

		JMenuItem apktoolRebuildMenuItem = new JMenuItem("Apktool Rebuild");
		apktoolRebuildMenuItem.addMouseListener(new ApktoolRebuildMouseListener());
		reverseMenu.add(apktoolRebuildMenuItem);

		JMenuItem apkSignMenuItem = new JMenuItem("ApkSigner");
		apkSignMenuItem.addMouseListener(new ApkSignerMouseListener());
		reverseMenu.add(apkSignMenuItem);

		JMenuItem jDMenuItem = new JMenuItem("JD-GUI");
		jDMenuItem.addMouseListener(new JDMouseListener());
		reverseMenu.add(jDMenuItem);

		JMenuItem jADXMenuItem = new JMenuItem("JADX-GUI");
		jADXMenuItem.addMouseListener(new JADXMouseListener());
		reverseMenu.add(jADXMenuItem);

		JMenuItem aXMLPrinter = new JMenuItem("AXMLPrinter");
		aXMLPrinter.addMouseListener(new AXMLPrinterMouseListener());
		reverseMenu.add(aXMLPrinter);

		JMenu screenshotMenu = new JMenu("Screnshot");
		menuBar.add(screenshotMenu);

		JMenuItem screenShotMenuItem = new JMenuItem("Screnshot");
		screenShotMenuItem.addMouseListener(new ScreenshotMouseListener());
		screenshotMenu.add(screenShotMenuItem);

		JMenu testMenu = new JMenu("Test");
		menuBar.add(testMenu);

		JMenuItem monkeyMenuItem = new JMenuItem("Monkey Test");
		monkeyMenuItem.addMouseListener(new MonkeyMouseListener());
		testMenu.add(monkeyMenuItem);
		
		JMenu textMenu = new JMenu("Text");
		menuBar.add(textMenu);

		JMenuItem osConvertMenuItem = new JMenuItem("OS Convert");
		osConvertMenuItem.addMouseListener(new OSConvertMouseListener());
		textMenu.add(osConvertMenuItem);
		
		JMenuItem encodeConvertMenuItem = new JMenuItem("Encode Convert");
		encodeConvertMenuItem.addMouseListener(new EncodeConvertMouseListener());
		textMenu.add(encodeConvertMenuItem);
		
		JMenu i18nMenu = new JMenu("I18N");
		menuBar.add(i18nMenu);

		JMenuItem i18nAddMenuItem = new JMenuItem("Add/Replace");
		i18nAddMenuItem.addMouseListener(new I18NAddMouseListener());
		i18nMenu.add(i18nAddMenuItem);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem checkUpdateMenuItem = new JMenuItem("Check for Updates");
		checkUpdateMenuItem.addMouseListener(new CheckUpdateMouseListener(this));
		helpMenu.add(checkUpdateMenuItem);

		JMenuItem contributeMenuItem = new JMenuItem("Contribute");
		contributeMenuItem.addMouseListener(new ContributeMouseListener());
		helpMenu.add(contributeMenuItem);

		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addMouseListener(new AboutMouseListener());
		helpMenu.add(aboutMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);

		JTree contentTree = new JTree();
		leftPanel.add(contentTree);

		JPanel centerPanel = new JPanel();
		centerPanel.setMinimumSize(new Dimension(5, 5));
		centerPanel.setSize(new Dimension(60, 80));
		centerPanel.setAlignmentY(0.3f);
		centerPanel.setAlignmentX(0.3f);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JList contentList = new JList();
		contentList.setModel(new AbstractListModel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "1", "4", "5", "6" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		contentList.setSelectedIndex(0);
		centerPanel.add(contentList);

		JPanel statusPanel = new JPanel();
		contentPane.add(statusPanel, BorderLayout.SOUTH);

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(searchPanel, BorderLayout.NORTH);

		searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(6, 20));
		searchTextField.setToolTipText("");
		searchTextField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
	}

}
