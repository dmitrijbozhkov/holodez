package org.nure.models.ui;

import java.util.List;

import org.nure.models.ontology.JSONMap;

public class ImageModel {

	private JSONMap data;
	private List<StringFile> files;
	
	public JSONMap getData() {
		return data;
	}
	public void setData(JSONMap data) {
		this.data = data;
	}
	public List<StringFile> getFiles() {
		return files;
	}
	public void setFiles(List<StringFile> files) {
		this.files = files;
	}
}
