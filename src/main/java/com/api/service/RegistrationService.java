package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repositary.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;



    public RegistrationService(RegistrationRepository registrationRepository,ModelMapper modelMapper){
        this.registrationRepository=registrationRepository;
        this.modelMapper=modelMapper;


    }
    public List<RegistrationDto> getallRegistrtion(){
        List<Registration> all= registrationRepository.findAll();
       List<RegistrationDto> dtos= all.stream().map(r-> entityToDto(r)).collect(Collectors.toList());
        return  dtos;
    }



    public RegistrationDto createRegistration(RegistrationDto registrationDto){

        Registration reg = dtotoEntity(registrationDto);
        Registration saveEntity = registrationRepository.save(reg);

        RegistrationDto dt = entityToDto(saveEntity);

        dt.setMessage("Hello");
        return  dt;
    }



    public Registration dtotoEntity(RegistrationDto registrationdto){
        Registration registration=modelMapper.map(registrationdto,Registration.class);


//        Registration registration = new Registration();
//        registration.setName(registrationdto.getName());
//        registration.setEmail(registrationdto.getEmail());
//        registration.setMobile((registrationdto.getMobile()));
        return registration;
    }

    public RegistrationDto entityToDto(Registration registration){
RegistrationDto registrationDto=modelMapper.map(registration,RegistrationDto.class);
//        RegistrationDto registrationDto
//                = new RegistrationDto();
//        registrationDto.setName(registration.getName());
//        registrationDto.setEmail(registration.getEmail());
//        registrationDto.setMobile(registration.getMobile());
        return registrationDto;

    }

    public void deleteRegistration(long id)
    {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration rr= registrationRepository.findById(id).get();
        rr.setName(registration.getName());
        rr.setEmail(registration.getEmail());
        rr.setMobile(registration.getMobile());
        Registration sv = registrationRepository.save(rr);
        return sv;

    }


    public RegistrationDto getRegistrationById(long id) {

      Registration registration=  registrationRepository.findById(id).orElseThrow(
              ()-> new ResourceNotFoundException("Record Not Found")
      );
        return entityToDto(registration);
    }
}
