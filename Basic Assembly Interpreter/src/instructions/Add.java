package instructions;

import execution_environment.ExecEnv;

public class Add implements Instructions {

	public Add() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(args.startsWith("$zero")) {
			throw new IllegalArgumentException("An attempt was made to change the $zero register");
		}
		if(args.charAt(0)=='$') {
			if(args.indexOf("$zero") > 0) {
				env.setRegister(args.substring(0, 3), env.getRegister(args.substring(4, 9)) + env.getRegister(args.substring(10))); 
			}
			else {
				env.setRegister(args.substring(0, 3), env.getRegister(args.substring(4, 7)) + env.getRegister(args.substring(8))); 
			}
		}
		else {
			throw new IllegalArgumentException("number or type of arguments is invalid");
		}

	}

}
