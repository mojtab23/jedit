/*
 * JFugue Plugin is a plugin for jEdit that provides basic functionality and 
 * access to JFugue.
 * Copyright (C) 2007 Eric Berry
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.townsfolkdesigns.jfugue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.gjt.sp.jedit.BeanShell;
import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.textarea.JEditTextArea;

/**
 * @author elberry
 * 
 */
public class JFuguePlugin extends EditPlugin {

	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 5, 300, TimeUnit.SECONDS,
	      new LinkedBlockingQueue<Runnable>());

	private final String CMD_PATH = "/bsh";

	/**
    * 
    */
	public JFuguePlugin() {
	}

	/*
    * (non-Javadoc)
    * 
    * @see org.gjt.sp.jedit.EditPlugin#start()
    */
	@Override
	public void start() {
		BeanShell.getNameSpace().addCommandPath(CMD_PATH, getClass());
	}

	/*
    * (non-Javadoc)
    * 
    * @see org.gjt.sp.jedit.EditPlugin#stop()
    */
	@Override
	public void stop() {
	}

	public static void playBuffer(View view) {
		JEditTextArea currentTextArea = view.getTextArea();
		String bufferText = currentTextArea.getText();
		if (StringUtils.isNotBlank(bufferText)) {
			playMusicString(bufferText);
		}
	}

	public static void playMusicString(String musicString) {
		if (StringUtils.isNotBlank(musicString)) {
			// run in Thread, so jEdit won't freeze up.
			threadPool.execute(new MusicPlayer(musicString));
		}
	}

	public static void playSelection(View view) {
		JEditTextArea currentTextArea = view.getTextArea();
		String selectedText = currentTextArea.getSelectedText();
		if (StringUtils.isNotBlank(selectedText)) {
			playMusicString(selectedText);
		} else {
			playBuffer(view);
		}
	}
}
