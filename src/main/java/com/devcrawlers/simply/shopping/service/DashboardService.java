package com.devcrawlers.simply.shopping.service;

import org.springframework.stereotype.Service;
import com.devcrawlers.simply.shopping.resources.DashboardResponse;

/**
 * Dashboard Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   05-10-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface DashboardService {

	/**
	 * Gets the dashboard details.
	 *
	 * @return the dashboard details
	 */
	public DashboardResponse getDashboardDetails();
	
}
