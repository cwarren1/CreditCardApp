package com.CreditApp.demo.controller;



import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author cawarr
 *
 */
@Controller
public class CreditAppController {

	/**
	 * @param freeze
	 * @param acctId
	 * @param model
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/FreezeAccount", method = RequestMethod.POST)
	public ResponseEntity<Object> freezeAccount(@RequestParam(value = "freeze") String freeze, @RequestParam(value = "acctId") String acctId, Model model) {
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("API-Key", "C5F5A63C-E604-47AA-A7CC-B01F95FFBF09");
		String body = "";

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = rest.exchange(
				"https://anypoint.mulesoft.com/mocking/api/v1/links/2107a7ca-f0f9-4894-93f3-a6f18e9c9f63/cardcontrols/onoff/" + acctId, HttpMethod.POST,
				requestEntity, String.class);
		HttpStatus status = responseEntity.getStatusCode();
		String response = responseEntity.getBody();
		System.out.println("Response status: " + status);
		System.out.println(response);
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	/**
	 * @param reason
	 * @param acctId
	 * @param comments
	 * @return
	 */
	@RequestMapping(value = "/ReplaceCard", method = RequestMethod.POST)
	public ResponseEntity<Object> replaceCard(@RequestParam(value = "reason") String reason, @RequestParam(value = "acctId") String acctId,
			@RequestParam(value = "comments") String comments) {
	
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("API-Key", "C5F5A63C-E604-47AA-A7CC-B01F95FFBF09");
			headers.setContentType(MediaType.APPLICATION_JSON);
		
			String body = "";
			JSONObject json = new JSONObject();
			try{			
			json.put("cardId", acctId);
			json.put("cardStatus", reason);
			json.put("comment", comments);
			body = json.toString();
			}catch(Exception e){
				//log errors
			}			
			HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
			ResponseEntity<String> responseEntity = rest.exchange("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/1a662d35-8008-4343-b811-226e2284646b/appdeveloperinterview/1.0.0/m/cardcontrols/reportcardissue", HttpMethod.POST, requestEntity, String.class);
			HttpStatus status = responseEntity.getStatusCode();
			String response = responseEntity.getBody();
			System.out.println("Response status: " + status);
			System.out.println(response);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
