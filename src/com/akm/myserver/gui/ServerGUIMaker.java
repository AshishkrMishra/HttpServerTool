package com.akm.myserver.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.akm.myserver.gui.thread.GuiStarterThread;
import com.akm.myserver.server.Main;

public class ServerGUIMaker {

	final static boolean shouldFill = false;
	final static boolean shouldWeightX = true;
	JFrame jFrame;
	JPanel requestHeaderPanel;
	JPanel requestBodypanel;
	JPanel responseHeaderPanel;
	JPanel responseBodyPanel;
	JTextField ipTextField;
	JTextField portTextField;
	JRadioButton attachButton;
	JLabel attachmentLabel;
	JLabel requestLabel;
	JLabel responseLabel;
	JLabel requestHeaderLabel;
	JLabel responseHeaderLabel;
	JLabel requestBodyLabel;
	JLabel responseBodyLabel;
	JButton startButton;
	JButton stopButton;
	JScrollPane logScrollPanel;
	JScrollPane requestHeaderScrollPanel;
	JScrollPane requestBodyScrollpanel;
	JScrollPane responseHeaderScrollPanel;
	JScrollPane responseBodyScrollPanel;
	JTextArea requestHeaderTextArea;
	JTextArea requestBodyTextArea;
	JTextArea resposeHeaderTextArea;
	JTextArea responseBodyTextArea;
	JFileChooser responseAttachements;
	JPanel logJPanel;
	JTextArea logTextArea;
	JScrollPane logScrollPane;
	JButton chooseAttachement;
	JFileChooser  fileDialog ;
	
	

	public ServerGUIMaker() {

		prepareGUI();
	}

	public void prepareGUI() {
		jFrame = new JFrame("HTTP/HTTPS Server");
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Preparing NorthSide of Server GUI
		addComponentsToPane(jFrame.getContentPane());
		// Display the window.
		jFrame.pack();
		jFrame.setVisible(true);

	}


	public void addComponentsToPane(Container container) {
		// Setting Layout of Container
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		addComponentTopSide(container, c);
		addComponentinMiddleSide(container,c);
	}
	
