package br.com.codeshare.util;

public enum PackageUtil {

	MODEL("br.com.codeshare.model"),
	SERVICE("br.com.codeshare.service"),
	DATA("br.com.codeshare.data"),
	UTIL("br.com.codeshare.util"),
	ENUMS("br.com.codeshare.enums"),
	BUILDER("br.com.codeshare.builder"),
	EXCEPTION("br.com.codeshare.exception");
	
	private String packageName;

	private PackageUtil(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
}
