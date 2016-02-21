package br.com.authentication.business.component.impl;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.authentication.business.component.CriptografiaComponent;
import br.com.authentication.integration.dao.CrudMasterDetailJpaDAO;

/**
 *
 * @author aromano
 */
@Component(value = "criptografiaComponent")
public class CriptografiaComponentImpl implements CriptografiaComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrudMasterDetailJpaDAO.class);

	@Override
	public String convertToHex(String senha){
		MessageDigest algorithm;
		StringBuilder hexString;

		try {
			algorithm = MessageDigest.getInstance("SHA-2");

			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));		 
			hexString = new StringBuilder();

			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}

		} catch (Exception e) {
			LOGGER.error("convertToHex() - Erro ao converter senha");
			return StringUtils.EMPTY;
		}
		return hexString.toString();
	}

}
