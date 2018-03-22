package domain.db;
import domain.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CountryDbSql implements CountryDB{
	private Properties properties = new Properties();
	private String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51314/webontwerp";
	
	public CountryDbSql() {
		properties.setProperty("user", "daily.build");
		properties.setProperty("password", "irooZiNgae0daiba");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	public List<Country> getAll() {
		List<Country> countries = new ArrayList<Country>();
		
		try (
			Connection connection = DriverManager.getConnection(url, properties);
			Statement statement = connection.createStatement();
		) {
		
		ResultSet result = statement.executeQuery("SELECT * FROM test_u0082726.country");
		
		// resultaten van de query tonen op console
		
		while (result.next()) {
			String name = result.getString("name");
			String capital = result.getString("capital");
			int votes = Integer.parseInt(result.getString("votes"));
			int inwoners = Integer.parseInt(result.getString("inhabitants"));
			Country country = new Country(name, inwoners, capital, votes);
			countries.add(country);
		}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return countries;
	}
	

	public Country getMostPopularCountry() {
		return null;
	}
public void add (Country country) {
		
	}
	
}
