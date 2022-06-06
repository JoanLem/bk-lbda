package com.grandapp.client.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.grandapp.client.model.ClientModel;
import com.grandapp.client.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService implements ClientRepository{
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<ClientModel> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public <S extends ClientModel> S save(S client) {
		log.info("objeto a Guardar "+ client);
		S cliente = client;
				
		if (cliente.getSharedKey() == null || cliente.getSharedKey().isEmpty()) {
			log.info("Gerenear Shared key a base del nombre");
			String nombre = cliente.getName();
			String[] sharedkey = nombre.split(" ");
			if (sharedkey.length > 1) {
				char[] fristletter = sharedkey[0].toCharArray();
				String lastname = sharedkey[1];
				String sharedKey = (fristletter[0]+lastname).toLowerCase();			
				cliente.setSharedKey(sharedKey);
			}else {
				cliente.setSharedKey(cliente.getName().replaceAll("\\s+",""));
			}
						
			log.info("<<<<<el Shared Key es>>>>>>> " + cliente.getSharedKey());
		}else {
			cliente.setSharedKey(cliente.getSharedKey().replaceAll("\\s+",""));
		}
		
		log.info(" Cliente: " + cliente);		
		cliente.setDataAdded(new Date());
		
		return clientRepository.save(cliente);
	}
	
	@Override
	public void deleteById(Long id) {
		try {
			clientRepository.deleteById(id);
		 
		} catch (Exception e) {
			 
		}
	}
}
