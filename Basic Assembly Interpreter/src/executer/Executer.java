package executer;

import java.util.HashMap;

import execution_environment.ExecEnv;
import instructions.*;

public class Executer {
	HashMap<String, Instructions> instr;
	
	public Executer() {
		instr = new HashMap<String, Instructions>();
		instr.put("POTD", new Output());
		instr.put("Addi", new Addi());
		instr.put("Subi", new Subi());
		instr.put("Add", new Add());
		instr.put("Sub", new Sub());
		instr.put("LW", new Load_Word());
		instr.put("SW", new Store_Word());
		instr.put("J", new Jump());
		instr.put("BEQ", new Break_Equals());
		instr.put("BNE", new Break_Not_Equals());
		instr.put("BGT", new Break_Greater_Than());
		instr.put("BLT", new Break_Less_Than());
		instr.put("jal", new Jump_Function());
		instr.put("jr", new Jump_Return());
		instr.put("Write", new Flush_To_File());
		instr.put("NL", new NewLine());
		instr.put("Run", new Fork());
	}
	
	public void Execute(String instruction, ExecEnv env) {
		if(instruction.equals("NL")) {
			instr.get(instruction).execute(env, "");
			return;
		}
		char[] cur = instruction.toCharArray();
		boolean found = false;
		for(int i = 0; i < cur.length-1; ++i) {
			if(cur[i+1] == ' ') {
				try {
					instr.get(instruction.substring(0, i+1)).execute(env, instruction.substring(i+2));
					found = true;
					break;
				} catch(NullPointerException np) {
					throw new IllegalArgumentException("Code contains a line that is not a valid instruction");
				}
			}
		}
		if(!found) {
			throw new IllegalArgumentException("String passed is not a valid instruction");
		}
	}
}
