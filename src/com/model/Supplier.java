package com.model;
import com.util.ApplicationUtil;

public class Supplier {

	private String supplierId;
	private String supplierName;
	private String contactPerson;
	private String email;
	private long phone;
	public Supplier(String supplierName, String contactPerson, String email, long phone) {
		super();
		this.supplierId = "SUPP"+new ApplicationUtil().generateUniqueId();
		this.supplierName = supplierName;
		this.contactPerson = contactPerson;
		this.email = email;
		this.phone = phone;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "SupplierManagement [supplierId=" + supplierId + ", supplierName=" + supplierName + ", contactPerson="
				+ contactPerson + ", email=" + email + ", phone=" + phone + "]";
	}
	


}
