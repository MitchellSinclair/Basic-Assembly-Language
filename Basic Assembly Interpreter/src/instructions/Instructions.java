package instructions;

import execution_environment.ExecEnv;

public interface Instructions {
	public void execute(ExecEnv env, String args);
}
