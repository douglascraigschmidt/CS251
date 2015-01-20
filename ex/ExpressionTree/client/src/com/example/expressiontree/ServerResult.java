package com.example.expressiontree;

/**
 * A POJO class that represents the results returned from a server request. This
 * will be parsed from JSON using the Retrofit library, so names, getters, and
 * setters are not used directly in the project, but still matter.
 */
public class ServerResult {

	public String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
