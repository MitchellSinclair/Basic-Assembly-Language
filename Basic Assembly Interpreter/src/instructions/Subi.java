package instructions;

import execution_environment.ExecEnv;

public class Subi implements Instructions {
	public Subi() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(args.charAt(0)=='$') {
			if(args.indexOf("$zero") > 0) {
				env.setRegister(args.substring(0, 3), env.getRegister(args.substring(4, 9)) - new Integer(args.substring(10))); 
			}
			else {
				env.setRegister(args.substring(0, 3), env.getRegister(args.substring(4, 7)) - new Integer(args.substring(8))); 
			}
		}
		else {
			throw new IllegalArgumentException("number or type of arguments is invalid");
		}
		
	}
}
