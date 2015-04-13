package com.excilys.formation.cdb.ui.cmd;

import com.excilys.formation.cdb.persistence.AbstractDAO;

public abstract class ListCommand<T> implements ICommand {

	private AbstractDAO<T> abstractDAO;

	public ListCommand(AbstractDAO<T> abstractDAO) {
		this.abstractDAO = abstractDAO;
	}

	@Override
	public void execute() {
		System.out.println("Début de la liste des elements :");
		abstractDAO.findAll().forEach(e -> System.out.println(format(e)));
		System.out.println("Fin de la liste des elements");
	}

	public abstract String format(T entity);
}
