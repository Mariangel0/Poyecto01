package org.example.proyecto_01.logic;

import org.example.proyecto_01.data.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private ClienteRepository clienteRepository;

    public Iterable<Cliente> clienteFindAll(){
        return clienteRepository.findAll();
    }

}
