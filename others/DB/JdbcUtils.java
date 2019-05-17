package DB;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.lang.reflect.Field;
import java.util.stream.IntStream;


public class JdbcUtils {
	/** Helper functions */
	private static String capitalize(String str){
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}

	@FunctionalInterface
	public interface FourFn<T,U,V,S,R>{
		R apply(T t,U u,V v,S s);
	}

	private static void setTypedParams(PreparedStatement ps, boolean nullable, Object... args) throws SQLException {
		int i=1;
		for(Object arg: args){
			if (arg != null){
				switch( ((Object) arg).getClass().getSimpleName() ){
					case "Integer": ps.setInt(i++, (Integer) arg); break;
					case "Long": ps.setLong(i++, (Long) arg); break;
					case "Double": ps.setDouble(i++, (Double) arg); break;
					case "Float": ps.setFloat(i++, (Float) arg); break;
					case "Boolean": ps.setBoolean(i++, (Boolean) arg); break;
					case "String": ps.setString(i++, (String) arg); break;
					case "Timestamp": ps.setTimestamp(i++, (Timestamp) arg); break;
					case "BigDecimal": ps.setBigDecimal(i++, (BigDecimal) arg); break;
					case "Date": ps.setDate(i++, (Date) arg); break;
				}
			} else if (nullable){
				ps.setString(i++, null);
			}
		}
	}


	/** return list of hashmap */
	private static List<Map<String,Object>> baseResultList(ResultSet rs) throws SQLException{
		List<Map<String,Object>> maps = new ArrayList<>();
		ResultSetMetaData metaData = rs.getMetaData();
		Map<String,Object> map;
		while(rs.next()) {
			map = new HashMap<>();
			for(int i=1; i<= metaData.getColumnCount(); i++){
				map.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			maps.add(map);
		}
		return maps;
	}

//	example: MgTransactionHistoryDAO
	public static List<Map<String,Object>> toResultList(Connection conn, String sql) throws SQLException {
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			return baseResultList(rs);
		}
	}

	public static List<Map<String,Object>> toResultList(PreparedStatement pst, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps, false, args);
			try(ResultSet rs = ps.executeQuery()){
				return baseResultList(rs);
		}}
	}

	/** return only single HashMap */
	private static Map<String,Object> baseResultMap(ResultSet rs, Map<String,Object> obj) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		while(rs.next()) {
			for(int i=1; i<= metaData.getColumnCount(); i++){
				obj.put(metaData.getColumnLabel(i), rs.getObject(i));
		}}
		return obj;
	}

	public static Map<String,Object> toResultMap(Connection conn, Map<String,Object> obj, String sql) throws SQLException {
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			return baseResultMap(rs, obj);
		}
	}
	public static Map<String,Object> toResultMap(Connection conn, String sql) throws SQLException {
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			return baseResultMap(rs, new HashMap<>());
		}
	}
	public static Map<String,Object> toResultMap(PreparedStatement pst, Map<String,Object> obj, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps, false, args);
			System.out.println(ps.toString());
			try(ResultSet rs = ps.executeQuery()){
				return baseResultMap(rs, obj);
		}}
	}
	public static Map<String,Object> toResultMap(PreparedStatement pst, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps, false, args);
			System.out.println(ps.toString());
			try(ResultSet rs = ps.executeQuery()){
				return baseResultMap(rs, new HashMap<>());
			}}
	}


	private static int getPsIndex(String name){
		int a = -1;
		switch(name){
			case "Int":  a=1; break;
			case "Long":  a=2; break;
			case "Double":  a=3; break;
			case "Float":  a=4; break;
			case "Boolean":  a=5; break;
			case "String":  a=6; break;
			case "Timestamp":  a=7; break;
			case "BigDecimal":  a=8; break;
		}
		return a;
	}

	public final static class CString {
		public String value;
		public CString(String value){
			this.value = value;
		}
	}

	private static void psSet(PreparedStatement ps, Object obj, int index, int pos) throws SQLException {
		switch( index ){
			case 1: ps.setInt(pos, (Integer) obj); break;
			case 2: ps.setLong(pos, (Long) obj); break;
			case 3: ps.setDouble(pos, (Double) obj); break;
			case 4: ps.setFloat(pos, (Float) obj); break;
			case 5: ps.setBoolean(pos, (Boolean) obj); break;
			case 6: ps.setString(pos, (String) obj); break;
			case 7: ps.setTimestamp(pos, (Timestamp) obj); break;
			case 8: ps.setBigDecimal(pos, (BigDecimal) obj); break;
		}
	}

	public static int oneUpdate(PreparedStatement pst, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps, true, args);
			int affectedRows = ps.executeUpdate();
			return affectedRows;
		}
	}

	public static <T> int oneObjUpdate(PreparedStatement pst, T obj, Object... args){
		List<T> list = new ArrayList<>();
		list.add(obj);
		return batchUpdate(pst, list, args);
	}

