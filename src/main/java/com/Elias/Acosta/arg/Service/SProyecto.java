/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Elias.Acosta.arg.Service;

import com.Elias.Acosta.arg.Entity.Proyecto;
import com.Elias.Acosta.arg.Repository.RProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyecto {
    @Autowired
    RProyecto Rproyecto;
    
    public List<Proyecto> list(){
        return Rproyecto.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return Rproyecto.findById(id);
    }
    
    public Optional<Proyecto> getByNombre(String nombre){
        return Rproyecto.findByNombre(nombre);
    }
    
    public void save(Proyecto proyecto){
        Rproyecto.save(proyecto);
    }
    
    public void delete(int id){
        Rproyecto.deleteById(id);
    }
    
    public boolean existsById(int id){
        return Rproyecto.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return Rproyecto.existsByNombre(nombre);
    }
}
