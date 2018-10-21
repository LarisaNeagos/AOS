package firstHomework;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConfig {

	private InputStream inputStream;

	private DbConfig() {
	}

	private static class SingletonHolder {
		private static final DbConfig INSTANCE = new DbConfig();
	}

	public static DbConfig getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Connection connect() throws IOException {
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("databased.driver");
			String url = properties.getProperty("databased.url");
			String username = properties.getProperty("databased.username");
			String pwd = properties.getProperty("databased.password");

			Class.forName(driver);

			Connection con = DriverManager.getConnection(url, username, pwd);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return null;
	}

	public int insert(String name, String email) throws IOException {
		PreparedStatement ps;
		if (connect() != null) {
			try {
				ps = connect().prepareStatement("INSERT INTO person (name, email) VALUES(?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, email);
				return ps.executeUpdate();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}	
		}
		return 0;
	}
}
