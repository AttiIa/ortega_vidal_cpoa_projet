package dao.interfaces;

public interface IDao<T> {
		
		public abstract boolean create(Object T);
		
		public abstract boolean delete(Object T);
		
		public abstract boolean update(Object T);
		
		public abstract Object getById(int id);

}
