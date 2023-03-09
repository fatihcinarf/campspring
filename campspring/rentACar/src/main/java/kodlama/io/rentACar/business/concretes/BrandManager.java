package kodlama.io.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;

	@Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService,BrandBusinessRules brandBusinessRules) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
		this.brandBusinessRules = brandBusinessRules;
	}

	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandsResponse> brandResponse = new ArrayList<GetAllBrandsResponse>();
		for (Brand brand : brands) {
			GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
			responseItem.setBrandId(brand.getBrandId());
			responseItem.setBrandName(brand.getBrandName());
			brandResponse.add(responseItem);
		}
		return brandResponse;
	}

	@Override
	public void saveBrand(CreateBrandRequest createBrandRequest) {
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		
		Brand brand=this.modelMapperService.forRequest()
				.map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
	}

	@Override
	public void updateBrand(UpdateBrandRequest updateBrandRequest) {
		Brand brand=this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
		this.brandRepository.save(brand);
	}

	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand=this.brandRepository.findById(id).orElseThrow();
		GetByIdBrandResponse response =this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		return response;
	}

	@Override
	public void deleteBrand(int id) {
		this.brandRepository.deleteById(id);
		
	}

}
