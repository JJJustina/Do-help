package com.doHelp.model;

import java.io.Serializable;

public class UserRedis implements Serializable {

	private static final long serialVersionUID = -5759384645308535203L;

	private String name;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRedis() {
		super();
	}

	@Override   
//@Override是伪代码，所以是可写可不写的．它表示方法重写，写上会给我们带来好处． 1．可以当注释用,方便阅读．2．告诉阅读你代码的人，这是方法的复写．3．编译器可以给你验证@Override下面的方法名是否是你父类中所有的，如果没有则报错． 
//可是，如果你没写@Override,而且不小心把方法名写错了，这时你的编译器是可以编译通过的，因为编译器以为这个方法是你的子类中自己增加的方法。
//相反，如果你很机智，在知道自己要重写父类的方法，加上了@Override标签后，编译器会检查出重写方法错误，会保证你重写父类方法的正确性	加了@Override后编译器会提示错误.
	public String toString() {
		return "UserRedis [name=" + name + ", password=" + password + "]";
	}

}
