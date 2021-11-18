package app.disney.service.implement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.disney.DTO.GenderDTO;
import app.disney.entitys.Gender;
import app.disney.repository.IGenderRepository;
import app.disney.service.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

	@Autowired
	private IGenderRepository genderRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Gender> getAllGender() {
		return genderRepo.findAll();
	}

	@Override
	public Gender saveGender(Gender gender) {
		return genderRepo.save(gender);
	}

	@Override
	public Gender getGenderById(Integer id) {
		return genderRepo.findById(id).get();
	}

	@Override
	public void deleteGenderById(Integer id) {
		genderRepo.deleteById(id);
	}
	
	/***BUSQUEDAS***/

	@Override
	public Gender getByGenderName(String name) {
		return genderRepo.findByGenderName(name);
	}

	
	/////////////////////////////////////////////////////
	@Override
	public List<Gender> mappingListToModel(List<GenderDTO> listDTO) {
		List<Gender> listGenderModel = listDTO
                                       .stream()
                                       .map(gender -> modelMapper.map(gender, Gender.class))
                                       .collect(Collectors.toList());
        return listGenderModel;
	}

	@Override
	public List<GenderDTO> mappingListToDTO(List<Gender> listModel) {
		List<GenderDTO> listGenderDTO = listModel
                                     .stream()
                                     .map(gender -> modelMapper.map(gender, GenderDTO.class))
                                     .collect(Collectors.toList());
        return listGenderDTO;
	}



}
