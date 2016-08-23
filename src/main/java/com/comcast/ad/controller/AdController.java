package com.comcast.ad.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.ad.exception.AdNotFoundException;
import com.comcast.ad.model.AdCampaign;
import com.comcast.ad.service.AdService;

@RestController
public class AdController {

	@Autowired
    AdService adService;
	
	
	@RequestMapping(value = "/ad/{partnerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdCampaign> getAdCampaign(@PathVariable("partnerId") String partnerId) {
        
        AdCampaign adCampaign = adService.retrieveAdCampaign(partnerId);
        if (adCampaign == null) {
            throw new AdNotFoundException(partnerId);
        }
        return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/adall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdCampaign>> getAllAdCampaigns() {
        
        List<AdCampaign> adCampaignList = adService.retrieveAllAdCampaign();
        if (adCampaignList == null) {
        	throw new AdNotFoundException("ALL");
        }
        return new ResponseEntity<List<AdCampaign>>(adCampaignList, HttpStatus.OK);
    }
	
		
	@RequestMapping(value = "/ad", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespMessage> createUser(@RequestBody AdCampaign adCampaign) {
        
        String partnerId = adCampaign.getPartnerId();
        if ((partnerId != null) && !partnerId.isEmpty()) {
			if (adService.hasActiveCampaign(partnerId)) {
				RespMessage respMessage = new RespMessage(RespMessage.CREATE_FAILED, "Only one active campaign can exist for a given partner");
	            return new ResponseEntity<RespMessage>(respMessage, HttpStatus.CONFLICT);
			} else {
				adService.createAdCampaign(adCampaign);
				RespMessage respMessage = new RespMessage(RespMessage.CREATE_SUCCESS, "The active campaign is created");
				return new ResponseEntity<RespMessage>(respMessage, HttpStatus.CREATED);
			}
		} else {
            RespMessage respMessage = new RespMessage(RespMessage.CREATE_FAILED, "Empty partner id");
            return new ResponseEntity<RespMessage>(respMessage, HttpStatus.CONFLICT);
        }
    }
	
	@ExceptionHandler(AdNotFoundException.class)
	public ResponseEntity<RespMessage> AdNotFound(AdNotFoundException e) {
		String partnerId = e.getPartnerId();
		RespMessage respMessage = new RespMessage(RespMessage.NOT_FOUND, "There is no active ad campaign for " + partnerId);
		return new ResponseEntity<RespMessage>(respMessage, HttpStatus.NOT_FOUND);
	}

 
}
