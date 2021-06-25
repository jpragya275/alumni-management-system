package com.ui;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

import com.socket.Message_2;
import com.socket.SocketClient;

public class ChatFrame extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username;
    public Thread clientThread;
    public DefaultListModel model;
    
    public ChatFrame() {
        initComponents();
        this.setTitle("Alumni Student Messenger");
        /*try {
			this.setIconImage(ImageIO.read(new File("F:\\pragya\\Chatting\\MessagingApp\\AlumniClients\\com\\ui\\1200px-Jawaharlal_Nehru_University_Logo_vectorized.svg.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
        model.addElement("All");
        userList.setSelectedIndex(0);
        
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message_2("message", username, ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }
    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {
    	
        unameField = new javax.swing.JTextField();
        unameLabel = new javax.swing.JLabel();
        availButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        textAreaScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        listScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        msgLabel = new javax.swing.JLabel();
        msgField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(ChatFrame.DISPOSE_ON_CLOSE);//javax.swing.WindowConstants.EXIT_ON_CLOSE);

        unameField.setText("");
        unameField.setEnabled(true);

        unameLabel.setText("Username :");

        availButton.setText("Available for chat");
        availButton.setEnabled(false);
        availButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availButtonActionPerformed(evt);
            }
        });

        textArea.setColumns(60);
        textArea.setFont(new java.awt.Font("Consolas", 0, 12)); 
        textArea.setRows(15);
        textArea.setEnabled(false);
        textAreaScrollPane.setViewportView(textArea);

        userList.setModel((model = new DefaultListModel()));
        listScrollPane.setViewportView(userList);

        msgLabel.setText("Message : ");

        sendButton.setText("Send Message ");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(unameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unameField))
                                .addGap(18, 18, 18)
                                )
                            )
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(availButton, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textAreaScrollPane)
                        .addGap(18, 18, 18)
                        .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(msgLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgField)
                        .addGap(18, 18, 18)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unameLabel)
                    .addComponent(availButton)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaScrollPane)
                    .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(msgLabel)
                    .addComponent(msgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
        );

        pack();
    }

    public void establishConnection() {
        serverAddr = "localhost"; port = 1000;
        
        if(!serverAddr.isEmpty()) { 
            try{
                client = new SocketClient(this);
                clientThread = new Thread(client);
                clientThread.start();
                client.send(new Message_2("test", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
                textArea.append("[Application > Me] : Server not found\n");
            }
        }
    }

    private void availButtonActionPerformed(java.awt.event.ActionEvent evt) {
        username = unameField.getText();
        /////***********//////
        if(!username.isEmpty()){
            client.send(new Message_2("setConnection", username, "12", "SERVER"));
        }
        else {
        	textArea.append("[Application > Me] : Please enter your username\n");
        }
    }
    
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String msg = msgField.getText();
        String target = userList.getSelectedValue().toString();
        
        if(!msg.isEmpty() && !target.isEmpty()){
            msgField.setText("");
            client.send(new Message_2("message", username, msg, target));
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
        /*ChatFrame cf = new ChatFrame();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cf.setVisible(true);
                cf.establishConnection();
            }
        });*/
    }
    
    public javax.swing.JButton availButton;
    public javax.swing.JButton sendButton;
    private javax.swing.JLabel unameLabel;
    private javax.swing.JLabel msgLabel;
    public javax.swing.JList userList;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea textArea;
    public javax.swing.JTextField unameField;
    public javax.swing.JTextField msgField;
}
