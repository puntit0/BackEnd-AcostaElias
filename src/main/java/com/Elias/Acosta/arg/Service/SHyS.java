/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Elias.Acosta.arg.Service;

import com.Elias.Acosta.arg.Entity.hys;
import com.Elias.Acosta.arg.Repository.RHyS;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHyS {
    @Autowired
    RHyS rHyS;
    
    public List<hys> list(){
        return rHyS.findAll();
    }
    
    public Optional<hys> getOne(int id){
        return rHyS.findById(id);
    }
    
    public Optional<hys> getByNombre(String nombre){
        return rHyS.findByNombre(nombre);
    }
    
    public void save(hys skill){
        rHyS.save(skill);
    }
    
    public void delete(int id){
        rHyS.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHyS.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHyS.existsByNombre(nombre);
    }
}
