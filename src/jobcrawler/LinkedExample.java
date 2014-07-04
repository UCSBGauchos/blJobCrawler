package jobcrawler;

import java.util.Scanner;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class LinkedExample {
	
	
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~";

	
	public static void main(String [] args){
		OAuthService service = new ServiceBuilder()
		.provider(LinkedInApi.class)
        .apiKey("75u107mbpv94wy")
        .apiSecret("19LAEHqTTZMIl49l")
        .callback("https://github.com/UCSBGauchos/blJobCrawler")
        .build();
		
		Scanner in = new Scanner(System.in);
		System.out.println("QAuthor 2.0 begin!");
	    System.out.println();
	    
	    // Obtain the Request Token
	    System.out.println("Get Request Token");
	    Token requestToken = service.getRequestToken();
	    System.out.println();
	    
	    System.out.println("Authorize Web");
	    System.out.println(service.getAuthorizationUrl(requestToken));
	    System.out.println("Input the authorize code");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
	    
	    // Obtain the Access Token
	    System.out.println("Get the Access Token...");
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token! It is "+accessToken);
	    System.out.println();
	    
	    // Get the resource by using Linked API
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println(response.getBody());
	}
}
