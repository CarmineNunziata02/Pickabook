package catalogoManagement;

import java.sql.SQLException;
import java.util.Collection;

public interface GenereDAO {
	public Collection<String> doRetrieveAll() throws SQLException;
	public Boolean checkGenereName(String genereName) throws SQLException;
}
