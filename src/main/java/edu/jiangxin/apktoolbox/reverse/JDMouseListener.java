package edu.jiangxin.apktoolbox.reverse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.jiangxin.apktoolbox.utils.StreamHandler;
import edu.jiangxin.apktoolbox.utils.Utils;

public class JDMouseListener extends MouseAdapter {

	private static final Logger logger = LogManager.getLogger(JDMouseListener.class);

	@Override
	public void mousePressed(MouseEvent e) {
		super.mouseClicked(e);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("java -jar \"-Duser.language=en\" \"-Dfile.encoding=UTF8\"").append(" \"")
					.append(Utils.getToolsPath()).append(File.separator).append("jd-gui-1.4.0.jar\"");
			String cmd = sb.toString();
			logger.info(cmd);
			Process process = Runtime.getRuntime().exec(cmd);
			new StreamHandler(process.getInputStream(), 0).start();
			new StreamHandler(process.getErrorStream(), 1).start();
		} catch (IOException e1) {
			logger.error("open gd-gui fail", e);
		}
	}
}
