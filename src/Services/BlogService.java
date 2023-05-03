/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.activation.DataSource;
import util.MyConnection;

/**
 *
 * @author Ala Jaidi
 */
public class BlogService {
    
    private final Connection cnx;

    private static BlogService instance;
    
        public BlogService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public static BlogService getInstance()
    {
        if (instance == null) {
            instance = new BlogService();
        }
        return instance; 
    }
    
   public void addBlog(Blog q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `blog`(`nomblog`,`descriptionblog`,`imageblog`,`date`) VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNomblog());
                st.setString(2,q.getDescription());
                st.setString(3,q.getImage());
                st.setDate(4,q.getDate());
                
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierBlog(Blog e) throws SQLDataException {
                String query = "UPDATE `blog` SET `nomblog`=?,`descriptionblog`=?,`imageblog`=?,`date`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getNomblog());
                st.setString(2,e.getDescription());
                st.setString(3,e.getImage());
                st.setDate(4,e.getDate());
                st.setInt(5,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Blog> getAllBlog() throws SQLDataException {

        
        List<Blog> list =new ArrayList<Blog>();
        int count =0;
        
        String requete="select * from blog";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Blog e = new Blog();
                e.setId(rs.getInt("id"));
                e.setDescription(rs.getString("descriptionblog"));
                e.setImage(rs.getString("imageblog"));
                e.setDate(rs.getDate("date"));
                e.setNomblog(rs.getString("nomblog"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Blog get_BlogById(int i) {
        Blog e = new Blog();
        int nombre = 0;
        String requete = "select * from blog where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setDescription(rs.getString("descriptionblog"));
                e.setImage(rs.getString("imageblog"));
                e.setDate(rs.getDate("date"));
                e.setNomblog(rs.getString("nomblog"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
   
    

    public boolean deleteBlog(int idCat) throws SQLDataException {
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM blog WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
}
