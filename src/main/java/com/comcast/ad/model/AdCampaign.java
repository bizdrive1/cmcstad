package com.comcast.ad.model;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdCampaign {

	/**
	 * partner id.
	 */
	private String partnerId;
	
	/**
	 * The id of the ad campaign.
	 */
	@JsonIgnore
	private int adId;
	
	/**
	 * Ad active duration.
	 */
	int duration;

	/**
	 * The ad content
	 */
	private String adContent;
	
	/**
	 * The ad created date.
	 */
	@JsonIgnore
	Date createdDate;

	/**
	 * @return
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return
	 */
	public String getAdContent() {
		return adContent;
	}

	/**
	 * @param adContent
	 */
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	/**
	 * @param createdDate
	 */
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return
	 */
	public int getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 */
	public void setAdId(int adId) {
		this.adId = adId;
	}
	
	@JsonIgnore
	public boolean isActive() {
		boolean isActive = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdDate);
		cal.add(Calendar.SECOND, duration);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		
		if (cal.compareTo(cal1) > 0) {
			isActive = true;
		}
		
		return isActive;
	}
}
