package com.grandapp.client.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.grandapp.client.model.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
	
	@Transactional(readOnly = true)
	List<ClientModel> findBySharedKey(String sharedKey) throws Exception;	
}
