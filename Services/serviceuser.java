/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Utils.DataSource;
import Utils.UserSession;
import java.sql.Connection;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Dell
 */
public class serviceuser implements Iservices <user>{
         Connection cnx = DataSource.getInstance().getCnx();
         public user userInfos;



    public void ajouter(user t)
    {
        
        String requete = "INSERT INTO user (nom,prenom,motdepasse,type,discr,diplome,entreprise,typedecontrat,posteoccupe,date,competence,secteur,siteweb,tailleentreprise,numerotelephone,img,roles,nbr_follow \n" +
")" + "VALUES (?,?,?,?,?,null,null,null,null,null,null,?,?,?,?,?,?,0)";
//      String requete;
//             requete = "INSERT INTO User (nom,prenom,motdepasse,discr) VALUES (?,?,?,?)";
//      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           
    
    
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            String hashedPassword = BCrypt.hashpw(t.getMotdepasse(), BCrypt.gensalt(13));
            pst.setString(3, hashedPassword);
            pst.setString(4, t.getType());
            pst.setString(5, t.getDiscr());
//        pst.setString(6, t.getDiplome());
//            pst.setString(7, t.getEntreprise());
//            pst.setString(8, t.getTypedecontrat());
//            pst.setString(9, t.getPosteoccupe());
//            pst.setDate(10,(Date) t.getDate());
//            pst.setString(11, t.getCompetence());
            pst.setString(6, t.getSecteur());
            pst.setString(7, t.getSiteweb());
            pst.setInt(8, t.getTailleentreprise());
            pst.setInt(9, t.getNumerotelephone());
            pst.setString(10,t.getImg());
            pst.setString(11,t.getRoles());
          //  pst.setInt(20, t.getEtat());
            
 //           pst.setString(12,t.getNbr_follow());
          
       System.out.println(t.getNom());
            pst.executeUpdate();
            System.out.println("Candidat ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    public user getUserById(String nom) {
        user user = null;
        String requete="SELECT * FROM user where nom=?";
        ResultSet res;

        try {
            
            PreparedStatement pst =
                 cnx.prepareStatement(requete);
            pst.setString(1, nom);
            res = pst.executeQuery();
            if (res.last())//kan il9a il user
            {
                user = new user(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),
                        res.getString(8),res.getString(9),res.getString(10),res.getDate(11),res.getString(12),res.getString(13),res.getString(14),res.getInt(15),
                        res.getInt(16),res.getString(17),res.getString(18),res.getInt(19),res.getInt(20),res.getString(21)); 
            } 

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public int loginMembre(String nom,String password) throws SQLException{
      user scarra = new user();
		scarra.setId(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT motdepasse FROM user where nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("motdepasse");
            }
			
			if(BCrypt.checkpw(password, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM user where nom=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, nom);
				rs = pst.executeQuery();
				while (rs.next()) {
					scarra.setId(rs.getInt("id"));
					scarra.setNom(rs.getString("nom"));
                                        scarra.setPrenom(rs.getString("prenom"));
					scarra.setMotdepasse(rs.getString("motdepasse"));
					scarra.setType(rs.getString("type"));
					scarra.setDiscr(rs.getString("discr"));
                                        scarra.setDiplome(rs.getString("diplome"));
					scarra.setEntreprise(rs.getString("entreprise"));
					scarra.setTypedecontrat(rs.getString("typedecontrat"));
					scarra.setPosteoccupe(rs.getString("posteoccupe"));
                                        scarra.setDate(rs.getDate("date"));
                                        scarra.setCompetence(rs.getString("competence"));
                                        scarra.setSecteur(rs.getString("secteur"));
                                        scarra.setSiteweb(rs.getString("siteweb"));      
                                        scarra.setTailleentreprise(rs.getInt("tailleentreprise"));
                                        scarra.setNumerotelephone(rs.getInt("numerotelephone"));
                                        scarra.setImg(rs.getString("img"));
                                        scarra.setRoles(rs.getString("roles"));
                                        scarra.setNbr_follow(rs.getInt("nbr_follow"));
                                        scarra.setEtat(rs.getInt("etat"));
                                         scarra.setBlock(rs.getString("block"));
					System.out.println("  aaaa "+scarra);
				}
                                  UserSession.getInstance().setLoggedUser(scarra); 
                                  return 1;
			}
			else {
				System.out.println("faill"+scarra.getId());
			}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	
		
		return 0;
    }
     
    public void ajouterEmployer(user t)
    {

//
        
        String requete = "INSERT INTO user (nom,prenom,motdepasse,type,discr,diplome,entreprise,typedecontrat,posteoccupe,date,competence,secteur,siteweb,tailleentreprise,numerotelephone,img,roles,nbr_follow \n" +
")" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,null,null,0,0,?,?,0)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);

    
    
         pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            String hashedPassword = BCrypt.hashpw(t.getMotdepasse(), BCrypt.gensalt(13));
            pst.setString(3, hashedPassword);
            pst.setString(4, t.getType());
            pst.setString(5, t.getDiscr());
            pst.setString(6, t.getDiplome());
            pst.setString(7, t.getEntreprise());
            pst.setString(8, t.getTypedecontrat());
            pst.setString(9, t.getPosteoccupe());
            pst.setDate(10,(Date) t.getDate());
         pst.setString(11, t.getCompetence());
//            pst.setString(6, t.getSecteur());
//            pst.setString(7, t.getSiteweb());
//            pst.setInt(8, t.getTailleentreprise());
//            pst.setInt(9, t.getNumerotelephone());
            pst.setString(12,t.getImg());
            pst.setString(13,t.getRoles());
            
 //           pst.setString(12,t.getNbr_follow());
          
       
            pst.executeUpdate();
            System.out.println("Employer ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        
        
    }
    

 @Override
    public void supprimer(user t) {
        try {
            String requete = "DELETE FROM User WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            System.out.println(t.getId());
            pst.executeUpdate();
            System.out.println("Membre Supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


     @Override
    public void modifier(user t) {
       try {
                
            String requete = "UPDATE user SET id =? , nom = ?,"
					+ " prenom = ?, motdepasse = ?, type = ?,"
					+ " discr = ?, diplome = ?, entreprise = ? , typedecontrat = ? , posteoccupe = ? , date = ? , competence = ? , secteur = ? , siteweb = ?,"
                                        + " tailleentreprise= ? , numerotelephone = ? , img = ?, roles = ?, nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, t.getId());
                        pst.setString(2, t.getNom());
			pst.setString(3, t.getPrenom());
			String hashedPassword = BCrypt.hashpw(t.getMotdepasse(), BCrypt.gensalt(13));
                        pst.setString(4, hashedPassword);
			pst.setString(5, t.getType());
                        pst.setString(6, t.getDiscr());
                        pst.setString(7, t.getDiplome());
                        pst.setString(8, t.getEntreprise());
                        pst.setString(9, t.getTypedecontrat());
                        pst.setString(10, t.getPosteoccupe());
                        pst.setDate(11, t.getDate());
                        pst.setString(12, t.getCompetence());
                        pst.setString(13, t.getSecteur());
                        pst.setString(14, t.getSiteweb());
                        pst.setInt(15, t.getTailleentreprise());
                        pst.setInt(16, t.getNumerotelephone());
                        pst.setString(17, t.getImg());
                        pst.setString(18, t.getRoles());
                        pst.setInt(19, t.getNbr_follow());
                        pst.setInt(20, t.getId());
               
			
			System.out.println(requete);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
   @Override
    public ObservableList<user> afficher() {
        ObservableList<user> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM User where discr='candidat'";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
               Image image = new Image(rs.getString(17)); //create img
                ImageView image1 = new ImageView(image);
                image1.setFitHeight(80);
                image1.setFitWidth(80);
               
                list.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),image1,rs.getInt(19),rs.getString(21))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
   
    public ObservableList<user> afficherEmlpoyer() {
        ObservableList<user> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM user where discr='employer'";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
               Image image = new Image(rs.getString(17)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                System.out.println(rs.getString(17));
               
                list.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getDate(11),rs.getString(12),imgV)); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
      public user login(String inputUsername, String inputPassword) {
		user scarra = new user();
		scarra.setId(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT motdepasse FROM user where nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("motdepasse");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword)&& scarra.getEtat()==0) {
				System.out.println("It matches");
				requete = "SELECT * FROM user where nom=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, inputUsername);
				rs = pst.executeQuery();
				while (rs.next()) {
					scarra.setId(rs.getInt("id"));
					scarra.setNom(rs.getString("nom"));
                                        scarra.setPrenom(rs.getString("prenom"));
					scarra.setMotdepasse(rs.getString("motdepasse"));
					scarra.setType(rs.getString("type"));
					scarra.setDiscr(rs.getString("discr"));
                                        scarra.setDiplome(rs.getString("diplome"));
					scarra.setEntreprise(rs.getString("entreprise"));
					scarra.setTypedecontrat(rs.getString("typedecontrat"));
					scarra.setPosteoccupe(rs.getString("posteoccupe"));
                                        scarra.setDate(rs.getDate("date"));
                                        scarra.setCompetence(rs.getString("competence"));
                                        scarra.setSecteur(rs.getString("secteur"));
                                        scarra.setSiteweb(rs.getString("siteweb"));      
                                        scarra.setTailleentreprise(rs.getInt("tailleentreprise"));
                                        scarra.setNumerotelephone(rs.getInt("numerotelephone"));
                                        scarra.setImg(rs.getString("img"));
                                        scarra.setRoles(rs.getString("roles"));
                                        scarra.setNbr_follow(rs.getInt("nbr_follow"));
                                        scarra.setEtat(rs.getInt("etat"));
					System.out.println("  aaaa "+scarra);
				}
			}
			else {
				System.out.println("tiiiiiiiiiiiiiiiiiiiiit"+scarra.getId());
			}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	
		
		return scarra;
	}

     public ObservableList<user> search(String input) {
		ObservableList <user> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT *"
					+ "FROM user "
					+ "WHERE (`nom` like ? or `prenom` like ? or "
					+ " `type` like ?) and (discr = 'candidat') ";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+input+"%");
			pst.setString(2, "%"+input+"%");
			pst.setString(3, "%"+input+"%");
			
                       
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Image image = new Image(rs.getString("img")); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                ListUsers.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getInt(16),imgV,rs.getInt(19),rs.getString(20))); 
			
				
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
     
public void follow(user employer, user candidat) {
       
        String requete = "INSERT INTO employee_candidat (employee_id,candidat_id)"
                + "VALUES (?,?)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, employer.getId());
            pst.setInt(2, candidat.getId());
            try {
            //candidat.setNbr_follow(candidat.getNbr_follow()+1); 
           
            String requete1 = "UPDATE user SET  nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            
            pst1.setInt(1,(candidat.getNbr_follow())+1);
            pst1.setInt(2, candidat.getId());
             System.out.println(candidat.getNbr_follow());
            System.out.println(employer.getId());
            System.out.println( candidat.getId());
            
          
       
            pst1.executeUpdate();
            pst.executeUpdate();
            System.out.println("follow ajoutee !");
            }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
            public void unfollow(user employer, user candidat) {
        String requete = "delete from employee_candidat where employee_id=? and candidat_id=?";
               
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, employer.getId());
            pst.setInt(2, candidat.getId());
            try {
            //candidat.setNbr_follow(candidat.getNbr_follow()+1); 
            String requete1 = "UPDATE user SET  nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1,candidat.getNbr_follow()-1);
            pst1.setInt(2, candidat.getId());
            System.out.println(candidat.getNbr_follow());
            System.out.println(employer.getId());
            System.out.println( candidat.getId());
            
          
       
            pst1.executeUpdate();
            pst.executeUpdate();
            System.out.println("follow suprimer !");
            }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
             public void block(user candidat) {
          String requete = "UPDATE user SET etat =?, block=?"
					+ " WHERE id = ?";
            try {
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1,1);
                         pst.setString(2,"blocked");
                        
                        pst.setInt(3,candidat.getId());
                        pst.executeUpdate();
            }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
       }
       
        public void unblock(user candidat) {
          String requete = "UPDATE user SET etat =?, block=?"
					+ " WHERE id = ?";
            try {
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1,0);
                         pst.setString(2,"notblocked");
                        
                        pst.setInt(3,candidat.getId());
                        pst.executeUpdate();
            }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
       }
        public int RecupPwd(String nom){
        String requete="SELECT * FROM user where nom=? ";
        ResultSet res;
        try {
           PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, nom);
            
            
            res= pst.executeQuery();
            if (res.last())
            {
                System.out.println("nom Trouver");
                return 1;
            }
            

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
            
}