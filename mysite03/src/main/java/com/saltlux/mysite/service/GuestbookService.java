package com.saltlux.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.GuestbookRepository;
import com.saltlux.mysite.vo.Guestbook02Vo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;

	public boolean delete(Guestbook02Vo vo) {
		return guestbookRepository.delete(vo);
	}

	public void add(Guestbook02Vo vo) {
		guestbookRepository.insert(vo);
	}

	public List<Guestbook02Vo> findAll() {
		return guestbookRepository.findAll();
	}
	
}
