package com.zaelab.sapmock.setup;

import com.zaelab.sapmock.constants.SapmockConstants;
import de.hybris.platform.core.initialization.SystemSetup;
import org.springframework.stereotype.Component;

@Component
@SystemSetup(extension = SapmockConstants.EXTENSIONNAME)
public class SapmockSystemSetup {

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData() {
	}
}
