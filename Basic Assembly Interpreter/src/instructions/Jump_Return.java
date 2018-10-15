package instructions;

import execution_environment.ExecEnv;

public class Jump_Return implements Instructions {

	public Jump_Return() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(!args.equals("$ra")) {
			throw new IllegalArgumentException("Tried to pass a value that is not the return address");
		}
		env.setCP(env.getRegister("$ra"));
	}

}
