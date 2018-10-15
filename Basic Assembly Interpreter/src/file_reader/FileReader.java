package file_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import executer.Executer;
import execution_environment.ExecEnv;

public class FileReader extends Thread{

	String fileName;
	
	public FileReader(String filename) {
		fileName = filename;
	}
	
	public void run() {
		File file = new File(fileName);
		if(!file.exists()) {
			System.out.println("ERROR: No such file Exists");
			return;
		}
		if(fileName.length() < 4 || !fileName.substring(fileName.length()-4, fileName.length()).equals(".asm")) {
			System.out.println("ERROR: File not an assembly file");
			return;
		}
		ArrayList<String> code = new ArrayList<String>();
		Scanner scn = null;
		try {
			scn = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ExecEnv env = new ExecEnv();
		
		while(scn.hasNextLine()) {
			String line = scn.nextLine();
			if(line.indexOf(':') > 0) {
				env.addFlag(line.substring(0,line.indexOf(':')), code.size());
				line = line.substring(line.indexOf(':')+2, line.length());
			}
				code.add(line);
		}
		scn.close();
		Executer exc = new Executer();
		while(env.getCP() < code.size()) {
			if(code.get(env.getCP()).length() > 0) {
				if(code.get(env.getCP()).charAt(0) == '#') {
					env.setCP(env.getCP()+1);
					continue;
				}
				if(code.get(env.getCP()).startsWith("Wait")) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(code.get(env.getCP()).startsWith("Notify")) {
					this.notifyAll();
				}
				else {
					try {
						if (code.get(env.getCP()).indexOf('#') > 0) {
							exc.Execute(code.get(env.getCP()).substring(0, code.get(env.getCP()).indexOf('#') - 1),
									env);
						} else {
							exc.Execute(code.get(env.getCP()), env);
						}
					} catch (IllegalArgumentException ia) {
						System.out.println("\nERROR: " + ia.getMessage() + " at Line: " + (env.getCP() + 1));
						return;
					}
				}
			}
			env.setCP(env.getCP()+1);
		}
	}

}
