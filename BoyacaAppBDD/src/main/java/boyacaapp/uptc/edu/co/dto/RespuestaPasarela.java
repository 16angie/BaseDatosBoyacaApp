package boyacaapp.uptc.edu.co.dto;

public class RespuestaPasarela {
	
	private String event;
	private Data data;
	private String environment;
	private Signature signature;
	private String timestamp;
	private String  sent_at;
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Signature getSignature() {
		return signature;
	}
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSent_at() {
		return sent_at;
	}
	public void setSent_at(String sent_at) {
		this.sent_at = sent_at;
	}
}


