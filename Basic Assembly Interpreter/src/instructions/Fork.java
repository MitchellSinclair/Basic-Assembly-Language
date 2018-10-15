package instructions;

import execution_environment.ExecEnv;
import file_reader.FileReader;

public class Fork implements Instructions {

	public Fork() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		FileReader reader = new FileReader(args);
		reader.run();
	}

}
