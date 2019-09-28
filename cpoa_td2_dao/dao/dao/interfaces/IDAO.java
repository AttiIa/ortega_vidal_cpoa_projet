package dao.interfaces;

public interface IDAO<T> {
		
		abstract boolean create(Object T);
		
		abstract boolean delete(Object T);
		
		abstract boolean update(Object T);
		
		abstract Object getById(int id);

}
