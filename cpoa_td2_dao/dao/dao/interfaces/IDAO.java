package dao.interfaces;

public interface IDAO<T> {
		
		abstract boolean create(T object);
		
		abstract boolean delete(T object);
		
		abstract boolean update(T object);
		
		abstract T getById(int id);

}
