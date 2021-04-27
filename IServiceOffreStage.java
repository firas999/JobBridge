/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;

/**
 *
 * @author User
 */
public interface IServiceOffreStage<T> {
    public List<T> readAll();
    void addOfferStage(T t);
    
}
