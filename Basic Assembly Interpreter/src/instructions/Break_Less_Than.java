package instructions;

import execution_environment.ExecEnv;

public class Break_Less_Than implements Instructions {
	public Break_Less_Than() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(args.indexOf("$zero") > 0) {
			if(env.getRegister(args.substring(0, 3)).intValue() < env.getRegister(args.substring(4, 9)).intValue()) {
				env.setCP(env.getFlag(args.substring(args.lastIndexOf(' ')+1))-1);
			}
		}
		else {
			if(env.getRegister(args.substring(0, 3)).intValue() < env.getRegister(args.substring(4, 7)).intValue()) {
				env.setCP(env.getFlag(args.substring(args.lastIndexOf(' ' )+1))-1);
			}
		}
	}
}
