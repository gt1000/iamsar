package aisar.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class Test {
	String driver = "org.postgresql.Driver";

    String url = "jdbc:postgresql://localhost:45432/postgres";       
    String user = "postgres";                                     
    String password = "postgres";      
	
//	@org.junit.jupiter.api.Test
//	void test() {
//		String query = "insert into access_log(user_name, year, month, day, year_week, week, hour, minute, created_date) "
//        			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		
//		LocalDateTime start = LocalDateTime.of(2022, 1, 1, 0, 0);
//        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 0, 0);
//
//		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
//        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
//        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
//        DateTimeFormatter weekOfYearFormatter = DateTimeFormatter.ofPattern("ww", Locale.KOREA); // 주는 Locale에 따라 달라질 수 있음
//        DateTimeFormatter weekOfMonthFormatter = DateTimeFormatter.ofPattern("W", Locale.KOREA);
//        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");
//        DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("mm");
//
//        try (Connection con = DriverManager.getConnection(url, user, password);
//             PreparedStatement pstmt = con.prepareStatement("INSERT INTO access_log (user_name, year, month, day, year_week, week, hour, minute, created_date) "
//             		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
//            
//        	int i = 0;
//            while (start.isBefore(end)) {
//            	pstmt.setString(1, "test_user" + i);  // 'user_name'
//            	pstmt.setString(2, start.format(yearFormatter));  // 'year'
//            	pstmt.setString(3, start.format(monthFormatter));  // 'month'
//            	pstmt.setString(4, start.format(dayFormatter));  // 'day'
//            	pstmt.setString(5, start.format(weekOfYearFormatter));  // 'year_week'
//            	pstmt.setString(6, start.format(weekOfMonthFormatter));  // 'week'
//            	pstmt.setString(7, start.format(hourFormatter));  // 'hour'
//            	pstmt.setString(8, start.format(minuteFormatter));  // 'minute'
//            	
//            	Timestamp timestamp = Timestamp.valueOf(start);
//            	pstmt.setTimestamp(9, timestamp);  // 'created_date'
//
//            	pstmt.executeUpdate();
//                
//                start = start.plusMinutes(1);  // 다음 1분으로 이동
//                i++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}

	
	@org.junit.jupiter.api.Test
	void dummyInsert() {
		String query = "insert into access_log(user_name, year, month, day, year_week, week, hour, minute, created_date) "
        			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		LocalDateTime start = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 0, 0);

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter weekOfYearFormatter = DateTimeFormatter.ofPattern("ww", Locale.KOREA); // 주는 Locale에 따라 달라질 수 있음
        DateTimeFormatter weekOfMonthFormatter = DateTimeFormatter.ofPattern("W", Locale.KOREA);
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("mm");

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO access_log (user_name, year, month, day, year_week, week, hour, minute, created_date) "
             		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
        	final int batchSize = 10000;
            int count = 0;
        	
        	int i = 0;
            while (start.isBefore(end)) {
            	pstmt.setString(1, "test_user" + i);  // 'user_name'
            	pstmt.setString(2, start.format(yearFormatter));  // 'year'
            	pstmt.setString(3, start.format(monthFormatter));  // 'month'
            	pstmt.setString(4, start.format(dayFormatter));  // 'day'
            	pstmt.setString(5, start.format(weekOfYearFormatter));  // 'year_week'
            	pstmt.setString(6, start.format(weekOfMonthFormatter));  // 'week'
            	pstmt.setString(7, start.format(hourFormatter));  // 'hour'
            	pstmt.setString(8, start.format(minuteFormatter));  // 'minute'
            	
            	Timestamp timestamp = Timestamp.valueOf(start);
            	pstmt.setTimestamp(9, timestamp);  // 'created_date'

            	pstmt.addBatch();
                if (++count % batchSize == 0) {
                	pstmt.executeBatch(); // 배치 사이즈에 도달하면 실행
                }
            	
                start = start.plusMinutes(1);  // 다음 1분으로 이동
                i++;
            }
            pstmt.executeBatch();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
