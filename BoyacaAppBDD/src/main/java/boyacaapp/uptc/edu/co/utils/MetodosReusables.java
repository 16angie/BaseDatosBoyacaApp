package boyacaapp.uptc.edu.co.utils;

import java.security.SecureRandom;

public class MetodosReusables {
	public static  String metodorandomimg() {
	    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		 SecureRandom rnd = new SecureRandom();
		   StringBuilder sb = new StringBuilder(5);
		   for(int i = 0; i < 10; i++)
		      sb.append(AB.charAt(rnd.nextInt(AB.length())));
		   return sb.toString();
		
	}
}
