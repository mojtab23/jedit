package ise.plugin.nav;

import java.awt.event.*;
import java.util.Stack;
import java.util.Vector;

import javax.swing.*;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditBus;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.msg.CaretChanging;
import org.gjt.sp.jedit.textarea.JEditTextArea;

/**
 * NavigatorPlugin for keeping track of where we were and where.
 * 
 * @author Dale Anson
 * @author Alan Ezust
 * 
 * @version $Id$
 */
public class Navigator implements ActionListener
{
	private class DontBother extends Exception
	{
	}

	/** Action command to go to the previous item. */
	public final static String BACK = "back";

	/** Action command to go to the next item. */
	public final static String FORWARD = "forward";

	/** Action command to indicate that it is okay to go back. */
	public final static String CAN_GO_BACK = "canGoBack";

	/** Action command to indicate that it is not okay to go back. */
	public final static String CANNOT_GO_BACK = "cannotGoBack";

	/** Action command to indicate that it is okay to go forward. */
	public final static String CAN_GO_FORWARD = "canGoForward";

	/** Action command to indicate that it is not okay to go forward. */
	public final static String CANNOT_GO_FORWARD = "cannotGoForward";

	private Vector history;
	private int current;
	private boolean jumpBack;
	
	private DefaultButtonModel backButtonModel;

	private DefaultButtonModel forwardButtonModel;

	private Object currentNode = null;

	private int maxStackSize = 512;

	private View view;

	private boolean ignoreUpdates;

	public Navigator(View view)
	{
		this(view, "");
	}

