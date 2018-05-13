package org.nure.models.ontology.patient.operation;

import org.nure.models.ontology.patient.people.MedicalWorker;

public class OperationPersonel {

	public OperationPersonel(String id, MedicalWorker worker, String status) {
		this.id = id;
		this.worker = worker;
		this.status = status;
	}
	
	public String id;
	public MedicalWorker worker; // participated_as
	public String status; // has_status
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MedicalWorker getWorker() {
		return worker;
	}
	public void setWorker(MedicalWorker worker) {
		this.worker = worker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
