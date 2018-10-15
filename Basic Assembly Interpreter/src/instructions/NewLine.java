package instructions;

import execution_environment.ExecEnv;

public class NewLine implements Instructions {

	public NewLine() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		System.out.println();
	}

}