//	example: SportTeamDAO.batchInsertOnDuplicate
	public static <T> int batchUpdate(PreparedStatement pst, List<T> list, Object... args) {
		if (list.size() == 0) return 0;
		List<Integer> objPs = new ArrayList<>();
		List<Integer> objPos = new ArrayList<>();
		List<Method> objMethod = new ArrayList<>();
		List<Integer> constPos = new ArrayList<>();
		List<Integer> constPs = new ArrayList<>();
		List<Object> constList = new ArrayList<>();

		try {
			Class cls = list.get(0).getClass();

			try(PreparedStatement ps = pst){
//				get corresponding pos, obj types
				for(int i=0; i<args.length; i++){
					Object arg = args[i];
					String argType = arg.getClass().getSimpleName();
					if (argType.equals("String")){
						objPos.add(i+1);
						String fieldName = arg.toString();
						Field field;
						try {
							field = cls.getDeclaredField(fieldName);
						} catch(NoSuchFieldException e){
							field = cls.getSuperclass().getDeclaredField(fieldName);
						}
						objMethod.add(cls.getMethod( "get"+capitalize(fieldName) ));

						String type = capitalize(field.getType().getSimpleName());
						if(type.equals("Integer")) type = "Int";
						// System.out.println(types);
						objPs.add(getPsIndex(type));
					}
					else if (argType.equals("CString")) {
						constPos.add(i+1);
						String obj = ((CString) arg).value;
						constList.add(obj);
						constPs.add(6);
					}
					else {
						constList.add(arg);
						constPos.add(i+1);
						if (argType.equals("Integer")) argType = "Int";
						constPs.add(getPsIndex(capitalize(argType)));
					}
				}

//				loop through list of objects
				for(T obj: list){
					for(int i=0; i<objPos.size(); i++){
						psSet(ps, objMethod.get(i).invoke(obj), objPs.get(i), objPos.get(i));
					}
					for(int i=0; i<constPos.size(); i++){
						psSet(ps, constList.get(i), constPs.get(i), constPos.get(i));
					}
					ps.addBatch();
				}
				int[] affectedRows = ps.executeBatch();
				return IntStream.of(affectedRows).sum();
//			PreparedStatement cannot get updated num of rows
//				return list.size();
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return 0;
	}


	public final static class ConditionBuilder {
		private StringJoiner joiner = new StringJoiner(" AND ");

		public void and(String condition){
			joiner.add(condition);
		}

		public String build(){
			String strs = joiner.toString();
			return strs.equals("") ? "" : "WHERE " + strs + " ";
		}
	}


	/** return list of object entities */
	private static <T> List<T> baseObjectList(ResultSet rs, Class<T> cls) {
		List<T> objectList = new ArrayList<>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			List<Method> methodList = new ArrayList<>();
			List<Integer> rsIndex = new ArrayList<>();

			for(int i=1; i<= metaData.getColumnCount(); i++){
				String fieldName = metaData.getColumnLabel(i);
				Field field;
				try {
					field = cls.getDeclaredField(fieldName);
				} catch(NoSuchFieldException e){
					field = cls.getSuperclass().getDeclaredField(fieldName);
				}
				Method method = cls.getMethod( "set"+capitalize(fieldName) , field.getType());
				methodList.add(method);

				String types = capitalize(field.getType().getSimpleName());
				if(types.equals("Integer")) types = "Int";
				rsIndex.add(getPsIndex(types));
			}
			while(rs.next()) {
				T obj = (T) cls.newInstance();
				for(int i=0; i< metaData.getColumnCount(); i++){
					// stu.setName(rs.getString(0))
					switch(rsIndex.get(i)) {
						case 1: methodList.get(i).invoke(obj, rs.getInt(i+1)); break;
						case 2: methodList.get(i).invoke(obj, rs.getLong(i+1)); break;
						case 3: methodList.get(i).invoke(obj, rs.getDouble(i+1)); break;
						case 4: methodList.get(i).invoke(obj, rs.getFloat(i+1)); break;
						case 5: methodList.get(i).invoke(obj, rs.getBoolean(i+1)); break;
						case 6: methodList.get(i).invoke(obj, rs.getString(i+1)); break;
						case 7: methodList.get(i).invoke(obj, rs.getTimestamp(i+1)); break;
						case 8: methodList.get(i).invoke(obj, rs.getBigDecimal(i+1)); break;
					}
				}
				objectList.add(obj);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return objectList;
	}

	public static <T> List<T> toObjectList(Connection conn, Class<T> cls, String sql) throws SQLException{
		try(Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql)) {
			return baseObjectList(rs, cls);
		}
	}
	public static <T> List<T> toObjectList(PreparedStatement pst, Class<T> cls, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps,true, args);
			try(ResultSet rs = ps.executeQuery()){
				return baseObjectList(rs, cls);
			}
		}
	}
	public static <T> T toObjectMap(Connection conn, Class<T> cls, String sql) throws SQLException{
		try(Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql)) {
			List<T> ls = baseObjectList(rs, cls);
			return ls.size() == 0 ? null : ls.get(0);
		}
	}

