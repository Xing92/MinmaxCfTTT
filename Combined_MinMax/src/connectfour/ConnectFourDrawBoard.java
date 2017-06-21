package connectfour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import resources.ImageContainer;


public class ConnectFourDrawBoard extends JPanel {

	
	public ImageContainer IC = new ImageContainer();
//    private int currentPlayer = 1;
    private ConnectFourBoard board;
    
//    public int getCurrentPlayer(){
//    	return currentPlayer;
//    }
//    public void setCurrentPlayer(int currentPlayer){
//    	this.currentPlayer = currentPlayer;
//    }
    public ConnectFourDrawBoard() {
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        System.out.println("paint");
         
        for(int i=0; i<7; i++){
        	for(int j=0; j<6; j++){
        		if(board.getBoard()[i][j]==0){
        			g2d.drawImage(IC.getBlank(),155+80*i,160+80*j,this);
        		}
        		else if(board.getBoard()[i][j]==1){
        			g2d.drawImage(IC.getCross(),155+80*i,160+80*j,this);
        		}
        		else if(board.getBoard()[i][j]==-1){
        			g2d.drawImage(IC.getCircle(),155+80*i,160+80*j,this);
        		}
        	}
        }
    }
}