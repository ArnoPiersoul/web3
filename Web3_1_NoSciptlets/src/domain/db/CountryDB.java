package domain.db;

import java.util.List;

import domain.model.Country;

public interface CountryDB {

	List<Country> getAll();

	void add(Country country);

}