	public void addComponentinMiddleSide(Container container,
			GridBagConstraints c) {
		// Adding RequestLabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		requestLabel = new JLabel("HTTP/HTTPS REQUEST");
		container.add(requestLabel, c);
		// Adding Response Label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		responseLabel = new JLabel("HTTP/HTTPS RESPONSE");
		container.add(responseLabel, c);
		
		// Adding RequestLabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		requestHeaderLabel = new JLabel("REQUEST HEADER");
		container.add(requestHeaderLabel, c);
		
		// Adding Response Label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		responseHeaderLabel = new JLabel("RESPONSE HEADER");
		container.add(responseHeaderLabel, c);
		
		
		// Adding Request Header  Text Field
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy += 3;
		c.ipady = 0;
		c.weighty = 10.0; 
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 4;
		c.gridheight=2;
		requestHeaderPanel= new JPanel();
		requestHeaderPanel.setLayout(new BorderLayout(4, 4));
		requestHeaderTextArea = new JTextArea();
		requestHeaderTextArea.setText("<RequestHeader></RequestHeader>");
		requestHeaderPanel.add(requestHeaderTextArea);
		requestHeaderScrollPanel=new JScrollPane(requestHeaderPanel);
		requestHeaderScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		container.add(requestHeaderScrollPanel, c);
		
		// Adding response Header Text Field 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		//c.gridy = 5;
		c.ipady = 0;
		c.weighty = 10.0; 
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 4;
		c.gridheight=2;
		responseHeaderPanel= new JPanel();
		responseHeaderPanel.setLayout(new BorderLayout(4, 4));
		resposeHeaderTextArea = new JTextArea();
		resposeHeaderTextArea.setText("<ResponseHeader></ResponseHeader>");
		responseHeaderPanel.add(resposeHeaderTextArea);
		responseHeaderScrollPanel=new JScrollPane(responseHeaderPanel);
		responseHeaderScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container.add(responseHeaderScrollPanel, c);
		
		// Adding RequestLabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.ipady = 0;
		c.gridy +=2;
		requestBodyLabel = new JLabel("REQUEST BODY");
		container.add(requestBodyLabel, c);
		
		// Adding Response Label
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.ipady = 0;
		responseBodyLabel = new JLabel("RESPONSE BODY");
		container.add(responseBodyLabel, c);
		
		// Adding Request Body Text Field
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy += 3;
		c.ipady = 30;
		c.weighty = 5.0; 
		c.insets = new Insets(10,0,0,0);
		c.gridwidth = 2;
		requestBodypanel= new JPanel();
		requestBodypanel.setLayout(new BorderLayout(4, 4));
		requestBodyTextArea = new JTextArea();
		requestBodyTextArea.setText("<RequestBody></RequestBody>");
		requestBodypanel.add(requestBodyTextArea);
		requestBodyScrollpanel=new JScrollPane(requestBodypanel);
		requestBodyScrollpanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container.add(requestBodyScrollpanel, c);
		
		// Adding response Body Text Field
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		//c.gridy = 9;
		c.ipady = 30;
		c.weighty = 5.0; 
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 2;
		responseBodyPanel= new JPanel();
		responseBodyPanel.setLayout(new BorderLayout(4, 4));
		responseBodyTextArea = new JTextArea();
		responseBodyTextArea.setText("<ResponseBody></ResponseBody>");
		responseBodyPanel.add(responseBodyTextArea);
		responseBodyScrollPanel=new JScrollPane(responseBodyPanel);
		responseBodyScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container.add(responseBodyScrollPanel, c);
		// Adding Log For Server
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END; 
		c.ipadx=100;
		c.gridx = 0;
		c.gridy +=3;
		c.ipady = 0;
		c.weighty = 0; 
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 0;
		logJPanel= new JPanel();
		logJPanel.setLayout(new BorderLayout(4, 4));
		logTextArea = new JTextArea();
		logTextArea.setText("<LOG></LOG>");
		logJPanel.add(logTextArea);
		logScrollPane=new JScrollPane(logJPanel);
		logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container.add(logScrollPane, c);
		logTextArea.setText(readFileContent());	
	}

