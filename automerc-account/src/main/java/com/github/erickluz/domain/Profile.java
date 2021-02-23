
package com.github.erickluz.domain;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Entity of profiles from users
 * @author erick
 *
 */
public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),
	EMPLOYER(2, "ROLE_EMPLOYER");
		
	public int code;
	public String description;
	public static final ConcurrentHashMap<Integer, Profile> codigoToEnum = new ConcurrentHashMap<>();
	
	static {
		for(final Profile profile : values()) {
			codigoToEnum.put(profile.code, profile);
		}
	}
	
	Profile(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public static Profile fromCode(int code) {
		return codigoToEnum.get(code);
	}
}
