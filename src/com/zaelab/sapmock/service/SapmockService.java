package com.zaelab.sapmock.service;

import com.zaelab.sapmock.enums.IdocType;

public interface SapmockService {
	void createIdoc(IdocType idocType, String content);
}
