package com.doHelp.service;

import java.util.List;

import com.doHelp.model.Remarks;

public interface RemarksServices {

    public List<Remarks> find() throws Exception ;
	public void save(Remarks remarsk) throws Exception;
	public void update(Remarks remarks) throws Exception ;
	public void snapCount(Remarks remarks) throws Exception;
	
}
