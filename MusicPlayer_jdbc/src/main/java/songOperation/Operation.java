package songOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Operation {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static String Query;
	private static int rows;

	public static Connection getConn() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myMusicPlayer", "root", "root");
		return conn;
	}

	public static void addSong() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Song ID : ");
		int id = sc.nextInt();
		System.out.println("Enter Song name : ");
		String name = sc.next();
		System.out.println("Enter Singer Name: ");
		String singerName = sc.next();
		System.out.println("Enter Song Duration (e.g 5.49) : ");
		Double duration = sc.nextDouble();
		Query = "insert into songlist values (?,?,?,?)";
		pstmt = getConn().prepareStatement(Query);
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, singerName);
		pstmt.setDouble(4, duration);
		rows = pstmt.executeUpdate();
		if (rows == 1) {
			System.out.println("Song Added successfully...");
		}
		getConn().close();

	}

	public static void playSong() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Song ID : ");
		int id = sc.nextInt();
		Query = "Select * from songlist where id = ?";
		pstmt = getConn().prepareStatement(Query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString(2));
		}
		getConn().close();

	}

	public static void editSong() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Song ID : ");
		int id = sc.nextInt();
		Query = "Select * from songlist where id = ?";
		pstmt = getConn().prepareStatement(Query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("Your Details : ");
		while (rs.next()) {
			System.out.print("Id : " + rs.getInt(1));
			System.out.print("\tSong name : " + rs.getString(2));
			System.out.print("\tSinger name : " + rs.getString(3));
			System.out.println("\tSong Duration : " + rs.getDouble(4) + "min");
		}

		System.out.println("Enter Song name : ");
		String name = sc.next();
		System.out.println("Enter Singer Name: ");
		String singerName = sc.next();
		System.out.println("Enter Song Duration (e.g 5.49) : ");
		Double duration = sc.nextDouble();
		Query = "update songlist set name = '" + name + "' , singer = '" + singerName + "' " + ", duration = "
				+ duration + " where id = " + id;
		pstmt = getConn().prepareStatement(Query);
		rows = pstmt.executeUpdate();
		if (rows == 1) {
			System.out.println("Song Updated successfully...");
		}

		getConn().close();

	}

	public static void viewAllSong() throws Exception {
		Query = "Select * from songlist";
		pstmt = getConn().prepareStatement(Query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("Id : " + rs.getInt(1) + "\t SongName : " + rs.getString(2) + "\tSingerName : "
					+ rs.getString(3) + "\tDuration : " + rs.getDouble(4) + "min");

		}
	}
}
