/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Certification;
import entity.Entreprise;
import java.util.List;

/**
 *
 * @author ouaji
 */
public interface IService<T> {
    void insert(T t);
    List<T>readAll();
    List Trie_Desc();
    void supprimer(int t);
   
     public void modifier_2(T t,int id);
    public List<Certification> getCertification();
    public List<Entreprise> getEntreprise();
  
 
   
}
