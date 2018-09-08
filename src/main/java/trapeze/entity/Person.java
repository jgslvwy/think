package trapeze.entity;

import common.DressTypeEnum;
import common.ShoesTypeEnum;

public class Person {
	String personId;
	String wxId;
	String phoneNo;
	int Weight;
	int Height;
	Boolean sex;
	Dress dress;
	Shoes shoes;
	static class Dress{
		String color;
		DressTypeEnum type;
	}
	static class Shoes{
		String color;
		ShoesTypeEnum type;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getWxId() {
		return wxId;
	}
	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Dress getDress() {
		return dress;
	}
	public void setDress(Dress dress) {
		this.dress = dress;
	}
	public Shoes getShoes() {
		return shoes;
	}
	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}
}
