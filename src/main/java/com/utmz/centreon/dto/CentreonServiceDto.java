package com.utmz.centreon.dto;

public class CentreonServiceDto {

    private int host_id;
    private String description;
    private int service_id;
    private int state;
    private String output;
    private Long last_check;
    private Long last_state_change;
	public int getHost_id() {
		return host_id;
	}
	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Long getLast_check() {
		return last_check;
	}
	public void setLast_check(Long last_check) {
		this.last_check = last_check;
	}
	public Long getLast_state_change() {
		return last_state_change;
	}
	public void setLast_state_change(Long last_state_change) {
		this.last_state_change = last_state_change;
	}
	public CentreonServiceDto(int host_id, String description, int service_id, int state, String output,
			Long last_check, Long last_state_change) {
		super();
		this.host_id = host_id;
		this.description = description;
		this.service_id = service_id;
		this.state = state;
		this.output = output;
		this.last_check = last_check;
		this.last_state_change = last_state_change;
	}

	
}