//	example: MgTokenDAO
	public static <T> T toObjectMap(PreparedStatement pst, Class<T> cls, Object... args) throws SQLException {
		try(PreparedStatement ps = pst){
			setTypedParams(ps, true, args);
			try(ResultSet rs = ps.executeQuery()){
				List<T> ls = baseObjectList(rs, cls);
				return ls.size() == 0 ? null : ls.get(0);
			}
		}
	}



	


  public static <T> List<T> toObjectList2(Connection conn, Class<T> cls, String sql){

    List<T> objectList = new ArrayList<>();

		try(Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(sql)
		){
      ResultSetMetaData metaData = rs.getMetaData();
      List<Method> methodList = new ArrayList<>();
      List<Method> rsList = new ArrayList<>();

      for(int i=1; i<= metaData.getColumnCount(); i++){
        String fieldName = metaData.getColumnLabel(i);
        Field field = cls.getDeclaredField(fieldName);
        Method method = cls.getMethod( "set"+capitalize(fieldName) , field.getType());
        methodList.add(method);
        
        String types = field.getType().getSimpleName();
        if(types.equals("Integer")) types = "Int";
        Method m2 = (ResultSet.class).getMethod("get"+capitalize(types), int.class );
        rsList.add(m2);
      }

      while(rs.next()) {
          T obj = (T) cls.newInstance();
          for(int i=1; i<= metaData.getColumnCount(); i++){
            // stu.setName(rs.getString(0))
            methodList.get(i-1).invoke(obj, rsList.get(i-1).invoke(rs, i) );
          }
          objectList.add(obj);
      }

      return objectList;
    
    } catch(Exception e){
      e.printStackTrace();
    } catch(Throwable th) {
      th.printStackTrace();
    }
    return null;
  }

  public static List<Student> toObjectList3(Connection conn, String sql){

    List<Student> objectList = new ArrayList<>();

		try(Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(sql)
		){
      while(rs.next()) {
          Student stu = new Student();
          stu.setId(rs.getInt(1));
          stu.setName(rs.getString(2));
          stu.setSex(rs.getString(3));
          stu.setJob1(rs.getFloat(4));
          stu.setAge(rs.getInt(5));
          objectList.add(stu);
      }

      return objectList;
    
    } catch(Exception e){
      e.printStackTrace();
    } catch(Throwable th) {
      th.printStackTrace();
    }
    return null;
  }


}





