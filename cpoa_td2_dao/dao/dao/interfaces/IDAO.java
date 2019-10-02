package dao.interfaces;

import java.util.ArrayList;

public interface IDAO<T> {
		
		abstract boolean create(T object);
		
		abstract boolean delete(T object);
		
		abstract boolean update(T object);
		
		abstract T getById(int id);

		ArrayList<T> findAll();

}
