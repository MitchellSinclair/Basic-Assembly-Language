package execution_environment;

import java.util.HashMap;

public class ExecEnv {
	
	private HashMap<String, Integer> registers;
	private HashMap<String, Integer> flags;
	private int CodePointer;
	private HashMap<Integer, Integer> memory;
	private HashMap<Integer, Integer> stack;
	
	public ExecEnv() {
		registers = new HashMap<String, Integer>();
		registers.put("$zero", 0);
		flags = new HashMap<String, Integer>();
		CodePointer = 0;
		memory = new HashMap<Integer, Integer>();
		stack = new HashMap<Integer, Integer>();
		registers.put("$sp", 0);
		}
	
	public void writeMem(int loc, Integer val) {
		if(loc > Math.pow(2, 32) - registers.get("$sp")) {
			throw new IllegalArgumentException("An attempt was made to overwrite the stack");
		}
		memory.put(loc/4, val);
	}
	
	public Integer getMem(int loc) {
		if(memory.get(loc/4) == null) {
			return 0;
		}
		return memory.get(loc/4);
	}
	
	public void writeStack(int loc, Integer val) {
		stack.put(-1*(loc/4), val);
	}
	
	public Integer getStack(int loc) {
		if(stack.get(-1*(loc/4)) == null) {
			return 0;
		}
		return stack.get(-1*(loc/4));
	}
	
	public void setCP(int val) {
		CodePointer = val;
	}
	
	public int getCP() {
		return CodePointer;
	}
	
	public void addFlag(String s, Integer line) {
		flags.put(s, line);
	}
	
	public Integer getFlag(String s) {
		return flags.get(s);
	}
	
	public void setRegister(String s, Integer val) {
		if(s.equals("$zero")) {
			throw new IllegalArgumentException("Attempted to set zero register");
		}
		registers.put(s, val);
	}
	
	public Integer getRegister(String s) {
		if(registers.get(s) == null) {
			return 0;
		}
		return registers.get(s);
	}
}
