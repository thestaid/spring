package com.gura.spring.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring.users.dto.UsersDto;
//component tm
@Repository
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
		
	}

	@Override
	public boolean isValid(UsersDto dto) {
		UsersDto resultDto=session.selectOne("users.isValid", dto);
		if(resultDto==null){//select된 정보가 없으면
			return false;//잘못된 아이디 혹은 비밀번호
		}else{//select된 정보가 있으면
			return true;//맞는 정보
		}
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}

	@Override
	public boolean canUseId(String id) {
		//인자로 전달된 아이디를 DB에서 select 해본다.
		String selectedId=session.selectOne("users.isExistId", id);
		if(selectedId==null){//없으면
			return true;//사용가능한 아이디가 없으면
		}else{
			return false;
		}
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}

}
