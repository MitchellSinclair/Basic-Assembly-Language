package user_interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import file_reader.FileReader;


public class Basic_Instructional_Language extends Application{
	
	
		/**
	 	* @param args the command line arguments
     	*/
    	public static void main(String[] args) {
    		launch(args);
    	}
	
		private boolean run = false;
		private Text area;
		StackPane root = new StackPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        TextArea text = new TextArea();
        FileReader reader;
        
		@Override
	    public void start(Stage primaryStage) throws FileNotFoundException {
			File file = new File("Out.txt");
			PrintStream out = new PrintStream(file);
			System.setOut(out); 
			
			 primaryStage.getIcons().add(new Image("file:///Users/mitchellsinclair/eclipse-workspace/Basic%20Assemb"
		     + "ly%20Interpreter/src/user_interface/feather.png"));
		     primaryStage.setResizable(false);
			
	        Button btn = new Button();
	        btn.setText("Run Code");
	        btn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	        btn.setTranslateX((screenSize.getWidth()/2)-40);
	        btn.setTranslateY(-screenSize.getHeight()/10);
	        root.getChildren().add(btn);
		      
	        TextArea runFile = new TextArea();
	        runFile.setMaxSize((screenSize.getWidth()/2.2), (screenSize.getHeight()/30));
	        runFile.setTranslateX((screenSize.getWidth()/2)-btn.getMaxWidth()*2);
	        runFile.setTranslateY(-(screenSize.getHeight()/10)-40);
	        root.getChildren().add(runFile);
	        
	        text.setEditable(false);
	        text.setMaxSize(screenSize.width/2, (screenSize.height/2)-150);
	        text.setText("Console Output:");
	        text.setTranslateX(-(screenSize.getWidth()/4));
    		text.setTranslateY(-(screenSize.getHeight()/5)-75);
    		root.getChildren().add(text);
    		
    		TextArea code = new TextArea();
    		code.setMaxHeight(screenSize.getHeight()/2);
    		code.setMaxWidth(screenSize.getWidth()-10);
    		code.setTranslateY(screenSize.getHeight()/5.25);
    		root.getChildren().add(code);
    		
    		Button btn2 = new Button();
	        btn2.setText("Load Code");
	        btn2.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	        btn2.setTranslateX((screenSize.getWidth()/2)-40);
	        btn2.setTranslateY(-screenSize.getHeight()/10-80);
	        root.getChildren().add(btn2);
	        
	        TextArea loadFile = new TextArea();
	        loadFile.setMaxSize((screenSize.getWidth()/2.2), (screenSize.getHeight()/30));
	        loadFile.setTranslateX((screenSize.getWidth()/2)-btn.getMaxWidth()*2);
	        loadFile.setTranslateY(-(screenSize.getHeight()/10)-120);
	        root.getChildren().add(loadFile);
	        
	        Button btn3 = new Button();
	        btn3.setText("Save Code");
	        btn3.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	        btn3.setTranslateX((screenSize.getWidth()/2)-40);
	        btn3.setTranslateY(-screenSize.getHeight()/10-160);
	        root.getChildren().add(btn3);
	        
	        TextArea saveFile = new TextArea();
	        saveFile.setMaxSize((screenSize.getWidth()/2.2), (screenSize.getHeight()/30));
	        saveFile.setTranslateX((screenSize.getWidth()/2)-btn.getMaxWidth()*2);
	        saveFile.setTranslateY(-(screenSize.getHeight()/10)-200);
	        root.getChildren().add(saveFile);
    		
	        btn.setOnAction(event->
	        {
				File file3 = new File("workspace/" + runFile.getText());
				if(runFile.getText().trim().isEmpty()) {
					String s = "Console Output:\n\n" + "ERROR: No file was specified";
					text.setText(s);
					return;
				}
				if (!file3.isDirectory()) {
					reader = new FileReader("workspace/" + runFile.getText());
					reader.run();
					try {
						reader.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String s = "";
					Scanner scn = null;
					try {
						scn = new Scanner(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					while (scn.hasNextLine()) {
						s += scn.nextLine() + "\n";
					}
					if (run) {
						root.getChildren().remove(area);
					}
					s = "Console Output:\n\n" + s;
					text.setText(s);
					PrintWriter wrt = null;
					try {
						wrt = new PrintWriter(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					wrt.write("");
					run = true;

				} else {
					String s = "Console Output:\n\n" + "ERROR: File specified is a directory";
					text.setText(s);
				}
	        }
	        );
	        
	     
	        btn2.setOnAction(event->
	        {
				File file3 = new File("workspace/" + loadFile.getText());
				if(loadFile.getText().trim().isEmpty()) {
					String s = "Console Output:\n\n" + "ERROR: No file was specified";
					text.setText(s);
					code.setText("");
					return;
				}
				if(!file3.exists()) {
					String s = "Console Output:\n\n" + "ERROR: File not found";
					text.setText(s);
					code.setText("");
					return;
				}
				if (!file3.isDirectory()) {
					Scanner scn = null;
					try {
						scn = new Scanner(file3);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					String s = "";
					while(scn.hasNextLine()) {
						String line = scn.nextLine();
						s = s + line + "\n";
					}
					scn.close();
					code.setText(s);
				} else {
					String s = "Console Output:\n\n" + "ERROR: File specified is a directory";
					text.setText(s);
					code.setText("");
				}
	        }
	        );
	        
	        btn3.setOnAction(event->
	        {
				File file3 = new File("workspace/" + saveFile.getText());
				if(saveFile.getText().trim().isEmpty()) {
					String s = "Console Output:\n\n" + "ERROR: No file was specified";
					text.setText(s);
					code.setText("");
					return;
				}
			PrintWriter wrt = null;
			try {
				wrt = new PrintWriter(file3);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Scanner scn3 = new Scanner(code.getText());
			while (scn3.hasNextLine()) {
				String str = scn3.nextLine();
				wrt.write(str);
			}
			scn3.close();
			wrt.close();
				
	        }
	        );
	        
	        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
	        
	        primaryStage.setTitle("Basic Assembly");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}
}
