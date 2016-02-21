package br.com.authentication.business.entity;

public enum Status {
	
	ATIVO("A"), INATIVO("I");
	
	private String statusCode;

	private Status(String statusCode){
		this.statusCode = statusCode;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
}
