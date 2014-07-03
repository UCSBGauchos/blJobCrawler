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
		System.out.println("=== LinkedIn's OAuth Workflow ===");
	    System.out.println();
	    
	    // Obtain the Request Token
	    System.out.println("Fetching the Request Token...");
	    Token requestToken = service.getRequestToken();
	    System.out.println("Got the Request Token!");
	    System.out.println();
	    
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(service.getAuthorizationUrl(requestToken));
	    System.out.println("And paste the verifier here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
	    
	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();
	    
	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getBody());
	}
}
