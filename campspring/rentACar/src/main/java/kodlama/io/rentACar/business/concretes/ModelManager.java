package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.entities.concretes.Model;

@Service
public class ModelManager implements ModelService {

	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> models = modelRepository.findAll();

		List<GetAllModelsResponse> modelsResponse = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
				.collect(Collectors.toList());
		return modelsResponse;
	}

	@Override
	public void add(CreateModelRequest createModelRequest) {
		
		Model model = new Model();

		Brand brand = this.brandRepository.getById(createModelRequest.getBrandId());
		
		model.setModelName(createModelRequest.getName());
		model.setBrand(brand);
		model.getBrand().setBrandId(createModelRequest.getBrandId());

		this.modelRepository.save(model);
	}

}





//		MODELMAPPER	Request
//		Model model=this.modelMapperService.forRequest()
//						.map(createModelRequest, Model.class);