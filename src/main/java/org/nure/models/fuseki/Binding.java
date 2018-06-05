package org.nure.models.fuseki;

public class Binding {
	
	public Binding() {
		super();
	}
	
	public Binding(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	private String type;
	private String value;
	private String datatype;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
}
