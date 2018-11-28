package com.doHelp.dao;

import java.util.List;

import com.doHelp.model.Remarks;

public interface RemarksDAO {

    public List<Remarks> find() throws Exception ;
	public void save(Remarks remarks) throws Exception;
	public void update(Remarks remarks) throws Exception ;
	public void snapCount(Remarks remarks) throws Exception;
}
