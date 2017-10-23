package com.zaelab.sapmock.service.impl;

import com.zaelab.sapmock.enums.IdocType;
import com.zaelab.sapmock.model.IdocModel;
import com.zaelab.sapmock.service.SapmockService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public class DefaultSapmockService implements SapmockService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultSapmockService.class);

	private ModelService modelService;

	@Override
	@Transactional
	public void createIdoc(IdocType idocType, String content) {
		LOG.info("Saving new [{}] idoc", idocType);
		IdocModel model = modelService.create(IdocModel.class);
		model.setContent(content);
		model.setType(idocType);
		model.setCreatedTime(new Date());
		modelService.save(model);
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
