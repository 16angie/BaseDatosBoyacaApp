package boyacaapp.uptc.edu.co.dto;

public class Transaction {
	
	
	private String id ;
	private  int amount_in_cents;
	private  String reference;
	private  String customer_email ;
	private  String currency;
	private  String payment_method_type;
	private  String redirect_url;
	private  String status;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount_in_cents() {
		return amount_in_cents;
	}
	public void setAmount_in_cents(int amount_in_cents) {
		this.amount_in_cents = amount_in_cents;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public String getRedirect_url() {
		return redirect_url;
	}
	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
