package dao.interfaces;

import java.util.ArrayList;

public interface IDAO<T> {
		
		abstract boolean create(T object) throws Exception;
		
		abstract boolean delete(T object) throws Exception;
		
		abstract boolean update(T object) throws Exception;
		
		abstract T getById(int id);

		ArrayList<T> findAll() throws Exception;

}
