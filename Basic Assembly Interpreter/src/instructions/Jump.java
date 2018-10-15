package instructions;

import execution_environment.ExecEnv;

public class Jump implements Instructions {

	public Jump() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		int loc = env.getFlag(args);
		env.setCP(loc-1);
	}

}
