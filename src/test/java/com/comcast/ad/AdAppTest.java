package com.comcast.ad;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.comcast.ad.model.AdCampaign;



/**
 * Unit test for simple App.
 */
public class AdAppTest
{
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {

	} 
	
	
	@Test
	public void createAdCampaign() {
		
		String url = "http://localhost:8080/ad";

		
		RestTemplate restTemplate = new RestTemplate();
		AdCampaign ad = new AdCampaign();
		ad.setPartnerId("GM");
		ad.setAdContent("Welcome");
		ad.setDuration(300000);
		
		restTemplate.postForObject(url, ad, AdCampaign.class);
		

    }
	
	@Test
	public void getAdCampaign() {
		
		String url = "http://localhost:8080/ad/GM/";

		RestTemplate restTemplate = new RestTemplate();
		
		AdCampaign ad = restTemplate.getForObject(url, AdCampaign.class);
		Assert.assertNotNull(ad);
    }
	
}
