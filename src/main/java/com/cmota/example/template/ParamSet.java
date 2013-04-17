package com.cmota.example.template;

import java.util.EnumSet;

public class ParamSet {

	private String field;
	private int lower;
	private int upper;
	
	private EnumSet<ItemCode> codeSet;

	public ParamSet( String f, int l, int u, EnumSet<ItemCode> cs ){
		this.field = f;
		this.lower = l;
		this.upper = u;
		this.codeSet = cs;
	}

	public String getField() {
		return field;
	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

	public String getCodes() {
		StringBuilder sb = new StringBuilder();
		String conn = "";
		for (ItemCode ic : codeSet) {
			sb.append(conn).append(" == ItemCode.").append(ic);
			conn = " ||";
		}
		return sb.toString();
	}
}
