package com.doHelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.DAOFactory;
import com.doHelp.model.Remarks;
import com.doHelp.service.RemarksServices;
@Service
public class RemarksServiceImp implements RemarksServices{

	@Autowired
	DAOFactory  DAOFactory;
	@SuppressWarnings("static-access")
	@Override
	public void save(Remarks remarks) throws Exception {
		DAOFactory.getRemarksDAO().save(remarks);
	}

	@SuppressWarnings("static-access")
	@Override
	public List<Remarks> find() throws Exception {
		return DAOFactory.getRemarksDAO().find();
	}


	@SuppressWarnings("static-access")
	@Override
	public void update(Remarks remarks) throws Exception {
		DAOFactory.getRemarksDAO().update(remarks);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void snapCount(Remarks remarks) throws Exception {
		DAOFactory.getRemarksDAO().update(remarks);
	}
}
