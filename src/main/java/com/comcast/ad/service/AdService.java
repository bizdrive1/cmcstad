package com.comcast.ad.service;

import java.util.List;

import com.comcast.ad.model.AdCampaign;

public interface AdService {
	
	
	void createAdCampaign(AdCampaign adCampaign);
	
	AdCampaign retrieveAdCampaign(String partnerId);
	
	List<AdCampaign> retrieveAllAdCampaign();
	
	boolean hasActiveCampaign(String partnerId);
}
