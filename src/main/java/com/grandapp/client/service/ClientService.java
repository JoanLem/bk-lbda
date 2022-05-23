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
	
	@Override
	public Optional<ClientModel> findById(Long id) {
		return null;
	}
	
	
	@Override
	public List<ClientModel> findBySharedKey(String sharedKey) throws Exception {
		return clientRepository.findBySharedKey(sharedKey);
	}

	@Override
	public List<ClientModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientModel> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<ClientModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientModel getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ClientModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(ClientModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends ClientModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends ClientModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ClientModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends ClientModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends ClientModel, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
