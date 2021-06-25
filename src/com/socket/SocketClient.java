package com.socket;

import com.ui.ChatFrame;

import java.io.*;
import java.net.*;

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public ChatFrame ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    
    public SocketClient(ChatFrame frame) throws IOException{
        ui = frame; 
        this.serverAddr = ui.serverAddr; 
        this.port = ui.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message_2 msg = (Message_2) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(ui.username)){
                        ui.textArea.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        ui.textArea.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                }
                else if(msg.type.equals("test")){
                    ui.availButton.setEnabled(true);
                    ui.unameField.setEnabled(true);
                }
                else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(ui.username)){
                        boolean exists = false;
                        for(int i = 0; i < ui.model.getSize(); i++){
                            if(ui.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ ui.model.addElement(msg.content); }
                    }
                }
                else if(msg.type.equals("setConnection")){
                    if(msg.content.equals("TRUE")){
                        ui.availButton.setEnabled(false);
                        ui.sendButton.setEnabled(true); 
                        ui.textArea.append("[SERVER > Me] : Connection established\n");
                    }
                    else{
                        ui.textArea.append("[SERVER > Me] : Connection Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(ui.username)){
                        ui.textArea.append("["+ msg.sender +" > Me] : Bye\n");
                        ui.sendButton.setEnabled(false);         
                        for(int i = 1; i < ui.model.size(); i++){
                            ui.model.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }
                    else{
                        ui.model.removeElement(msg.content);
                        ui.textArea.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
                    }
                }
                else{
                    ui.textArea.append("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                keepRunning = false;
                ui.textArea.append("[Application > Me] : Connection Failure\n");
                ui.sendButton.setEnabled(false); 
                System.out.println("List Size: " + ui.model.size());
                for(int i = 1; i < ui.model.size(); i++){
                    ui.model.removeElementAt(i);
                    System.out.println(i + "removed");
                }
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
                ui.clientThread.stop();
                
                
            }
        }
    }
    
    public void send(Message_2 msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
	