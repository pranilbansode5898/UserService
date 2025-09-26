package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repo.UserRepository;

@Service
public class UserService {
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public List<User> getAll() {
		return repo.findAll();
	}

	public Optional<User> getById(Long id) {
		return repo.findById(id);
	}

	public User create(User u) {
		return repo.save(u);
	}

	public User update(Long id, User upd) {
		return repo.findById(id).map(u -> {
			u.setName(upd.getName());
			u.setEmail(upd.getEmail());
			u.setAddress(upd.getAddress());
			return repo.save(u);
		}).orElse(null);
	}

	public boolean delete(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}
