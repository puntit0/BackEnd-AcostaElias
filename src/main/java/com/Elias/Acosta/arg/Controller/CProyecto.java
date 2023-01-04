/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Elias.Acosta.arg.Controller;

import com.Elias.Acosta.arg.Dto.dtoProyecto;
import com.Elias.Acosta.arg.Entity.Proyecto;
import com.Elias.Acosta.arg.Security.Controller.Mensaje;
import com.Elias.Acosta.arg.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://proyecfrontend.web.app", "http://localhost:4200"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
            
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe el ID"), HttpStatus.NOT_FOUND);
        }      
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto){
        if(StringUtils.isBlank(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getDescripcion())){
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
         if(sProyecto.existsByNombre(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = new Proyecto(
                dtoproyecto.getNombre(), 
                dtoproyecto.getDescripcion(),
                dtoproyecto.getImg()
        );
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("proyecto agregado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe el ID"),HttpStatus.NOT_FOUND);
        }
        if(sProyecto.existsByNombre(dtoproyecto.getNombre()) && sProyecto.getByNombre(dtoproyecto.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("el campo nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getDescripcion())){
            return new ResponseEntity(new Mensaje("el campo descripcion no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        //no quiero poner que sea obligatoria una imagen porque puede ser que no se tenga una
        
        Proyecto persona = sProyecto.getOne(id).get();
        
        persona.setNombre(dtoproyecto.getNombre());
        persona.setDescripcion(dtoproyecto.getDescripcion());
        persona.setImg(dtoproyecto.getImg());
        
        sProyecto.save(persona);
        return new ResponseEntity(new Mensaje("proyecto actualizado"), HttpStatus.OK);
    }   
}
