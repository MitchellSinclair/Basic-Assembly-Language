package instructions;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import execution_environment.ExecEnv;

public class Flush_To_File implements Instructions {

	public Flush_To_File() {
		
	}
	
	@SuppressWarnings("resource")
	@Override
	public void execute(ExecEnv env, String args) {
		String file = args.substring(0, args.indexOf(' '));
		int begin = new Integer(args.substring(args.indexOf(' ')+1, args.substring(args.indexOf(' ')+1).indexOf(' ')));
		int end = new Integer(args.substring(args.indexOf(' ')+1).indexOf(' ')+1);
		if(end > begin) {
			throw new IllegalArgumentException("Ending index of write is larger than beinning");
		}
		DataOutputStream os;
		try {
			os = new DataOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Could not open file specified for write");
		}
		for(int i = begin; i < end; ++i) {
			try {
				os.writeInt(env.getMem(i));
			} catch (IOException e) {
				
				throw new IllegalArgumentException("An error occured while trying to write to this file");
			} 
		}
		try {
			os.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to close the file specified");
		}
	}

}
