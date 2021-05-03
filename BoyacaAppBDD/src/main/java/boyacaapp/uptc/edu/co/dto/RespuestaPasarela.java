package boyacaapp.uptc.edu.co.dto;


public class RespuestaPasarela {
	
	String event;
	Data data;
	String environment;
	Signature ignature;
	String timestamp;
	String  sent_a;
}
class Data{
	 Transaction transaction;
}

class Transaction{
	String id ;
    int amount_in_cents;
    String reference;
    String customer_email ;
    String currency;
    String payment_method_type;
    String redirect_url;
    String status;
}

class Signature{
	String checksum;
}

