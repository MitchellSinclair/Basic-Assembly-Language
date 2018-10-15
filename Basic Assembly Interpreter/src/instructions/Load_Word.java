package instructions;

import execution_environment.ExecEnv;

public class Load_Word implements Instructions {

	public Load_Word() {
		
	}
	
	@Override
	public void execute(ExecEnv env, String args) {
		if(args.charAt(0)=='$') {
			if(args.substring(args.indexOf('(')+1, args.length()-1).equals("$sp")) {
				int offset =  env.getRegister("$sp") + new Integer(args.substring(args.indexOf(' ')+1, args.indexOf('(')));
				env.setRegister(args.substring(0, 3), env.getStack(offset));
			}
			else {
				env.setRegister(args.substring(0, 3), env.getMem(new Integer(args.substring(4, args.indexOf('('))) + env.getRegister(args.substring(args.indexOf('(')+1, args.length()-1))));
			}
		}
		else {
			throw new IllegalArgumentException("number or type of arguments is invalid");
		}
	}

}
