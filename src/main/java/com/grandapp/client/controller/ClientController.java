package com.grandapp.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandapp.client.model.ClientModel;
import com.grandapp.client.service.ClientService;
import com.grandapp.response.GeneralResponse;

import lombok.extern.slf4j.Slf4j;

 
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	private ResponseEntity<GeneralResponse<List<ClientModel>>> findAll(){
		
		GeneralResponse<List<ClientModel>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<ClientModel> data= null;
		try {
			data = clientService.findAll();
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;
//			log.info("findAll Ejecutado con Exito");
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
	//		log.info("findAll Fallido >>" + e);	
		}
		
		return new ResponseEntity<>(response, status);
	}
	
	@GetMapping("/{sharedKey}")
	private ResponseEntity<GeneralResponse<List<ClientModel>>> findBySharedKey(@PathVariable("sharedKey") String sharedKey){
		
		GeneralResponse<List<ClientModel>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<ClientModel> data= null;
		try {
			
			data = clientService.findBySharedKey(sharedKey);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;
			if (data.isEmpty()) {
				response.setMessage("El cliente " + sharedKey +" no existe");
				//log.info("El Cliente" + sharedKey + "no Existe!");	
				
			}
			//log.info("findBySharedKey Ejecutado con Éxito >>" + sharedKey);	
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		//	log.error("findBySharedKey Fallido >>" + e);	
		}	
		
		return new ResponseEntity<>(response, status);
	}
	
	
	
	@PostMapping
	private ResponseEntity<GeneralResponse<ClientModel>> save(@RequestBody ClientModel client){
		GeneralResponse<ClientModel> response = new GeneralResponse<>();
		HttpStatus status = null;
		
		try {
			
			ClientModel cliente = clientService.save(client);
			response.setMessage("Cliente almacenado correctamente");
			response.setSuccess(true);
			response.setData(cliente);
			status = HttpStatus.OK;
			//log.info("save Ejecutado con Éxito " + cliente );
			
		} catch (Exception e) {
			
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			//log.error("error generado "+ e.getMessage());
		}
		return new ResponseEntity<>(response, status);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<GeneralResponse<Boolean>> delete(@PathVariable("id") Long id) {
		
		GeneralResponse<Boolean> response = new GeneralResponse<>();
		HttpStatus status = null;
		try {
			clientService.deleteById(id);
			response.setMessage("Cliente eliminado correctamente");
			response.setSuccess(true);
			//response.setData(data);
			status = HttpStatus.OK;
			//log.info("delete Ejecutado con Éxito " );
			 
		} catch (Exception e) {
			//log.info("Error delete client" + e);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			//log.error("error generado "+ e.getMessage());
		}
		return new ResponseEntity<>(response, status);
						
	}
	

	//metodo para validar si el servicio esta arriba
		@GetMapping("/test")
		private String saludar() {
			//log.info("estamos en el test");
			//log.warn("ewsto es un peligro");
			return "Hello! this service is Run";				
		}

}
