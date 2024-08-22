package webjingoo.daoex;

public class DaoMain {

	public static void doWork (DataAccessObject dao) {
		dao.insert();
		dao.select();
		dao.update();
		dao.delete();
	}
	
	public static void main(String[] args) {
		doWork(new OracleDao());
//		doWork(new MySqlDao());

	}

}
