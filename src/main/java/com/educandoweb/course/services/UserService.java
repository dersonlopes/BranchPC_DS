package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

//buscar todos
	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

//buscar por id
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
				
	}

// incluir novo usuario
	public User insert(User obj) {
		return repository.save(obj);
	}

// Deletar um usuario por id
	public void delete(Long id) {
		repository.deleteById(id);
	}

// Atualizar um usuario 
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	// metodo de atualizacao chamado pelo update
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
