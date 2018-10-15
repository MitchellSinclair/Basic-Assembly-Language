package instructions;

import execution_environment.ExecEnv;

public class Jump_Function implements Instructions {

	public Jump_Function() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(args.charAt(0) == '$') {
			env.setRegister("$ra", env.getCP());
			env.setCP(env.getRegister(args)-2);
		}
		else {
			env.setRegister("$ra", env.getCP());
			env.setCP(new Integer(args)-2);
		}
	}

}
