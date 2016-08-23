package com.comcast.ad.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.comcast.ad.model.AdCampaign;
import com.comcast.ad.service.AdService;

@Component("adService")
public class AdServiceImpl implements AdService {
	
	/**
	 * partner id and its ad campaings.
	 */
	ConcurrentHashMap<String, List<AdCampaign>> map;
	
	/**
	 * Used as an id for the ad campaign.
	 */
	AtomicInteger atomicInteger;
	
	public AdServiceImpl() {
		map = new ConcurrentHashMap<String, List<AdCampaign>>();
		atomicInteger = new AtomicInteger();
	}
	
	public void createAdCampaign(AdCampaign adCampaign) {
		String partnerId = adCampaign.getPartnerId();
		
		List<AdCampaign> adList = map.get(partnerId);
		if (adList == null) {
			adList = new ArrayList<AdCampaign>();
		}
		adCampaign.setCreatedDate(new Date());
		adCampaign.setAdId(atomicInteger.addAndGet(1));
		adList.add(adCampaign);
		map.put(partnerId, adList);
	}
	
	
	public AdCampaign retrieveAdCampaign(String partnerId) {
		
		AdCampaign adCampaign = null;
		
		List<AdCampaign> adList = map.get(partnerId);
		if (adList != null) {
			for (AdCampaign ad : adList) {
				if (ad.isActive()) {
					adCampaign = ad;
					break;
				}
			}
		}
		
		return adCampaign;
	}
	
	public List<AdCampaign> retrieveAllAdCampaign() {
		
		List<AdCampaign> adCampaignList = null;
		
		Collection<List<AdCampaign>> collection = map.values();
		if (collection != null) {
			for (List<AdCampaign> aList : collection) {
				for (AdCampaign ad : aList) {
					if (ad.isActive()) {
						if (adCampaignList == null) {
							adCampaignList = new ArrayList<AdCampaign>();	
						}
						adCampaignList.add(ad);
					}
				}
			}
		}
		
		return adCampaignList;
	}
	
	public boolean hasActiveCampaign(String partnerId) {
		boolean hasActive = false;
		
		List<AdCampaign> adList = map.get(partnerId);
		if ((adList != null) && !adList.isEmpty()) {
			for (AdCampaign adCampaign : adList) {
				if (adCampaign.isActive()) {
					hasActive = true;
					break;
				}
			}
		}
		return hasActive;
	}
}