	public void addComponentTopSide(final Container container,
			final GridBagConstraints c) {
		if (shouldWeightX) {
			c.weightx = 0.1;
		}
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		ipTextField = new JTextField();
		ipTextField.setText("127.0.0.1");
		ipTextField.setEditable(false);
		container.add(ipTextField, c);
		// Added Port Field
		portTextField = new JTextField();
		portTextField.setText("9000");
		portTextField.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = 0;
		container.add(portTextField, c);

		// Added Attachement RadioButton
		attachButton = new JRadioButton();
		attachButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				// TODO Auto-generated method stub
				if(attachButton.isSelected())
				{
					chooseAttachement.setEnabled(true);
					fileDialog = new JFileChooser();
					chooseAttachement.addActionListener(new ActionListener() {
				         @Override
				         public void actionPerformed(ActionEvent e) {
				            int returnVal = fileDialog.showOpenDialog(jFrame);
				            if (returnVal == JFileChooser.APPROVE_OPTION) {
				               java.io.File file = fileDialog.getSelectedFile();
				               JOptionPane.showMessageDialog(jFrame,"File Selected :" 
						               + file.getName());
				            }
				            else{
				               JOptionPane.showMessageDialog(jFrame,"Open command cancelled by user.");
				            }      
				         }
				      });
					container.add(chooseAttachement);
				}else
				{
					chooseAttachement.setEnabled(false);
				}
			}
		});
		attachButton.setActionCommand("ATTACHMENT-REQ");
		//attachButton.setEnabled(false);
		attachButton.setToolTipText("Select If Attachement required In Response");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 2;
		c.gridy = 0;
		container.add(attachButton, c);
		// Add Label For Attachement
		attachmentLabel = new JLabel();
		attachmentLabel.setText("Attachement");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.01;
		c.gridx = 3;
		c.gridy = 0;
		container.add(attachmentLabel, c);
		// Add ChooseButton 
		chooseAttachement = new JButton("ChooseAttachement");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 4;
		c.gridy = 0;
		container.add(chooseAttachement, c);
		chooseAttachement.setEnabled(false);
		// Add StartButton
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				// TODO Auto-generated method stub
				Main.serverStarter(logTextArea);
				startButton.setEnabled(false);
				
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 5;
		c.gridy = 0;
		container.add(startButton, c);

		// Add StopButton
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				// TODO Auto-generated method stub
				Main.serviceStopper(logTextArea);
				startButton.setEnabled(true);
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 6;
		c.gridy = 0;
		container.add(stopButton, c);

	}
	
	public JFrame getjFrame() {
		return jFrame;
	}

	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public JPanel getRequestHeaderPanel() {
		return requestHeaderPanel;
	}

	public void setRequestHeaderPanel(JPanel requestHeaderPanel) {
		this.requestHeaderPanel = requestHeaderPanel;
	}

	public JPanel getRequestBodypanel() {
		return requestBodypanel;
	}

	public void setRequestBodypanel(JPanel requestBodypanel) {
		this.requestBodypanel = requestBodypanel;
	}

	public JPanel getResponseHeaderPanel() {
		return responseHeaderPanel;
	}

	public void setResponseHeaderPanel(JPanel responseHeaderPanel) {
		this.responseHeaderPanel = responseHeaderPanel;
	}

	public JPanel getResponseBodyPanel() {
		return responseBodyPanel;
	}

	public void setResponseBodyPanel(JPanel responseBodyPanel) {
		this.responseBodyPanel = responseBodyPanel;
	}

	public JTextField getIpTextField() {
		return ipTextField;
	}

	public void setIpTextField(JTextField ipTextField) {
		this.ipTextField = ipTextField;
	}

	public JTextField getPortTextField() {
		return portTextField;
	}

	public void setPortTextField(JTextField portTextField) {
		this.portTextField = portTextField;
	}

	public JRadioButton getAttachButton() {
		return attachButton;
	}

	public void setAttachButton(JRadioButton attachButton) {
		this.attachButton = attachButton;
	}

	public JLabel getAttachmentLabel() {
		return attachmentLabel;
	}

	public void setAttachmentLabel(JLabel attachmentLabel) {
		this.attachmentLabel = attachmentLabel;
	}

	public JLabel getRequestLabel() {
		return requestLabel;
	}

	public void setRequestLabel(JLabel requestLabel) {
		this.requestLabel = requestLabel;
	}

	public JLabel getResponseLabel() {
		return responseLabel;
	}

	public void setResponseLabel(JLabel responseLabel) {
		this.responseLabel = responseLabel;
	}

	public JLabel getRequestHeaderLabel() {
		return requestHeaderLabel;
	}

	public void setRequestHeaderLabel(JLabel requestHeaderLabel) {
		this.requestHeaderLabel = requestHeaderLabel;
	}

	public JLabel getResponseHeaderLabel() {
		return responseHeaderLabel;
	}

	public void setResponseHeaderLabel(JLabel responseHeaderLabel) {
		this.responseHeaderLabel = responseHeaderLabel;
	}

	public JLabel getRequestBodyLabel() {
		return requestBodyLabel;
	}

	public void setRequestBodyLabel(JLabel requestBodyLabel) {
		this.requestBodyLabel = requestBodyLabel;
	}

	public JLabel getResponseBodyLabel() {
		return responseBodyLabel;
	}

	public void setResponseBodyLabel(JLabel responseBodyLabel) {
		this.responseBodyLabel = responseBodyLabel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JScrollPane getLogScrollPanel() {
		return logScrollPanel;
	}

	public void setLogScrollPanel(JScrollPane logScrollPanel) {
		this.logScrollPanel = logScrollPanel;
	}

	public JScrollPane getRequestHeaderScrollPanel() {
		return requestHeaderScrollPanel;
	}

	public void setRequestHeaderScrollPanel(JScrollPane requestHeaderScrollPanel) {
		this.requestHeaderScrollPanel = requestHeaderScrollPanel;
	}

	public JScrollPane getRequestBodyScrollpanel() {
		return requestBodyScrollpanel;
	}

	public void setRequestBodyScrollpanel(JScrollPane requestBodyScrollpanel) {
		this.requestBodyScrollpanel = requestBodyScrollpanel;
	}

	public JScrollPane getResponseHeaderScrollPanel() {
		return responseHeaderScrollPanel;
	}

	public void setResponseHeaderScrollPanel(JScrollPane responseHeaderScrollPanel) {
		this.responseHeaderScrollPanel = responseHeaderScrollPanel;
	}

	public JScrollPane getResponseBodyScrollPanel() {
		return responseBodyScrollPanel;
	}

	public void setResponseBodyScrollPanel(JScrollPane responseBodyScrollPanel) {
		this.responseBodyScrollPanel = responseBodyScrollPanel;
	}

	public JTextArea getRequestHeaderTextArea() {
		return requestHeaderTextArea;
	}

	public void setRequestHeaderTextArea(JTextArea requestHeaderTextArea) {
		this.requestHeaderTextArea = requestHeaderTextArea;
	}

	public JTextArea getRequestBodyTextArea() {
		return requestBodyTextArea;
	}

	public void setRequestBodyTextArea(JTextArea requestBodyTextArea) {
		this.requestBodyTextArea = requestBodyTextArea;
	}

	public JTextArea getResposeHeaderTextArea() {
		return resposeHeaderTextArea;
	}

	public void setResposeHeaderTextArea(JTextArea resposeHeaderTextArea) {
		this.resposeHeaderTextArea = resposeHeaderTextArea;
	}

	public JTextArea getResponseBodyTextArea() {
		return responseBodyTextArea;
	}

	public void setResponseBodyTextArea(JTextArea responseBodyTextArea) {
		this.responseBodyTextArea = responseBodyTextArea;
	}

	public JFileChooser getResponseAttachements() {
		return responseAttachements;
	}

	public void setResponseAttachements(JFileChooser responseAttachements) {
		this.responseAttachements = responseAttachements;
	}

	public JPanel getLogJPanel() {
		return logJPanel;
	}

	public void setLogJPanel(JPanel logJPanel) {
		this.logJPanel = logJPanel;
	}

	public JTextArea getLogTextArea() {
		return logTextArea;
	}

	public void setLogTextArea(JTextArea logTextArea) {
		this.logTextArea = logTextArea;
	}

	public JScrollPane getLogScrollPane() {
		return logScrollPane;
	}

	public void setLogScrollPane(JScrollPane logScrollPane) {
		this.logScrollPane = logScrollPane;
	}

	public JButton getChooseAttachement() {
		return chooseAttachement;
	}

	public void setChooseAttachement(JButton chooseAttachement) {
		this.chooseAttachement = chooseAttachement;
	}

	public JFileChooser getFileDialog() {
		return fileDialog;
	}

	public void setFileDialog(JFileChooser fileDialog) {
		this.fileDialog = fileDialog;
	}

	public static boolean isShouldfill() {
		return shouldFill;
	}

	public static boolean isShouldweightx() {
		return shouldWeightX;
	}

	public String readFileContent()
	{
		String content="";
		
		File file=new File("C:\\Users\\JANVI\\Downloads\\Compressed\\SimpleJavaHttpServer\\src\\com\\happylife\\demo\\Handlers.java");
		if(file.exists() && file.isFile())
		{
			try {
				FileInputStream in= new FileInputStream(file);
				int size=in.available();
				byte [] bytes= new byte[size];
				in.read(bytes);
				content=new String(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return content;
	}

	public static void main(String[] args) {

		try {
			SwingUtilities.invokeAndWait(new GuiStarterThread());
		} catch (Exception e) {

		}
	}

}
