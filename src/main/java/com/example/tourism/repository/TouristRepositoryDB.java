package com.example.tourism.repository;
import java.sql.*;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.example.tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepositoryDB {
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;

    private String attractionToUpdate="";


    public void deleteAttraction(TouristAttraction touristAttraction){
        List<String> attractionIDS = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url ,username ,pwd)) {
            String SQL = "SELECT ID FROM TOURISTATTRACTIONS WHERE ATTRACTIONNAME=?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, touristAttraction.getName());
            ResultSet rs = ps.executeQuery();
            String attractionID = "";
            while (rs.next()) {
                attractionID = rs.getString(1);
            }

            String SQL1 = "SELECT * FROM TOURISTATTRACTION_TAGS WHERE TOURISTATTRACTION_ID=?";
            PreparedStatement ps1 = connection.prepareStatement(SQL1);
            ps1.setString(1, attractionID);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                attractionIDS.add(rs1.getString(1));
            }
            for (String s : attractionIDS){
                String SQL2 ="DELETE FROM TOURISTATTRACTION_TAGS WHERE TAG_ID=?";
                PreparedStatement ps2 = connection.prepareStatement(SQL2);
                ps2.setString(1, s);
                int rs2 = ps2.executeUpdate();
            }

            String SQL3 = "DELETE FROM TOURISTATTRACTIONS WHERE ID=?";
            PreparedStatement ps3 = connection.prepareStatement(SQL3);
            ps3.setString(1, attractionID);
            int rs3 = ps3.executeUpdate();
            String SQL4 = "DELETE FROM TOURISTATTRACTION_TAGS WHERE TOURISTATTRACTION_ID=?";
            PreparedStatement ps4 = connection.prepareStatement(SQL4);
            ps4.setString(1, attractionID);
            int rs4 = ps4.executeUpdate();

    } catch (SQLException e){
            System.out.println("Cannot connect to database");
        e.printStackTrace();}
    }


    public void updateAttraction(TouristAttraction touristAttraction) {
        List<String> attractionIDS = new ArrayList<>();
        List<String> newTags = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String attractionID = "";
            String SQL = "SELECT * FROM TOURISTATTRACTIONS WHERE ATTRACTIONNAME=?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, attractionToUpdate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                attractionID = rs.getString(1);
            }

            String SQL7 = "UPDATE TOURISTATTRACTIONS SET CITYPART=? WHERE ID=?";
            PreparedStatement ps7 = connection.prepareStatement(SQL7);
            ps7.setString(1, touristAttraction.getCityPart());
            ps7.setString(2, attractionID);
            int rs7 = ps7.executeUpdate();


            String SQL3 = "UPDATE TOURISTATTRACTIONS SET DESCRIPTION=? WHERE ID=?";
            PreparedStatement ps3 = connection.prepareStatement(SQL3);
            ps3.setString(1, touristAttraction.getDescription());
            ps3.setString(2, attractionID);
            int rs3 = ps3.executeUpdate();

            String SQL1 = "SELECT * FROM TOURISTATTRACTION_TAGS WHERE TOURISTATTRACTION_ID=?";
            PreparedStatement ps1 = connection.prepareStatement(SQL1);
            ps1.setString(1, attractionID);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                attractionIDS.add(rs1.getString(1));
            }
            for (String s : attractionIDS) {
                String SQL2 = "DELETE FROM TOURISTATTRACTION_TAGS WHERE TAG_ID=?";
                PreparedStatement ps2 = connection.prepareStatement(SQL2);
                ps2.setString(1, s);
                int rs2 = ps2.executeUpdate();
            }


            for (String s : touristAttraction.getTags()) {
                String SQL4 = "SELECT ID FROM TAGS WHERE NAME=?";
                PreparedStatement ps4 = connection.prepareStatement(SQL4);
                ps4.setString(1, s);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    newTags.add(rs4.getString(1));
                }

            }
            for (String s : newTags) {
                String SQL5 = "INSERT INTO TOURISTATTRACTION_TAGS  (TAG_ID, TOURISTATTRACTION_ID) VALUES (?, ?)";
                PreparedStatement ps5 = connection.prepareStatement(SQL5);
                ps5.setString(1, s);
                ps5.setString(2, attractionID);
                int rs5 = ps5.executeUpdate();

            }

        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}


    }

    public void addAttraction(TouristAttraction touristAttraction){
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "INSERT INTO TOURISTATTRACTIONS (ATTRACTIONNAME, DESCRIPTION, CITYPART) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, touristAttraction.getName());
            ps.setString(2, touristAttraction.getDescription());
            ps.setString(3, touristAttraction.getCityPart());
            int rs = ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}
    }

    public void addAttractionTags(TouristAttraction touristAttraction){
            List<String> tags = new ArrayList<>();
            List<String> tagsToAdd = touristAttraction.getTags();
            try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
                String SQ = "SELECT ID FROM TOURISTATTRACTIONS WHERE ATTRACTIONNAME=?";
                PreparedStatement p = connection.prepareStatement(SQ);
                p.setString(1, touristAttraction.getName());
                ResultSet r = p.executeQuery();
                String attractionID = "";
                while (r.next()) {
                    attractionID = r.getString(1);
                }
                for (String s : tagsToAdd) {
                    String SQL = "SELECT * FROM TAGS WHERE NAME=?";
                    PreparedStatement ps = connection.prepareStatement(SQL);
                    ps.setString(1, s);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        tags.add(rs.getString(1));
                    }
                }
                for (String s :tags){
                    String SQL = "INSERT INTO TOURISTATTRACTION_TAGS  (TAG_ID, TOURISTATTRACTION_ID) VALUES (?, ?)";
                    PreparedStatement ps = connection.prepareStatement(SQL);
                    ps.setString(1, s);
                    ps.setString(2, attractionID);
                    int rs = ps.executeUpdate();

                }
            }
            catch (SQLException e){
                System.out.println("Cannot connect to database");
                e.printStackTrace();}
    }

    public ArrayList<TouristAttraction> getTouristAttractions() {
        ArrayList<TouristAttraction> attractions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            Statement stmt = connection.createStatement();
            String SQL = "SELECT * FROM TOURISTATTRACTIONS";
            Statement stmt1 = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                attractions.add(new TouristAttraction(rs.getString(2), rs.getString(3), rs.getString(4)));
               /* System.out.print(rs.getString(2) + " ");
                System.out.print(rs.getString(3) + " ");
                System.out.println(rs.getString(4) + " ");*/
            }
        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}
        return attractions;
    }

    public TouristAttraction findAttractionByName(String name){
        List<String> tags = new ArrayList<>();
        TouristAttraction touristAttraction = new TouristAttraction();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT DISTINCT\n" +
                    "TAGS.NAME,\n" +
                    "TOURISTATTRACTIONS.ATTRACTIONNAME\n" +
                    "\n" +
                    "FROM TAGS\n" +
                    "JOIN TOURISTATTRACTION_TAGS\n" +
                    "\tON TAGS.ID = TOURISTATTRACTION_TAGS.TAG_ID\n" +
                    "JOIN TOURISTATTRACTIONS\n" +
                    "\tON TOURISTATTRACTION_TAGS.TOURISTATTRACTION_ID = TOURISTATTRACTIONS.ID\n" +
                    "    WHERE touristattractions.AttractionName=?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, name);
            String SQL1 = "SELECT * FROM TOURISTATTRACTIONS WHERE ATTRACTIONNAME=?";
            PreparedStatement ps1 = connection.prepareStatement(SQL1);
            ps1.setString(1, name);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString(1));
            }
            while (rs1.next()) {
                touristAttraction.setName(rs1.getString(2));
                touristAttraction.setDescription(rs1.getString(3));
                touristAttraction.setCityPart(rs1.getString(4));
            }
            touristAttraction.setTags(tags);
            attractionToUpdate = touristAttraction.getName();

        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}
        return touristAttraction;
    }

    public List<String> getCityParts(){
        List<String> cityParts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            Statement stmt = connection.createStatement();
            String SQL = "SELECT * FROM CITYPARTS";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                cityParts.add(rs.getString(1));
            }
        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}
        return cityParts;
    }


    public List<String> getTags(){
        List<String> tags = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            Statement stmt = connection.createStatement();
            String SQL = "SELECT * FROM TAGS";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                tags.add(rs.getString(2));
            }
        }
        catch (SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();}
        return tags;
    }
}