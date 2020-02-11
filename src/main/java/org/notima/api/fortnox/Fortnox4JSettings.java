package org.notima.api.fortnox;

import java.util.Map;

import org.notima.api.fortnox.entities3.Supplier;
import org.notima.piggyback.FieldRider;
import org.notima.piggyback.FieldRiderKeyValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for storing settings in the comment field of vendors
 * (piggybacking)
 * 
 * Copyright 2020 Notima System Integration AB (Sweden)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Daniel Tamm
 *
 */
public class Fortnox4JSettings {

	private Logger log = LoggerFactory.getLogger(Fortnox4JSettings.class);
	
	private FortnoxClient3 client;

	/**
	 * Create a settings class.
	 * 
	 * @param fc3		The fortnox client to use.
	 */
	public Fortnox4JSettings(FortnoxClient3 fc3) {
		client = fc3;
	}
	
	/**
	 * Get a single settings on the given supplier
	 * 
	 * @param supplierOrgNo		The org number of supplier where settings are stored.
	 * @param settingKey		The key of the setting.
	 * @return					A map of settings.
	 * @throws Exception		If something goes wrong.
	 */
	public String getSettingFromSupplierByOrgNo(
			String supplierOrgNo,
			String settingKey) throws Exception {
		
		Map<String,String> map = getSettingsFromSupplierByOrgNo(supplierOrgNo);
		if (map!=null) {
			return map.get(settingKey);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Get a single settings on the given supplier
	 * 
	 * @param supplierNo		The supplier number where settings are stored.
	 * @param settingKey		The key of the setting.
	 * @return					A map of settings.
	 * @throws Exception		If something goes wrong.
	 */
	public String getSettingFromSupplierNo(
			String supplierNo,
			String settingKey) throws Exception {
		
		Map<String,String> map = getSettingsFromSupplierNo(supplierNo);
		if (map!=null) {
			return map.get(settingKey);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Get a map of settings on the given supplier
	 * 
	 * @param supplierOrgNo		The org number of supplier where settings are stored.
	 * @return					A map of settings. Null if supplier does't exist.
	 * @throws Exception		If something goes wrong.
	 */
	public Map<String, String> getSettingsFromSupplierByOrgNo(
			String supplierOrgNo) throws Exception {
		
		
		Supplier supplier = client.getSupplierByTaxId(supplierOrgNo, true);
		if (supplier==null) {
			log.warn("Supplier with tax id {} doesn't exist.", supplierOrgNo);
			return null;
		}

		String comment = supplier.getComments();
		FieldRider rider = new FieldRider(comment);
		
		return rider.getSettingsMap();
		
	}

	/**
	 * Get a map of settings on the given supplier
	 * 
	 * @param supplierNo		The supplier number of supplier where settings are stored.
	 * @return					A map of settings. Null if supplier does't exist.
	 * @throws Exception		If something goes wrong.
	 */
	public Map<String, String> getSettingsFromSupplierNo(String supplierNo) throws Exception {
		
		Supplier supplier = client.getSupplierBySupplierNo(supplierNo);
		if (supplier==null) {
			log.warn("Supplier with no {} doesn't exist.", supplierNo);
			return null;
		}
		
		FieldRider rider = new FieldRider(supplier.getComments());
		return rider.getSettingsMap();
		
	}

	/**
	 * Writes a setting to supplier
	 * 
	 * @param supplierNo		Supplier no
	 * @param settingKey		Setting key
	 * @param settingValue		Setting value
	 * @return	The supplier if successful. Null if supplier is not found.
	 * @throws Exception 		If something goes wrong
	 */
	public Supplier writeSettingToSupplier(
			String supplierNo, 
			String settingKey, 
			String settingValue) throws Exception {

		
		Supplier supplier = client.getSupplierBySupplierNo(supplierNo);
		if (supplier==null) {
			log.warn("Supplier with no {} doesn't exist.", supplierNo);
			return null;
		}
		
		String comment = supplier.getComments();
		
		// Scan for settings in comments
		FieldRider rider = new FieldRider(comment);
		FieldRiderKeyValuePair kvp = rider.lookupKeyValuePair(settingKey);
		if (kvp==null) {
			// The key doesn't yet exist.
			kvp = new FieldRiderKeyValuePair(settingKey, settingValue);
			rider.addKeyValuePair(kvp);
		} else {
			// Update the value
			kvp.setValue(settingValue);
		}

		// Save the setting
		StringBuffer newContent = rider.updateContent();
		supplier.setComments(newContent.toString());
		
		supplier = client.setSupplier(supplier, false);
		
		return supplier;
	}
	
	
	/**
	 * Writes a setting to supplier
	 * 
	 * @param supplierOrgNo		Supplier's org no
	 * @param settingKey		Setting key
	 * @param settingValue		Setting value
	 * @return	The supplier if successful. Null if supplier is not found.
	 * @throws Exception 		If something goes wrong
	 */
	public Supplier writeSettingToSupplierByOrgNo(
			String supplierOrgNo, 
			String settingKey, 
			String settingValue) throws Exception {

		
		Supplier supplier = client.getSupplierByTaxId(supplierOrgNo, true);
		if (supplier==null) {
			log.warn("Supplier with tax id {} doesn't exist.", supplierOrgNo);
			return null;
		}
		
		String comment = supplier.getComments();
		
		// Scan for settings in comments
		FieldRider rider = new FieldRider(comment);
		FieldRiderKeyValuePair kvp = rider.lookupKeyValuePair(settingKey);
		if (kvp==null) {
			// The key doesn't yet exist.
			kvp = new FieldRiderKeyValuePair(settingKey, settingValue);
			rider.addKeyValuePair(kvp);
		} else {
			// Update the value
			kvp.setValue(settingValue);
		}

		// Save the setting
		StringBuffer newContent = rider.updateContent();
		supplier.setComments(newContent.toString());
		
		supplier = client.setSupplier(supplier, false);
		
		return supplier;
	}
	
}
