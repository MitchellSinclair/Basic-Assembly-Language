package instructions;

import execution_environment.ExecEnv;

public class Output implements Instructions{
	
	public Output() {
		
	}

	@Override
	public void execute(ExecEnv env, String args) {
		if(args.charAt(0)=='$') {
			if(args.length() > 3) {
				throw new IllegalArgumentException();
			}
			System.out.print(env.getRegister(args));
		}
		else {
			System.out.print(args);
		}
	}

}