	/**
	 * Constructor for Navigator
	 * 
	 * @param vw
	 * @param position
	 */
	public Navigator(View vw, String position)
	{
		this.view = vw;

		backButtonModel = new DefaultButtonModel();
		forwardButtonModel = new DefaultButtonModel();
		ignoreUpdates = false;

		backButtonModel.setActionCommand(Navigator.BACK);
		forwardButtonModel.setActionCommand(Navigator.FORWARD);
		backButtonModel.addActionListener(this);
		forwardButtonModel.addActionListener(this);

		// set up the history stacks
		history = new Vector();
		clearStacks();
		update();

		/*
		 * add a mouse listener to the view. Each mouse click on a text
		 * area in the view is stored
		 */

		view.getTextArea().getPainter().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ce)
			{
				update();
			}
		});

	}

	public ButtonModel getBackModel()
	{
		return backButtonModel;
	}

	NavPosition currentPosition() throws DontBother
	{
		Buffer b = view.getBuffer();
		JEditTextArea ta = view.getTextArea();
		if (ta == null)
			throw new DontBother();
		int cp = ta.getCaretPosition();
		if ((cp == 0) && b.getName().startsWith("Untitled"))
			throw new DontBother();
		NavPosition retval = new NavPosition(b, cp);
		return retval;
	}

	public void update()
	{

		if (ignoreUpdates)
		{
			return;
		}
		try
		{
			update(currentPosition());
		}
		catch (DontBother db)
		{
		}
	}

	public ButtonModel getForwardModel()
	{
		return forwardButtonModel;
	}

	/**
	 * Updates the stacks and button state based on the given node. Pushes
	 * the node on to the "back" history, clears the "forward" history.
	 * 
	 * @param node
	 *                an instance of NavPosition.
	 */
	private void update(NavPosition node)
	{
		if (currentNode != null && ! node.toString().equals(currentNode.toString()))
		{
			history.set(current, node);
			current++;
			while (history.size() > current)
				history.remove(history.size() - 1);
			history.add(null);
		}
		currentNode = node;
		setButtonState();
	}

	/**
	 * The action handler for this class. Actions can be invoked by calling
	 * this method and passing an ActionEvent with one of the action
	 * commands defined in this class (BACK, FORWARD, etc).
	 * 
	 * @param ae
	 *                the action event to kick a response.
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals(BACK))
		{

			goBack();

		}
		else if (ae.getActionCommand().equals(FORWARD))
		{
			
			goForward();
		}
		else if (ae.getActionCommand().equals(CAN_GO_BACK))
		{
			backButtonModel.setEnabled(true);
		}
		else if (ae.getActionCommand().equals(CANNOT_GO_BACK))
		{
			backButtonModel.setEnabled(false);
		}
		else if (ae.getActionCommand().equals(CAN_GO_FORWARD))
		{
			forwardButtonModel.setEnabled(true);
		}
		else if (ae.getActionCommand().equals(CANNOT_GO_FORWARD))
		{
			forwardButtonModel.setEnabled(false);
		}
	}

	/**
	 * Removes an invalid node from the navigation history.
	 * 
	 * @param node
	 *                an invalid node
	 */
	public void remove(Object node)
	{
		if (history.remove(node))
			current--;
	}

	public void setMaxHistorySize(int size)
	{
		maxStackSize = size;
	}

	public int getMaxHistorySize()
	{
		return maxStackSize;
	}

	public void clearStacks()
	{
		history.clear();
		history.add(null);
		current = 0;
		setButtonState();
	}

	/** Sets the state of the navigation buttons. */
	private void setButtonState()
	{
		backButtonModel.setEnabled(current > 0);
		forwardButtonModel.setEnabled(current < history.size() - 1);
	}

	/**
	 * Sets the position of the cursor to the given NavPosition.
	 * 
	 * @param o
	 *                The new NavPosition value
	 */
	public void setPosition(Object o)
	{
		NavPosition np = (NavPosition) o;
		Buffer buffer = np.buffer;
		// JEditBuffer buffer = np.buffer; // for jEdit 4.3
		int caret = np.caret;

		if (buffer.equals(view.getBuffer()))
		{
			// nav in current buffer, just set cursor position
			view.getTextArea().setCaretPosition(caret, true);
			return;
		}

		// Stop listening to EditBus events while we are changing
		// buffers
		ignoreUpdates = true;

		// check if buffer is open
		Buffer[] buffers = jEdit.getBuffers();
		for (int i = 0; i < buffers.length; i++)
		{
			if (buffers[i].equals(buffer))
			{
				// found it
				view.goToBuffer(buffer);
				EditBus.send(new CaretChanging(view.getTextArea()));
				view.getTextArea().setCaretPosition(caret, true);
				ignoreUpdates = false;
				return;
			}
		}

		// buffer isn't open
		String path = buffer.getPath();
		buffer = jEdit.openFile(view, path);

		// Now we can listen to events again
		ignoreUpdates = false;


		if (buffer == null)
		{
			remove(np);
			return; // nowhere to go, maybe the file got deleted?
		}

		if (caret >= buffer.getLength())
		{
			caret = buffer.getLength() - 1;
		}
		if (caret < 0)
		{
			caret = 0;
		}
		try
		{
			view.getTextArea().setCaretPosition(caret, true);
		}
		catch (NullPointerException npe)
		{
			// sometimes Buffer.markTokens throws a NPE here, catch
			// it
			// and silently ignore it.
		}
		view.getTextArea().requestFocus();
	}

	synchronized public void backList()
	{
		if (current < 1)
		{
			JOptionPane.showMessageDialog(view, "No backward items", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Vector list = new Vector();
		for (int i = current - 1; i >= 0; i--)
			list.add(history.get(i));
		jumpBack = true;
		new NavHistoryPopup(view, this, list, false);
	}
	
	synchronized public void jump(int index)
	{
		if (jumpBack)
			current -= index + 1;
		else
			current += index + 1;
		currentNode = history.get(current);
		setPosition(currentNode);
		setButtonState();
	}
	
	/** Moves to the previous item in the "back" history. */
	synchronized public void goBack()
	{
		if (current > 0)
		{
			try
			{
				history.set(current, currentPosition());
			}
			catch (DontBother db)
			{
			}

			current--;
			currentNode = history.get(current);
			setPosition(currentNode);
			setButtonState();
		}
		

	}

	synchronized public void forwardList()
	{
		if (current > history.size() - 2)
		{
			JOptionPane.showMessageDialog(view, "No forward items", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Vector list = new Vector();
		for (int i = current + 1; i < history.size(); i++)
			list.add(history.get(i));
		jumpBack = false;
		new NavHistoryPopup(view, this, list, false);
	}
	
	/** Moves to the next item in the "forward" history. */
	synchronized public void goForward()
	{
		if (current < history.size() - 1)
		{
			try
			{
				currentNode = currentPosition();
				history.set(current, currentNode);
			}
			catch (DontBother db)
			{
			}

			current++;
			currentNode = history.get(current);
			setPosition(currentNode);
			setButtonState();
		}
		
	}
}
