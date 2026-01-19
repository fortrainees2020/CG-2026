package com.cts.model;

public class Employee {
	int did;
	int eid;
	String ename;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Employee(int did, int eid, String ename) {
		super();
		this.did = did;
		this.eid = eid;
		this.ename = ename;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
