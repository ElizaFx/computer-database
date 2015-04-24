package com.excilys.formation.cdb.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.formation.cdb.util.Util;

public class LongSelectionValidator extends Validate<List<Long>> {
	private List<Long> output;

	public LongSelectionValidator(String input) {
		super(input);
		String[] ids = getInput() == null ? null : getInput().split(",");
		if ((ids != null) && (ids.length != 0)) {
			if (Arrays.stream(ids).allMatch(Util::isNumeric)) {
				output = Arrays.stream(ids).filter(id -> id != null)
						.map(Long::parseLong).collect(Collectors.toList());
			} else {
				setErrorMsg("Something goes wrong with your selection, wrong ids");
			}
		} else {
			setErrorMsg("You need to choose some computers first");
		}
	}

	@Override
	public List<Long> getOutput() {
		return output;
	}

}
